/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.service.resources;


import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cars.*;

/**
 *
 * @author User
 */
@Path("cars")
public class CarService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars() {
        CarUtils utils = new CarUtils();
        List<Car> cars = utils.getCars();
        Gson gson = new Gson();
        return Response.status(200).entity(gson.toJson(cars)).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCar(@PathParam("id") int id) {
        CarUtils utils = new CarUtils();
        try {
            Car car = utils.getCar(id);
            if (car == null) {
                return Response.status(404).build();
            }
            Gson gson = new Gson();
            return Response.status(200).entity(gson.toJson(car)).build();
        } catch (SQLException e) {
            return Response.status(500).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCar(String json) {
        Gson gson = new Gson();
        Car car = gson.fromJson(json, Car.class);
        CarUtils utils = new CarUtils();
        boolean res = utils.addCar(car);
        return res ? Response.status(201).build() : Response.status(500).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(String json) {
        Gson gson = new Gson();
        Car car = gson.fromJson(json, Car.class);
        CarUtils utils = new CarUtils();
        boolean res = utils.updateCar(car);
        return res ? Response.status(200).build() : Response.status(500).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteCar(@PathParam("id") int id) {
        CarUtils utils = new CarUtils();
        boolean res = utils.deleteCar(id);
        return res ? Response.status(200).build() : Response.status(500).build();
    }
}

