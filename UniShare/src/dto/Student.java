package dto;

import java.util.Date;

public class Student {

	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String mail;
	private Faculty faculty;
	private String description;
	private byte[] image;
	private String studyProgram;
	private int facultyYear;
	private Date lastTimeActive;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getStudyProgram() {
		return studyProgram;
	}
	public void setStudyProgram(String studyProgram) {
		this.studyProgram = studyProgram;
	}
	public int getFacultyYear() {
		return facultyYear;
	}
	public void setFacultyYear(int facultyYear) {
		this.facultyYear = facultyYear;
	}
	public Date getLastTimeActive() {
		return lastTimeActive;
	}
	public void setLastTimeActive(Date lastTimeActive) {
		this.lastTimeActive = lastTimeActive;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	
	
}
