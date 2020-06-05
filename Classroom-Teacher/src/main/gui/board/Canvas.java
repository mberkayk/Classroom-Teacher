package main.gui.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.event.DrawEvent;
import main.event.DrawEventAnouncer;
import main.gui.board.tools.DrawTool;
import main.gui.board.tools.shapes.ShapeType;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener, DrawEventAnouncer {	
	private static final long serialVersionUID = 1L;

	private ArrayList<Shape> shapes;
	
	private DrawTool tool;
	
	private int preX,preY;
	
	private boolean timeRanOut;
	
	public Canvas() {
		super();
		this.setPreferredSize(new Dimension(400, 400));
		this.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		shapes = new ArrayList<Shape>();
		
		timeRanOut = false;
	}

	public void setTool(DrawTool t) {
		this.tool = t;
	}
	
	@Override
	public void paint(Graphics gr) {
		Graphics2D g = (Graphics2D)(gr);
		//background
		g.setColor(new Color(30, 50, 100)); 
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	
		g.setColor(Color.white);
		
		//all the shapes
		for(int i = 0; i < shapes.size(); i++) {
			g.draw(shapes.get(i));
		}
		
		if(timeRanOut) {
			g.setColor(new Color(230, 40, 40));
			g.setFont(new Font("", Font.BOLD, 50));
			
			g.drawString("TIME", this.getWidth()/5, this.getHeight()/5);
			g.drawString("RAN OUT", this.getWidth()/5, this.getHeight()*2/5);
		}
		
		
	}

	public void clear() {
		this.shapes.clear();
		this.repaint();
		this.drawEvent(new DrawEvent(ShapeType.ERASER, 0, 0, this.getWidth(), this.getHeight()));
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
//		System.out.println("pressed " + x + " " + y);
		
		this.tool.setPos(x, y);
		this.tool.setDimension(0, 0);
		this.shapes.add(this.tool.preview()); // add a preview shape that changes its dimensions with every mouseDragged event

		this.preX = x;
		this.preY = y;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int dx = x - preX;
		int dy = y - preY;
		
		this.tool.setDimension(dx, dy); //set the dimension of the preview shape
		
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.shapes.remove(this.shapes.size()-1); //remove the preview shape
		this.shapes.add(this.tool.apply()); // add a new static shape
		this.drawEvent(new DrawEvent(tool.getType(), tool.getX1(), tool.getY1(), tool.getX2(), tool.getY2()));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int getNumberOfShapes() {
		return shapes.size();
	}

	public void timeRanOut() {
		timeRanOut = true;
		this.removeMouseListener(this);
		this.removeMouseMotionListener(this);
		this.repaint();
	}
	
}
