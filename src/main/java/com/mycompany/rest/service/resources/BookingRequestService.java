/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.service.resources;

import com.google.gson.Gson;
import bookings.bookingRequestUtils;
import bookings.bookingRequest;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author User
 */
@Path("bookingRequests")
public class BookingRequestService {

    private bookingRequestUtils utils = new bookingRequestUtils();
    private Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequests() {
        List<bookingRequest> requests = utils.getAllRequests();
        String json = gson.toJson(requests);
        System.out.println("Returning JSON: " + json); // Debug log
        return Response
                .status(200)
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
                return Response.status(404).build();
            } else {
                return Response.status(200).entity(gson.toJson(request)).build();
            }
        } catch (SQLException e) {
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRequest(String json) {
        bookingRequest request = gson.fromJson(json, bookingRequest.class);
        boolean res = utils.addRequest(request);

        if (res) {
            return Response.status(201).build();
        } else {
            return Response.status(500).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRequest(String json) {
        bookingRequest request = gson.fromJson(json, bookingRequest.class);
        boolean res = utils.updateRequest(request);

        if (res) {
            return Response.status(200).build();
        } else {
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteRequest(@PathParam("id") int id) {
        boolean res = utils.deleteRequest(id);

        if (res) {
            return Response.status(200).build();
        } else {
            return Response.status(500).build();
        }
    }
}
