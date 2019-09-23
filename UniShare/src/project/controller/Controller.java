package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.StudentBean;
import daoimpl.FacultyDaoImpl;
import daoimpl.StudentDaoImpl;
import dto.Faculty;
import dto.Student;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentDaoImpl sdi = new StudentDaoImpl();
	private FacultyDaoImpl fdi = new FacultyDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.setCharacterEncoding("UTF-8");
		String address = "/login.jsp";
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if (action == null || action.equals("")) {
			address = "/login.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			address = "/login.jsp";
		} else if (action.equals("login")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			StudentBean studentBean = new StudentBean();
			System.out.println(username + " " + password);
			if(studentBean.login(username, password)) {
				session.setAttribute("studentBean", studentBean);
				address = "/WEB-INF/pages/main.jsp";
			}
		} else if (action.equals("registration")) {
			String name = req.getParameter("name");
			String surname = req.getParameter("surname");
			String password = req.getParameter("password");
			String username = req.getParameter("username");
			String mail = req.getParameter("mail");
			StudentBean studentBean = new StudentBean();
			Student student = new Student(); 
			student.setName(name);
			student.setSurname(surname);
			student.setPassword(password);
			student.setUsername(username);
			student.setMail(mail);
			studentBean.setStudent(student);
			if(studentBean.add(student)) {
				session.setAttribute("studentBean", studentBean);
				address = "/WEB-INF/pages/updateProfile.jsp";
			}
			else {
				address = "/registration.jsp";
			}
		} else if (action.equals("update")) {
			Faculty faculty = fdi.getFacultyByName(req.getParameter("faculty"));
			
			StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
			studentBean.getStudent().setName(req.getParameter("name"));
			studentBean.getStudent().setSurname(req.getParameter("surname"));
			studentBean.getStudent().setPassword(req.getParameter("password"));
			studentBean.getStudent().setDescription(req.getParameter("description"));
			studentBean.getStudent().setStudyProgram(req.getParameter("studyProgram"));
			studentBean.getStudent().setFaculty(faculty);
			studentBean.getStudent().setFacultyYear(Integer.parseInt(req.getParameter("facultyYear")));
			
			// TODO: ADD IMAGE SAVING IN DATABASE 
			
			if(studentBean.update()) {
				
				System.out.println("SUCCESS");
			}
			else {
				System.out.println("UNSUCCESS");
			}
					
			//System.out.println(test);
			
		} else if (action.equals("main")) {
			
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(address);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}