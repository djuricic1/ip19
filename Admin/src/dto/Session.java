package dto;

public class Session {
	private int id;
	private long timeLogged;
	private long timeSignedOut;
	private int sudentId;
	
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
	public int getSudentId() {
		return sudentId;
	}
	public void setSudentId(int sudentId) {
		this.sudentId = sudentId;
	}
	
	
}
