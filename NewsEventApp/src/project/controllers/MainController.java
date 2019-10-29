package project.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class MainController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 697190419710671301L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String nextPage = "main.xhtml";
		
		
		if("create_news".equals(action)) {
			nextPage = "createNews.xhtml";
		}
		else if("create_event".equals(action)) {
			nextPage = "createEvent.xhtml";
		}
		else {
			nextPage = "main.xhtml";
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(nextPage);
		dispatcher.forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}
	
}
