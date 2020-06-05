package main.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.event.DrawEvent;
import main.event.DrawEventListener;
import main.gui.board.Canvas;
import main.gui.board.tools.EllipseTool;
import main.gui.board.tools.LineTool;
import main.gui.board.tools.RectTool;

public class Toolbar extends JPanel implements ActionListener, DrawEventListener {
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	private JRadioButton rectBtn;
	private JRadioButton elpsBtn;
	private JRadioButton lineBtn;
	private ButtonGroup group;
	
	private JLabel numberLabel, descriptionLabel;
	
	private RectTool rectTool;
	private EllipseTool elpsTool;
	private LineTool lineTool;
	
	private JButton clrBtn;
	
	private JLabel timeLabel;
	
	public Toolbar(Canvas c) {
		super();
		
		this.canvas = c;
		canvas.addDrawEventListener(this);
		
		rectTool = new RectTool();
		elpsTool = new EllipseTool();
		lineTool = new LineTool();
		
		canvas.setTool(rectTool);
		
		descriptionLabel = new JLabel("Number of Shapes: ");
		this.add(descriptionLabel);
		
		numberLabel = new JLabel(String.valueOf(canvas.getNumberOfShapes()));
		this.add(numberLabel);
		
		this.rectBtn = new JRadioButton("Rect");
		this.rectBtn.setSelected(true);
		this.elpsBtn = new JRadioButton("Ellipse");
		this.lineBtn = new JRadioButton("Line");
		
		group = new ButtonGroup();
		group.add(rectBtn);
		group.add(elpsBtn);
		group.add(lineBtn);
		
		this.rectBtn.addActionListener(this);
		this.elpsBtn.addActionListener(this);
		this.lineBtn.addActionListener(this);
		
		this.add(rectBtn);
		this.add(elpsBtn);
		this.add(lineBtn);
		
		this.clrBtn = new JButton("Clear");
		
		this.clrBtn.addActionListener(this);
		
		this.add(this.clrBtn);
		
		timeLabel = new JLabel("300");
		this.add(timeLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
			case "Rect":
				canvas.setTool(rectTool);
				break;
			case "Ellipse":
				canvas.setTool(elpsTool);
				break;
			case "Line":
				canvas.setTool(lineTool);
				break;
			case "Clear":
				canvas.clear();
				break;
		}
		
	}
	
	@Override
	public void drawEvent(DrawEvent e) {
		numberLabel.setText(String.valueOf(canvas.getNumberOfShapes()));
		this.repaint();
	}
	
	public void timeRanOut() {
		clrBtn.setEnabled(false);
		rectBtn.setEnabled(false);
		elpsBtn.setEnabled(false);
		lineBtn.setEnabled(false);
	}
	
	public void setTimeLeft(int timeInSeconds) {
		String minutes = String.valueOf((int)Math.floor(timeInSeconds / 60));
		String seconds = String.valueOf((int)Math.floor(timeInSeconds % 60));
		
		timeLabel.setText(minutes + ":" + seconds);
	}
	
}
