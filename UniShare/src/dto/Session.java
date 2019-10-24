package dto;

public class Session {
	private int id;
	private long timeLogged;
	private long timeSignedOut;
	private int studentId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getTimeLogged() {
		return timeLogged;
	}
	public void setTimeLogged(long timeLogged) {
		this.timeLogged = timeLogged;
	}
	public long getTimeSignedOut() {
		return timeSignedOut;
	}
	public void setTimeSignedOut(long timeSignedOut) {
		this.timeSignedOut = timeSignedOut;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
}
