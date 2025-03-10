/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.service.resources;

import com.google.gson.Gson;
import admin.DBUtilsAdmin;
import admin.Admin;
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

@Path("admins")
public class AdminService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmins() {
        DBUtilsAdmin utils = new DBUtilsAdmin();
        List<Admin> admins = utils.getAdmins();

        Gson gson = new Gson();
        String json = gson.toJson(admins);
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
    public Response getAdmin(@PathParam("id") int id) {
        DBUtilsAdmin utils = new DBUtilsAdmin();
        
        try {
            Admin admin = utils.getAdmin(id);
            if (admin == null) {
                return Response
                    .status(404)
                    .build();
            } else {
                Gson gson = new Gson();
                return Response
                    .status(200)
                    .entity(gson.toJson(admin))
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
    public Response addAdmin(String json) {
        Gson gson = new Gson();
        Admin admin = gson.fromJson(json, Admin.class);
        DBUtilsAdmin utils = new DBUtilsAdmin();
        boolean res = utils.addAdmin(admin);
        
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
    public Response updateAdmin(String json) {
        Gson gson = new Gson();
        Admin admin = gson.fromJson(json, Admin.class);
        DBUtilsAdmin utils = new DBUtilsAdmin();
        boolean res = utils.updateAdmin(admin);
        
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
    public Response deleteAdmin(@PathParam("id") int id) {
        DBUtilsAdmin utils = new DBUtilsAdmin();
        boolean res = utils.deleteAdmin(id);
        
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
