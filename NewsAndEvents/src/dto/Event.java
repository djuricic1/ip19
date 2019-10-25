package dto;

public class Event {
	
	private int id;
	private int title;
	private long startOn;
	private long endsOn;
	private String description;
	private String imageUrl;
	private String category;
	private int numberOfLikes;
	private int numberOfDislikes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTitle() {
		return title;
	}
	public void setTitle(int title) {
		this.title = title;
	}
	public long getStartOn() {
		return startOn;
	}
	public void setStartOn(long startOn) {
		this.startOn = startOn;
	}
	public long getEndsOn() {
		return endsOn;
	}
	public void setEndsOn(long endsOn) {
		this.endsOn = endsOn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
}
