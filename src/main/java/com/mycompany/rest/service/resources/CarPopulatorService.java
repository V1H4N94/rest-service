package com.mycompany.rest.service.resources;

import com.google.gson.Gson;
import bookings.CarPopulator;
import bookings.CarPopulatorUtils;
import bookings.VehicleType;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
@Path("vehicles")
public class CarPopulatorService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVehiclePackages() {
        try {
            CarPopulatorUtils utils = new CarPopulatorUtils();
            List<CarPopulator> packageVehicles = utils.getAllPackageVehicles();
            
            if (packageVehicles.isEmpty()) {
                return Response
                    .status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"error\": \"No package-vehicle relationships found\"}")
                    .build();
            }
            
            Gson gson = new Gson();
            String json = gson.toJson(packageVehicles);
            System.out.println("Returning all package-vehicle relationships: " + json); // Debug log
            
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(json)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                .status(500)
                .header("Access-Control-Allow-Origin", "*")
                .entity("{\"error\": \"Server error: " + e.getMessage() + "\"}")
                .build();
        }
    }
    
    @GET
    @Path("package/{packageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehiclesByPackage(@PathParam("packageId") int packageId) {
        CarPopulatorUtils utils = new CarPopulatorUtils();
        
        try {
            List<VehicleType> vehicleTypes = utils.getVehiclesByPackage(packageId);
            if (vehicleTypes.isEmpty()) {
                return Response
                    .status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"error\": \"No vehicle types found for package " + packageId + "\"}")
                    .build();
            } else {
                Gson gson = new Gson();
                String json = gson.toJson(vehicleTypes);
                System.out.println("Returning vehicles for package " + packageId + ": " + json); // Debug log
                
                return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(json)
                    .build();
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return Response
                .status(500)
                .header("Access-Control-Allow-Origin", "*")
                .entity("{\"error\": \"Database error: " + e.getMessage() + "\"}")
                .build();
        }
    }
    
    // New endpoint to get all data needed for the frontend
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllData() {
        try {
            CarPopulatorUtils utils = new CarPopulatorUtils();
            String json = utils.getAllPackagesAndVehicles();
            
            return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(json)
                .build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response
                .status(500)
                .header("Access-Control-Allow-Origin", "*")
                .entity("{\"error\": \"Server error: " + e.getMessage() + "\"}")
                .build();
        }
    }
}