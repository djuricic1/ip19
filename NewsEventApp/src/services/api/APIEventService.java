package services.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import dto.EventLike;
import services.EventService;

@Path("/events")
public class APIEventService {
	
	EventService eventService = new EventService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addrating")
	public Response addRating(EventLike eventLike) {
		if(eventLike.getStudentId() != 0 && eventLike.getStudentId() != 0 && eventLike.getType() != 0 || eventLike.getType() != 1) {
			eventService.addRate(eventLike);
			return Response.status(200).entity(eventLike).build();
		}else {
			return Response.status(500).build();
		}
	}
	
}
