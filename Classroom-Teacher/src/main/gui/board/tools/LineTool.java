package main.gui.board.tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

import main.gui.board.tools.shapes.ShapeType;

public class LineTool extends DrawTool {

	Line2D shape;
	
	public LineTool() {
		this.shape = new Line2D.Float(0, 0, 10, 10);
		this.type = ShapeType.LINE;
	}
	
	@Override
	public void setPos(int x, int y) {
		int dx = (int) (x - this.shape.getX1());
		int dy = (int) (y - this.shape.getY1());
		this.shape.setLine(x, y, shape.getX2() + dx, shape.getY2() + dy);
	}

	@Override
	public void setDimension(int w, int h) {
		this.shape.setLine(shape.getX1(), shape.getY1(), shape.getX1() + w, shape.getY1() + h);
	}
	
	public Shape apply() {
		return (Shape) shape.clone();
	}
	
	public Shape preview() {
		return shape;
	}

	@Override
	public int getX1() {
		return (int)shape.getP1().getX();
	}

	@Override
	public int getY1() {
		return (int)shape.getP1().getY();
	}

	@Override
	public int getX2() {
		return (int)shape.getP2().getX();
	}

	@Override
	public int getY2() {
		return (int)shape.getP2().getY();
	}

}
