package project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

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
import daoimpl.BlogDaoImpl;
import daoimpl.ConnectionDaoImpl;
import daoimpl.FacultyDaoImpl;
import daoimpl.FileDaoImpl;
import daoimpl.LikeDaoImpl;
import daoimpl.PostDaoImpl;
import daoimpl.StudentDaoImpl;
import dto.Blog;
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
	private FileDaoImpl fileDaoImpl = new FileDaoImpl();
	private BlogDaoImpl bdi = new BlogDaoImpl();
	private ConnectionDaoImpl cdi = new ConnectionDaoImpl();
	
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
				if(areLoginParamValid(username, password)) {
					
					StudentBean studentBean = new StudentBean();				
					
					if(studentBean.login(username, password)) {
						session.setAttribute("studentBean", studentBean);
						address = "/WEB-INF/pages/main.jsp";
					}
					else {
						session.setAttribute("loginNotification", "Invalid username or password");
					}
				}
				
			} else if (action.equals("registration")) {
				
				String name = req.getParameter("name");
				String surname = req.getParameter("surname");
				String password = req.getParameter("password");
				String username = req.getParameter("username");
				String mail = req.getParameter("mail");
				
				String res = areRegistrationParamValid(name, surname, username, password, mail);
				
				if("".equals(res)) {
					
					StudentBean studentBean = new StudentBean();
					
					Student student = new Student(); 
					
					student.setName(name);
					student.setSurname(surname);
					student.setPassword(password);
					student.setUsername(username);
					student.setMail(mail);
					student.setImage("");
				
					studentBean.setStudent(student);
					
					if(studentBean.add(student)) {
						session.setAttribute("studentBean", studentBean);
						address = "/WEB-INF/pages/updateProfile.jsp";
					}
					else {
						address = "/login.jsp";
					}
					
				}
				else {
					session.setAttribute("registrationNotification", res);
				}
				
				
			} else if (action.equals("update")) {
				
				String name = req.getParameter("name");
				String surname = req.getParameter("surname");
				String password = req.getParameter("password");
				String username = req.getParameter("username");			
				String mail = req.getParameter("mail");
				Faculty faculty = fdi.getFacultyByName(req.getParameter("faculty"));
				
				String res = areRegistrationParamValid(name, surname, username, password, mail);
				if("".equals(res) && faculty!=null) {
					
			
					
					StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
					
					studentBean.getStudent().setName(name);
					studentBean.getStudent().setSurname(surname);
					studentBean.getStudent().setPassword(password);
					studentBean.getStudent().setUsername(username);
					studentBean.getStudent().setMail(mail);
					
					studentBean.getStudent().setDescription(req.getParameter("description"));
					studentBean.getStudent().setStudyProgram(req.getParameter("studyProgram"));
					
					studentBean.getStudent().setFaculty(faculty);
					studentBean.getStudent().setFacultyYear(Integer.parseInt(req.getParameter("facultyYear")));
	
					Part filePart = req.getPart("file");
					
					try {
						// file saving 
						InputStream fileContent = filePart.getInputStream();					
						byte[] buffer = new byte[fileContent.available()];
						fileContent.read(buffer);		
						String webRootPath = getWebRootPath();
						
						String uploadPath = webRootPath + "assets\\img\\userImg\\" + username + "_" + filePart.getSubmittedFileName();
						
						File targetFile = new File(uploadPath);
						
						OutputStream outStream = new FileOutputStream(targetFile);
						outStream.write(buffer);
						outStream.close();
					} catch (IOException ioe) {
						// TODO: handle exception
						ioe.printStackTrace();
					} 
					
					
					address = "/WEB-INF/pages/updateProfile.jsp";
					if(!"".equals( filePart.getSubmittedFileName()))
						studentBean.getStudent().setImage("/assets/img/userImg/" +  username + "_" + filePart.getSubmittedFileName());
					//System.out.println(studentBean.getStudent().getImage());
					if(!studentBean.update()) {
						System.out.println("Database problem");
						session.setAttribute("updateNotification", "Error updating your profile");
					}
					
				}
				else {
					session.setAttribute("updateNotification", res);
					address = "/WEB-INF/pages/updateProfile.jsp";
				}		
				
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
				
				if("".equals(description) && "".equals(linkPost)) {
					resp.setStatus(403);
					resp.setContentType("text/plain");
					resp.getWriter().write("Invalid post data");
					return;
				}
				
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
			
				
			} else if (action.equals("addFile")) {
		
				
				StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
				int studentId = studentBean.getStudent().getId();
				String description = req.getParameter("fileDescription");
				
				// file saving 
				Part filePart = req.getPart("file");
				InputStream fileContent = filePart.getInputStream();
				if("".equals(filePart.getSubmittedFileName())) {
					
					session.setAttribute("addFileNtf", "Please choose file");
					//address = "/WEB-INF/pages/main.jsp";
				
				}
				else {
				
					try {
						byte[] buffer = new byte[fileContent.available()];
						fileContent.read(buffer);
						
						String root = getWebRootPath();
						
						String uploadPath = root + "assets\\files\\" + filePart.getSubmittedFileName();
						File targetFile = new File(uploadPath);
						
						OutputStream outStream = new FileOutputStream(targetFile);
						outStream.write(buffer);
					} catch (IOException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
					dto.File file = new dto.File();
					if("".equals(description)) {
						file.setDescription("No file description");
					}
					else 
						file.setDescription(description);
					
					file.setPath("/assets/files/" + filePart.getSubmittedFileName());
					file.setStudentId(studentId);
					
					if(!fileDaoImpl.insertFile(file)) {
						session.setAttribute("addFileNtf", "Err saving Your file on server");	
					}
					else {
						session.setAttribute("addFileNtf", "Succesful");
					}
					
				}
				address = "/WEB-INF/pages/main.jsp";
				
			} else if (action.equals("addBlog")) {
				
				StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
				int studentId = studentBean.getStudent().getId();
				String title = req.getParameter("title");
				String blogDescription = req.getParameter("blogDescription");
				
				if("".equals(title) || "".equals(blogDescription)) {
					session.setAttribute("addBlogNtf", "Please add title and description of your blog.");
				}
				else {
					session.setAttribute("addBlogNtf", "Succesful");
					Blog blog = new Blog();
					
					blog.setDateCreated(new Date(System.currentTimeMillis()));
					blog.setContent(blogDescription);
					blog.setStudentId(studentId);
					blog.setTitle(title);
					
					bdi.insertBlog(blog);
					
				}
				address = "/WEB-INF/pages/main.jsp";
				
				
			} else if (action.equals("addComment")) {
				StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
				int studentId = studentBean.getStudent().getId();
				String blogComment = req.getParameter("blogComment");
				bdi.addComment(req.getParameter("blogId"), blogComment, studentId);
				address = "/WEB-INF/pages/main.jsp";
			} else if(action.equals("connections")) {
				address = "/WEB-INF/pages/connection.jsp";
			} else if(action.equals("sendConnectionRequest")) {
				address = "/WEB-INF/pages/connection.jsp";
				BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
				String json = "";
				if(br != null){
					json = br.readLine();
					System.out.println(json);
				}
				
				JSONObject obj = new JSONObject(json);
				int senderId = obj.getInt("senderId");
				int recieverId  = obj.getInt("recieverId");
				if(cdi.insertConnection(senderId, recieverId, 1))
					resp.setStatus(200);
				else {
					System.out.println("ERR");
				}
				return;
				
			} else if(action.equals("acceptConnectionRequest")) {
				address = "/WEB-INF/pages/connection.jsp";
				BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
				String json = "";
				if(br != null){
					json = br.readLine();
					System.out.println(json);
				}
				
				JSONObject obj = new JSONObject(json);
				int senderId = obj.getInt("senderId");
				int accepterId  = Integer.parseInt(obj.getString("accepterId"));
				int accept = obj.getInt("accept");
				
				
				
				if(accept==1) {
					cdi.acceptConnection(senderId, accepterId);
				 }
				else {
					cdi.deleteConnection(senderId, accepterId);
				}
				resp.setStatus(200);
			} else if(action.equals("deleteConnection")) {
				address = "/WEB-INF/pages/connection.jsp";
				BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
				String json = "";
				if(br != null){
					json = br.readLine();
					System.out.println(json);
				}
				
				JSONObject obj = new JSONObject(json);
				int senderId = obj.getInt("senderId");
				int accepterId  = obj.getInt("accepterId");
				
				cdi.deleteConnection(senderId, accepterId);
				cdi.deleteConnection(accepterId, senderId);
			}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(address);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	private boolean areLoginParamValid(String username, String password) {
		if("".equals(username) || "".equals(password))
			return false;
		return true;
	}
	
	private String areRegistrationParamValid(String name, String surname, String username, String password, String mail) {
		
		if("".equals(name) || name.length() < 2)
			return "Invalid name";
		
		if("".equals(surname) || surname.length() < 2)
			return "Invalid surname";
		
		if("".equals(username))
			if( username.length() < 4)			
				return "Invalid username";
		else if(sdi.doesUsernameExist(username)) {
			return "Username already exist";
		}		
		
		if("".equals(password) || password.length() < 4)
			return "Invalid password";
		
		if("".equals(mail) || mail.length() < 5)
			return "Invalid mail";
		
		return "";
	}
	
	private String getWebRootPath() {
		 ResourceBundle bundle =
			      PropertyResourceBundle.getBundle("daoimpl.ConnectionPool");
		 String result = bundle.getString("webRootFolder");		 
		 return result;
	}
	
	private boolean saveProfilePicture(InputStream image) {
		
		return true;
	}
	
}