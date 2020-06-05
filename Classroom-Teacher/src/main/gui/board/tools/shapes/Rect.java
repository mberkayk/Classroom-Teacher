package main.gui.board.tools.shapes;

import java.awt.Rectangle;
import java.awt.Shape;

public class Rect {
	private Rectangle shape;
	private int x, y, width, height;
	
	public Rect(Rect r) {
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;
		shape = new Rectangle(x, y, width, height);
	}
	
	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		shape = new Rectangle(x, y, w, h);
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

