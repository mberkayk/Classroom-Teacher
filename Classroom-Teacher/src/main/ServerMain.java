package main;

import main.gui.Window;
import main.net.ClassroomServer;

public class ServerMain {
	
	static Window window;
	static ClassroomServer server;
	
	public static void main(String[] args) {
		window  = new Window();
		server = new ClassroomServer();
		
		window.getTimer().addActionListener(server);
		window.addDrawEventListener(server);
		
		server.addNetworkEventListener(window);
	}
	
}
