package beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "sessionBean")
@ViewScoped
public class SessionBean {
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
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
	
	
}
