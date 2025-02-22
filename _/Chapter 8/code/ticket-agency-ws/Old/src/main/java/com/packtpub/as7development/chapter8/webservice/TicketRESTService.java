package com.packtpub.as7development.chapter8.webservice;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.packtpub.as7development.chapter8.ejb.TheatreBox;
import com.packtpub.as7development.chapter8.model.Seat;


@Path("/ticket")
@RequestScoped
public class TicketRESTService {

	//@Inject
	//private Logger log;

	@Inject
	TheatreBox service;


	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Seat> getSeatList() {
		return service.getSeatList();
	}

	@GET
	@Path("/book/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response buyTicket(@PathParam("id") int id) {
		Response.ResponseBuilder builder = null;
		try {		
			service.buyTicket(id);
			builder = Response.ok("Ticket booked");
			//log.info("Ticket booked "+id);
		}
		catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();

	}



}
