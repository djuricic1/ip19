package service;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import beans.StudentBean;
import daoimpl.SessionDaoImpl;
import dto.Session;
import dto.Student;

@WebListener
public class AttributeListener implements HttpSessionAttributeListener  {
	
	SessionDaoImpl sdi = new SessionDaoImpl();
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		//String attributeName = event.getName();
		Object attributeValue = event.getValue();
		if(attributeValue instanceof StudentBean) {
			
			Student student = ((StudentBean) attributeValue).getStudent();
		//	System.out.println(student.getStudent().getName());
			Session session = new Session();
			session.setStudentId(student.getId());
			session.setTimeLogged(System.currentTimeMillis());
			((StudentBean) attributeValue).setSessionId(sdi.insertSession(session));
			
		}
		
		
		//System.out.println("Attribute added : " + attributeName + " : " + attributeValue);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String attributeName = event.getName();
		Object attributeValue = event.getValue();
		System.out.println("Attribute removed : " + attributeName + " : " + attributeValue);
		if(attributeValue instanceof StudentBean) {
			
			Student student = ((StudentBean) attributeValue).getStudent();
			int sessionId = ((StudentBean) attributeValue).getSessionId();
			Session session = new Session();
			session.setId(sessionId);
			session.setTimeSignedOut(System.currentTimeMillis());
			sdi.updateSession(session);
			
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		String attributeName = event.getName();
		Object attributeValue = event.getValue();
		System.out.println("Attribute replaced : " + attributeName + " : " + attributeValue);	
	}
	
}
