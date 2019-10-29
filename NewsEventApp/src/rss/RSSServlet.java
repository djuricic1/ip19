package rss;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedOutput;

import dao.EventDao;
import daoimpl.EventDaoImpl;
import dto.Event;

@WebServlet("/rss")
public class RSSServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("Events");
		feed.setDescription("System provided events");
		feed.setLink("https://etf.unibl.org");
		
		List<SyndEntry> entries = new ArrayList<>();
		EventDao eventDao = new EventDaoImpl();
		
		ArrayList<Event> events = eventDao.getAll();
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		for(Event event : events) {
			SyndEntry item = new SyndEntryImpl();
			item.setTitle(event.getTitle());
			SyndContent description = new SyndContentImpl();
			description.setType("text/html");
			StringBuilder descriptionBuilder = new StringBuilder();
			descriptionBuilder.append(event.getDescription());
			descriptionBuilder.append("<br/>");
			descriptionBuilder.append("Događaj traje u periodu od ");
			descriptionBuilder.append(new SimpleDateFormat("dd.MM.yyyy").format(new Date(event.getStartOn())));
			descriptionBuilder.append(" do ");
			descriptionBuilder.append(new SimpleDateFormat("dd.MM.yyyy").format(new Date(event.getEndsOn())));
			description.setValue(descriptionBuilder.toString());
			item.setDescription(description);
			List<SyndCategory> categories = new ArrayList<>();
			SyndCategory category = new SyndCategoryImpl();
			category.setName(event.getCategory());
			categories.add(category);
			item.setCategories(categories);
			item.setLink(event.getImageUrl());
			SyndContent ratings = new SyndContentImpl();
			ratings.setType("application/json");
			ratings.setValue(gson.toJson(event.getNumberOfLikes()) + "###" + gson.toJson(event.getNumberOfDislikes()));
			List<SyndContent> contents = new ArrayList<>();
			contents.add(ratings);
			item.setContents(contents);
			item.setAuthor(String.valueOf(event.getId()));
			
			entries.add(item);
		}
		
		feed.setEntries(entries);
		try {
			resp.getWriter().println(new SyndFeedOutput().outputString(feed));
		} catch (Exception ex) {
			ex.printStackTrace();
			resp.sendError(500);
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		doGet(req, resp);
	}
}
