package main.gui.board.tools.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ellipse {
	
	private Ellipse2D shape;
	private int x, y, width, height;
	
	public Ellipse(Ellipse e) {
		this.x = e.x;
		this.y = e.y;
		this.width = e.width;
		this.height = e.height;
		shape = new Ellipse2D.Float(x, y, width, height);
	}
	
	public Ellipse(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		shape = new Ellipse2D.Float(x, y, w, h);
	}
	
	public Shape shape() {
		
		if(width < 0 && height < 0) {
			shape.setFrame(x+width, y+height, -width, -height);
		}else if(width < 0 && height >=0) {
			shape.setFrame(x+width, y, -width, height);
		}else if(width >= 0 && height < 0) {
			shape.setFrame(x, y+height, width, -height);
		}else {
			shape.setFrame(x, y, width, height);
		}
		
		return shape;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
		this.shape();
	}
	
	public void setDimension(int w, int h) {
		width = w;
		height = h;
		this.shape();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
