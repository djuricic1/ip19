package services.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.News;
import dto.NewsLike;
import services.NewsService;

@Path("/news")
public class APINewsService {
	
	NewsService newsService = new NewsService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addrating")
	public Response addRating(NewsLike newsLike) {
		if(newsLike.getNewsId() != 0 && newsLike.getStudentId() != 0 && newsLike.getType() != 0 || newsLike.getType() != 1) {
			newsService.addRating(newsLike);
			return Response.status(200).entity(newsLike).build();
		}else {
			return Response.status(500).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<News> getNews() {
		
		List<News> res = newsService.getAll();
		if(res!=null)
			return (ArrayList<News>)res;
		else 
			return null;
		
	}
	
}
