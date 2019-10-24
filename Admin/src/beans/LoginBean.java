package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.LoginDao;
import dao.SessionDao;
import dao.UserDao;
import dto.User;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

	/*
	 * 	
	 */
	private static final long serialVersionUID = 6955508471291131930L;
	private SessionDao sd = new SessionDao();
	private UserDao ud = new UserDao();
	private ArrayList<User> users = (ArrayList<User>) ud.getAllUsers();
	
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		System.out.println("test");
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() {
		
		System.out.println("TEST 1234");
		
		boolean valid = LoginDao.validate(user, pwd);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "main";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
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

	
	
}
