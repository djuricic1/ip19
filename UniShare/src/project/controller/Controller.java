package project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.json.JSONObject;

import beans.StudentBean;
import daoimpl.FacultyDaoImpl;
import daoimpl.LikeDaoImpl;
import daoimpl.PostDaoImpl;
import daoimpl.StudentDaoImpl;
import dto.Faculty;
import dto.Like;
import dto.Post;
import dto.Student;

@WebServlet("/Controller")
@MultipartConfig
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentDaoImpl sdi = new StudentDaoImpl();
	private FacultyDaoImpl fdi = new FacultyDaoImpl();
	private PostDaoImpl pdi = new PostDaoImpl();
	private LikeDaoImpl ldi = new LikeDaoImpl();
	private String UPLOAD_DIRECTORY = "/assets/img/userImg";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.setCharacterEncoding("UTF-8");
		String address = "/login.jsp";
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		
		
		if (action == null || action.equals("")) {
			// check this later
			if(session.getAttribute("studentBean")!=null) {
				address = "/WEB-INF/pages/main.jsp";
			}
			else {
				address = "/login.jsp";
			}
		} else if (action.equals("logout")) {
			address = "/login.jsp";
			session.invalidate();
		} else if (action.equals("toUpdate")) {
			address = "/WEB-INF/pages/updateProfile.jsp";
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

			
			// file saving 
			Part filePart = req.getPart("file");
			InputStream fileContent = filePart.getInputStream();
			
			byte[] buffer = new byte[fileContent.available()];
			fileContent.read(buffer);
			// TODO: CHANGE THIS SHIT 
			String uploadPath = "C:\\Users\\djdjuricic\\ip-projektni\\UniShare\\WebContent\\assets\\img\\userImg\\" + filePart.getSubmittedFileName();
			File targetFile = new File(uploadPath);
			
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);
			address = "/WEB-INF/pages/updateProfile.jsp";
			studentBean.getStudent().setImage(uploadPath);
			
			if(studentBean.update()) {
				System.out.println("SUCCESS");
			}
			else {
				System.out.println("UNSUCCESS");
			}
			
			//System.out.println(test);
			
		} else if (action.equals("like")) {
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
			String json = "";
			if(br != null){
				json = br.readLine();
				System.out.println(json);
			}
			
			JSONObject obj = new JSONObject(json);
			int postId = obj.getInt("postId");
			int rate  = obj.getInt("rate");
			int studentId = ((StudentBean)session.getAttribute("studentBean")).getStudent().getId();
			
			Post post = pdi.getById(postId);
			Like like = new Like();
			like.setStudentId(studentId);
			like.setPostId(postId);
			
			if(rate == 0) {					
				post.setNumberOfLikes(post.getNumberOfLikes() + 1);
				like.setType(0);
			}
			else {
				post.setNumberOfDislikes(post.getNumberOfDislikes() + 1);
				like.setType(1);
			}
			if(ldi.insertLike(like)) {
				pdi.updatePostRate(post);
			}else {
				resp.setStatus(204);
			}
			return;
			
		} else if (action.equals("main")) {
			
		} else if (action.equals("post")) {
			
		
			
			int studentId = Integer.parseInt(req.getParameter("studentId"));
			long dateCreated = Long.parseLong(req.getParameter("dateCreated"));
			String description = req.getParameter("description");
			String linkPost = req.getParameter("linkPostText");
			
			
			
			Post post = new Post();
			post.setDescription(description);
			post.setNumberOfLikes(0);
			post.setNumberOfDislikes(0);
			post.setStudentId(studentId);
			post.setDatePosted(new Date(dateCreated));
			
			String typeOfPost = "0";
			if(linkPost.equals(""))
				typeOfPost = "0";
			else if(linkPost.contains("youtube.com")) {
				typeOfPost = "2";
				linkPost = linkPost.replace("watch?v=", "embed/");
				System.out.println(linkPost);
			}
			else {
				typeOfPost = "1";
			}
			
			post.setTypeOfPost(typeOfPost);
			post.setLinkPost(linkPost);
			
			
			PostDaoImpl pdi = new PostDaoImpl();
			pdi.insertPost(post);
			
			
			return;	
		}
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(address);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	
}