package main.event;

import main.gui.board.tools.shapes.ShapeType;

public class DrawEvent {
	private ShapeType type;
	private int x1, y1, x2, y2;

	public DrawEvent(ShapeType s, int x1, int y1, int x2, int y2) {
		this.type = s;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public String toString() {
		String result = "";
		result += type.toString() + " ";
		result += x1 + " ";
		result += y1 + " ";
		result += x2 + " ";
		result += y2 + " ";
		
		return result;
	}
	
	public ShapeType getType() {
		return type;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}
}
