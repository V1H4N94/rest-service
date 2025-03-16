package com.mycompany.rest.service.resources;

import com.google.gson.Gson;
import bookings.bookingRequestUtils;
import bookings.bookingRequest;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("bookingRequests")
public class BookingRequestService {

    private bookingRequestUtils utils = new bookingRequestUtils();
    private Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequests() {
        List<bookingRequest> requests = utils.getAllRequests();
        String json = gson.toJson(requests);
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(json)
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequest(@PathParam("id") int id) {
        try {
            bookingRequest request = utils.getRequest(id);
            if (request == null) {
                return Response.status(404).entity("{\"error\":\"Request not found\"}").build();
            }
            return Response.status(200).entity(gson.toJson(request)).build();
        } catch (SQLException e) {
            return Response.status(500).entity("{\"error\":\"Database error\"}").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRequest(String json) {
        bookingRequest request = gson.fromJson(json, bookingRequest.class);
        boolean res = utils.addRequest(request);

        if (res) {
            return Response.status(201).entity("{\"message\":\"Request added successfully\"}").build();
        }
        return Response.status(500).entity("{\"error\":\"Failed to add request\"}").build();
    }
    
    @POST
    @Path("/approve/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveRequest(@PathParam("id") int id, String json) {
        bookingRequest request = gson.fromJson(json, bookingRequest.class);
        request.setId(id);  // Ensure ID is set
        boolean res = utils.updateRequest(request);

        if (res) {
            return Response.ok("{\"message\":\"Request updated successfully\"}").build();
        }
        return Response.status(500).entity("{\"error\":\"Failed to update request\"}").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRequest(String json) {
        bookingRequest request = gson.fromJson(json, bookingRequest.class);
        boolean res = utils.updateRequest(request);

        if (res) {
            return Response.status(200).entity("{\"message\":\"Request updated successfully\"}").build();
        }
        return Response.status(500).entity("{\"error\":\"Failed to update request\"}").build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRequest(@PathParam("id") int id) {
        boolean res = utils.deleteRequest(id);

        if (res) {
            return Response.status(200).entity("{\"message\":\"Request deleted successfully\"}").build();
        }
        return Response.status(500).entity("{\"error\":\"Failed to delete request\"}").build();
    }
}