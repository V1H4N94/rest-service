/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.service.resources;

import com.google.gson.Gson;
import bookings.PopulatorUtils;
import bookings.populator;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */

@Path("packages")
public class PopulatorService {
@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPackages() {
        PopulatorUtils utils = new PopulatorUtils();
        List<populator> packages = utils.getPackages();
        Gson gson = new Gson();
        String json = gson.toJson(packages);
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
    public Response getPackage(@PathParam("id") int id) {
        PopulatorUtils utils = new PopulatorUtils();
        
        try {
            populator packageObj = utils.getPackage(id);
            if (packageObj == null) {
                return Response
                    .status(404)
                    .build();
            } else {
                Gson gson = new Gson();
                return Response
                    .status(200)
                    .entity(gson.toJson(packageObj))
                    .build();
            }
        } catch(SQLException e) {
            return Response
                .status(500)
                .build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPackage(String json) {
        Gson gson = new Gson();
        populator packageObj = gson.fromJson(json, populator.class);
        PopulatorUtils utils = new PopulatorUtils();
        boolean res = utils.addPackage(packageObj);
        
        if (res) {
            return Response
                .status(201)
                .build();
        } else {
            return Response
                .status(500)
                .build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePackage(String json) {
        Gson gson = new Gson();
        populator packageObj = gson.fromJson(json, populator.class);
        PopulatorUtils utils = new PopulatorUtils();
        boolean res = utils.updatePackage(packageObj);
        
        if (res) {
            return Response
                .status(200)
                .build();
        } else {
            return Response
                .status(500)
                .build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response deletePackage(@PathParam("id") int id) {
        PopulatorUtils utils = new PopulatorUtils();
        boolean res = utils.deletePackage(id);
        
        if (res) {
            return Response
                .status(200)
                .build();
        } else {
            return Response
                .status(500)
                .build();
        }
    }
}