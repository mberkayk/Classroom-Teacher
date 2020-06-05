package main.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import main.event.DrawEvent;
import main.event.DrawEventListener;
import main.event.NetworkEvent;
import main.event.NetworkEventAnouncer;

public class ClassroomServer implements DrawEventListener, ActionListener, NetworkEventAnouncer {
	
	private final int PORT = 5500;
	private ServerSocket serverSocket;
	
	private ArrayList<Student> students;
	
	private Thread acceptConnThread, listenToRaisedHandsThread;
	
	private File attendance;
	
	private boolean running;
	
	public ClassroomServer() {
		running = true;
		
		students = new ArrayList<Student>();
		attendance = new File("./attendance.txt");
		try {
			System.out.println("Attendance is at: " + attendance.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		acceptConnThread = new Thread(new Runnable() {
			public void run() {
				while(running) {
					try {
						System.out.println("Listening for clients");
						Socket clientSocket = serverSocket.accept();
						Student s = new Student(clientSocket);
						networkEvent(new NetworkEvent(s, "New Student"));
						students.add(s);
						FileWriter w = new FileWriter(attendance, true);
						w.write(s.getName() + '\n');
						w.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		acceptConnThread.start();
		
		listenToRaisedHandsThread = new Thread(new Runnable() {
			public void run() {
				while(running) {
					for(Student s : students) {
						try {
							String msg = s.in.readLine();
							if(msg.equals("Raise Hand")){
								s.setRaisedHand(true);
								networkEvent(new NetworkEvent(s, "Raised Hand"));
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		listenToRaisedHandsThread.start();
	}
	
	@Override
	public void drawEvent(DrawEvent e) {
		String msg = e.toString();
		System.out.println(msg);
		
		for(Student s : students) {
			PrintWriter out = null;
			try {
				out = new PrintWriter(s.getSocket().getOutputStream(), true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			out.println(msg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Time Ran out");
		this.close();
	}
	
	public void close() {
		running = false;
		try {
			acceptConnThread.join(1);
			listenToRaisedHandsThread.join(1);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
		try {
			for(Student s : students) {
				s.getSocket().close();
			}
			serverSocket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	public ArrayList<Student> getStudents(){
		return students;
	}

}
