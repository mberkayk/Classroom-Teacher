package main.gui.board.tools;

import java.awt.Shape;

import main.gui.board.tools.shapes.Rect;
import main.gui.board.tools.shapes.ShapeType;

public class RectTool extends DrawTool {

	Rect shape;
	
	public RectTool() {
		this.shape = new Rect(0, 0, 10, 10);
		this.type = ShapeType.RECT;
	}
	
	@Override
	public void setPos(int x, int y) {
		this.shape.setPos(x, y);
	}

	@Override
	public void setDimension(int w, int h) {
		this.shape.setDimension(w, h);		
	}
	
	public Shape apply() {
		return new Rect(this.shape).shape();
	}

	public Shape preview() {
		return shape.shape();
	}

	@Override
	public int getX1() {
		return shape.getX();
	}

	@Override
	public int getY1() {
		return shape.getY();
	}

	@Override
	public int getX2() {
		return shape.getX() + shape.getWidth();
	}

	@Override
	public int getY2() {
		return shape.getY() + shape.getHeight();
	}
	
}
