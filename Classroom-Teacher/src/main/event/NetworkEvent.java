package main.event;

import main.net.Student;

public class NetworkEvent {

	private Student student;
	String type;
	public NetworkEvent(Student s, String str){
		this.student = s;
		type = str;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public String getType() {
		return type;
	}
	
}
