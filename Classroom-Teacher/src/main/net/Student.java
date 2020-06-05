package main.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Student {
	private Socket socket;
	public BufferedReader in;
	private boolean raisedHand;
	private String name;
	
	public Student(Socket s) {
		this.socket = s;
		in = null;
		name = "";
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			name = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		raisedHand = false;
	}

	public Socket getSocket() {
		return socket;
	}

	public boolean isRaisedHand() {
		return raisedHand;
	}

	public String getName() {
		return name;
	}
	
	public void setRaisedHand(boolean b) {
		raisedHand = b;
	}
	
}
