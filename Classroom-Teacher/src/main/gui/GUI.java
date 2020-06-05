package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import main.event.DrawEventListener;
import main.gui.board.Canvas;
import main.net.Student;

public class GUI extends JPanel  implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private BorderLayout layout;
	
	private Canvas canvas;
	private Toolbar toolbar;
	
	private ArrayList<Student> students;
	private JPanel attPanel;
	private ArrayList<JButton> attendanceList;
	
	public GUI() {
		super();
		layout = new BorderLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 400));
		
		canvas = new Canvas();
		this.add(canvas, BorderLayout.CENTER);
		
		toolbar = new Toolbar(canvas);
		this.add(toolbar, BorderLayout.NORTH);
		
		students = new ArrayList<Student>();
		
		attPanel = new JPanel();
		attPanel.setLayout(new BoxLayout(attPanel, BoxLayout.Y_AXIS));
		attendanceList = new ArrayList<JButton>();
		this.add(attPanel, BorderLayout.EAST);
		attPanel.setVisible(true);
	}
	
	public void addDrawEventListener(DrawEventListener l) {
		canvas.addDrawEventListener(l);
	}
	
	public void timeRanOut() {
		canvas.timeRanOut();
		toolbar.timeRanOut();
	}
	
	public void setTimeLeft(int i) {
		toolbar.setTimeLeft(i);
	}
	
	public void addStudent(Student s) {
		students.add(s);
		JButton btn = new JButton(s.getName());
		attPanel.add(btn);
		attendanceList.add(btn);
		btn.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
		btn.setBackground(Color.white);
		btn.addActionListener(this);
	}
	
	public void raisedHand(Student s) {
		System.out.println(s.getName() + " raised their hand");
		for(int i = 0; i < students.size(); i++) {
			if(s.getName().equals(students.get(i).getName())){
				attPanel.getComponent(i).setBackground(Color.red);
				attPanel.repaint();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		for(int i = 0; i < students.size(); i++) {
			if(cmd.equals(students.get(i).getName())) {
				students.get(i).setRaisedHand(false);
				attendanceList.get(i).setBackground(Color.white);
				attPanel.repaint();
			}
		}
		
	}

}
