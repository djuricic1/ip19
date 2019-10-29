package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.EventDao;
import daoimpl.EventDaoImpl;
import dto.Event;

@ManagedBean(name = "eventBean")
@RequestScoped
public class EventBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3556362880962978541L;
	
	public EventBean() {
		categories.add("Exam");
		categories.add("Lecture");
		categories.add("Promotion");
		categories.add("Practice");
		categories.add("Travel");
		
	}
	
	private Event event = new Event();
	private List<String> categories = new ArrayList<String>();
	private Date startDate = new Date();
	private Date endDate = new Date();
	
	public String saveEvent() {
		EventDao eventDao = new EventDaoImpl();
		
		//System.out.println(event.getTitle());
		
		
		event.setStartOn(startDate.getTime());
		event.setEndsOn(endDate.getTime());
		eventDao.addEvent(event);
		this.event.setCategory(null);
		this.event.setDescription(null);
		this.event.setEndsOn(0);
		this.event.setId(0);
		this.event.setImageUrl(null);
		this.event.setStartOn(0);
		this.event.setTitle(null);
		
		return null;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	
	
}
