package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.Post;

@Path("/posts")
public class APIPostService {

	PostService postService = new PostService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Post post) {
		System.out.println("test");
		if(postService.insert(post)) {
			return Response.status(200).entity(post).build();
		}
		else {
			return Response.status(500).entity("Greska u dodavanju").build();
		}
	}
	
	@GET
	public String get() {
		return "test";
	}
	
	
}
