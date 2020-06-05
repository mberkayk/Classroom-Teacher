package main.event;

import java.util.ArrayList;

public interface DrawEventAnouncer {
	
	ArrayList<DrawEventListener> listeners = new ArrayList<DrawEventListener>();
	
	default void drawEvent(DrawEvent e) {
		for(int i = 0; i < listeners.size(); i++) {
			listeners.get(i).drawEvent(e);
		}
	}
	
	default void addDrawEventListener(DrawEventListener l) {
		listeners.add(l);
	}
	
}
