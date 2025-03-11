/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.service.resources;

import com.google.gson.Gson;
import CarCategories.CarCategoryUtils;
import CarCategories.CarCategories;
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

@Path("carcategories")
public class CarCategoryService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarCategories() {
        CarCategoryUtils utils = new CarCategoryUtils();
        List<CarCategories> categories = utils.getCarCategories();
        Gson gson = new Gson();
        String json = gson.toJson(categories);
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
    public Response getCarCategory(@PathParam("id") int id) {
        CarCategoryUtils utils = new CarCategoryUtils();
        
        try {
            CarCategories category = utils.getCarCategory(id);
            if (category == null) {
                return Response
                    .status(404)
                    .build();
            } else {
                Gson gson = new Gson();
                return Response
                    .status(200)
                    .entity(gson.toJson(category))
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
    public Response addCarCategory(String json) {
        Gson gson = new Gson();
        CarCategories category = gson.fromJson(json, CarCategories.class);
        CarCategoryUtils utils = new CarCategoryUtils();
        boolean res = utils.addCarCategory(category);
        
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
    public Response updateCarCategory(String json) {
        Gson gson = new Gson();
        CarCategories category = gson.fromJson(json, CarCategories.class);
        CarCategoryUtils utils = new CarCategoryUtils();
        boolean res = utils.updateCarCategory(category);
        
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
    public Response deleteCarCategory(@PathParam("id") int id) {
        CarCategoryUtils utils = new CarCategoryUtils();
        boolean res = utils.deleteCarCategory(id);
        
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

