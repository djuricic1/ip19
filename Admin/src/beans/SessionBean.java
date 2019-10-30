package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;	
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.NewsEventDao;
import dao.SessionDao;
import dao.UserDao;
import dto.NewsEvent;
import dto.User;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean {
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	private UserDao ud = new UserDao();
	private SessionDao sd = new SessionDao();
	private NewsEventDao ned = new NewsEventDao();	
	
	private ArrayList<User> users = (ArrayList<User>) ud.getAllUsers();
	private ArrayList<NewsEvent> newsAndEvents = ned.getAll();
	
	public String onLoad() throws IOException {
		if(loginBean.getAdmin() == null) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
			return "login.xhtml?faces-redirect=true";
		}	else {
			return null;
		}
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public void blockUser(User user) {
	
			ud.blockUser(user.getId(), Math.abs(user.getDisabled() - 1));
			users = (ArrayList<User>) ud.getAllUsers();
		
	}
	public void resetPassword() {
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("id")) {
			int z = Integer.parseInt(reqMap.get("id"));
			ud.randomlyResetUserPassword(z);
		}
		
	}
	
	public int getNumberOfActiveUsers() {
		return sd.getNumberOfActiveUsers();
	}
	
	public int getNumberOfRegisteredUsers() {
		return ud.getNumberOfRegisteredUsers();
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public void deleteNewsEvent(NewsEvent ne) {
		
	
		
			ned.remove(ne);
			this.setNewsAndEvents(ned.getAll());
			//return "main.xhtml?faces-redirect=true";
			
		
	}

	public List<NewsEvent> getNewsAndEvents() {

		return newsAndEvents;
	}

	public void setNewsAndEvents(ArrayList<NewsEvent> newsAndEventsList) {
		this.newsAndEvents = newsAndEventsList;
	}
	
	public boolean isBlocked(int id) {
		//System.out.println(user == null);
		//return user.getDisabled() == 1;
		System.out.println(id);
		return true;
	}
}
 