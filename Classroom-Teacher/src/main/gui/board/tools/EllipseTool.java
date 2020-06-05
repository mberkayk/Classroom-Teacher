package main.gui.board.tools;

import java.awt.Shape;

import main.gui.board.tools.shapes.Ellipse;
import main.gui.board.tools.shapes.ShapeType;

public class EllipseTool extends DrawTool {

	Ellipse shape;
	
	public EllipseTool() {
		this.shape = new Ellipse(0, 0, 0, 0);
		this.type = ShapeType.ELLIPSE;
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
		return new Ellipse(this.shape).shape();
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
