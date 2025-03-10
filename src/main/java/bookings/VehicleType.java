/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookings;

/**
 *
 * @author User
 */
public class VehicleType {
    private int vehicleTypeId;
    private String vehicleName;
    private String description;
    
    // Default constructor
    public VehicleType() {
    }
    
    // Getters and Setters
    public int getVehicleTypeId() {
        return vehicleTypeId;
    }
    
    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
    
    public String getVehicleName() {
        return vehicleName;
    }
    
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
