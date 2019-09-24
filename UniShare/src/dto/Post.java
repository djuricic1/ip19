package dto;

import java.util.Date;

public class Post {
	private int id;
	private int numberOfLikes;
	private int numberOfDislikes;
	private int studentId;
	private Date datePosted;
	private String description;
	private String typeOfPost;
	private String linkPost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfLikes() {
		return numberOfLikes;
	}
	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}
	public int getNumberOfDislikes() {
		return numberOfDislikes;
	}
	public void setNumberOfDislikes(int numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getTypeOfPost() {
		return typeOfPost;
	}
	public void setTypeOfPost(String typeOfPost) {
		this.typeOfPost = typeOfPost;
	}
	public String getLinkPost() {
		return linkPost;
	}
	public void setLinkPost(String linkPost) {
		this.linkPost = linkPost;
	}
	
	
}
