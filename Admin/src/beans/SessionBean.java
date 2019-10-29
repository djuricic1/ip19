package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.SessionDao;
import dao.UserDao;
import dto.User;

@ManagedBean(name = "sessionBean")
@ViewScoped
public class SessionBean {
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	private UserDao ud = new UserDao();
	private SessionDao sd = new SessionDao();

	private ArrayList<User> users = (ArrayList<User>) ud.getAllUsers();
	
	public String onLoad() throws IOException {
		if(loginBean.getAdmin() == null) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
			return "login.xhtml?faces-redirect=true";
		}else {
			return null;
		}
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public void blockUser() {
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (reqMap.containsKey("id")) {
			int z = Integer.parseInt(reqMap.get("id"));
			ud.blockUser(z, 1);
		}
		
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
	
}
