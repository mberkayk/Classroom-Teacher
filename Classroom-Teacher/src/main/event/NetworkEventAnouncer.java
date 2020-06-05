package main.event;

import java.util.ArrayList;

public interface NetworkEventAnouncer {
	
	ArrayList<NetworkEventListener> listeners = new ArrayList<NetworkEventListener>();
	
	default void addNetworkEventListener(NetworkEventListener n) {
		listeners.add(n);
	}
	
	default void networkEvent(NetworkEvent e) {
		for(NetworkEventListener listener : listeners) {
			listener.networkEvent(e);
		}
	}
	
}
