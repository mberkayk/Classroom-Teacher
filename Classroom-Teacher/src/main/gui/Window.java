package main.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import main.event.DrawEventListener;
import main.event.NetworkEvent;
import main.event.NetworkEventListener;

public class Window extends JFrame implements ActionListener, NetworkEventListener {
	
	private static final long serialVersionUID = 1L;
	
	GUI gui;
	
	Timer timer, decrement;
	int timeLeft; // in seconds
	
	public Window() {
		super("Classroom Server");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		gui = new GUI();
		
		this.add(gui);
		this.pack();

		timeLeft = 0;
		try {
			timeLeft = 60 * Integer.valueOf(JOptionPane.showInputDialog(this, "Enter the amount of time you need in minutes"));
		} catch (NumberFormatException e) {
			timeLeft = 300;
		}
		
		timer = new Timer(timeLeft * 1000, this);
		timer.setActionCommand("Time ran out");
		gui.setTimeLeft(timeLeft);
		
		decrement = new Timer(1000, this);
		decrement.setActionCommand("decrement");
		
		decrement.start();
		timer.start();
	}
	
	public void addDrawEventListener(DrawEventListener l) {
		gui.addDrawEventListener(l);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Time ran out")) {
			System.out.println("action");
			gui.timeRanOut();
			timer.stop();
			decrement.stop();
		}else if(e.getActionCommand().equals("decrement")) {
			timeLeft--;
			gui.setTimeLeft(timeLeft);
		}
	}
	
	public Timer getTimer() {
		return timer;
	}

	@Override
	public void networkEvent(NetworkEvent e) {
		if(e.getType().equals("New Student")) {
			gui.addStudent(e.getStudent());
		}else if(e.getType().equals("Raised Hand")) {
			gui.raisedHand(e.getStudent());
		}
	}
}
