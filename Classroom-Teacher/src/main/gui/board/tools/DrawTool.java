package main.gui.board.tools;

import java.awt.Shape;

import main.gui.board.tools.shapes.ShapeType;

public abstract class DrawTool {
	
	protected Shape shape;
	protected ShapeType type;
	public abstract void setPos(int x, int y);
	public abstract void setDimension(int w, int h);
	public abstract Shape apply();
	public abstract Shape preview();
	public ShapeType getType() {
		return this.type;
	}
	
	public abstract int getX1();
	public abstract int getY1();
	public abstract int getX2();
	public abstract int getY2();
	
}
