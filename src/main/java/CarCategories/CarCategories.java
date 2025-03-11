/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarCategories;

/**
 *
 * @author User
 */
public class CarCategories {
    private int vehicle_type_id;
    private String vehicle_cat;
    private String description;

    public CarCategories() {
        this.vehicle_type_id = -1;
        this.vehicle_cat = "";
        this.description = "";
    }
    
    public CarCategories(int vehicle_type_id, String vehicle_cat, String description) {
        this.vehicle_type_id = vehicle_type_id;
        this.vehicle_cat = vehicle_cat;
        this.description = description;
    }
    
    public int getVehicleTypeId() {
        return vehicle_type_id;
    }

    public void setVehicleTypeId(int id) {
        this.vehicle_type_id = vehicle_type_id;
    }
    
    public String getVehicleCat() {
        return vehicle_cat;
    }

    public void setVehicleCat(String vehicle_cat) {
        this.vehicle_cat = vehicle_cat;
    }

    public String getDescription() {
        return description;
    }

    public void setPass(String description) {
        this.description = description;
    }
}
