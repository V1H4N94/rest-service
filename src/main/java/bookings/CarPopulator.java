/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookings;

/**
 *
 * @author User
 */
public class CarPopulator {
    private int package_id;
    private int vehicle_type_id;
    
    public CarPopulator(){
        this.package_id = -1;
        this.vehicle_type_id = -1;
    
    }
    
    public int getPackageId(){
        return package_id;
    }
    
    public void setPackageId(int package_id){
        this.package_id = package_id;
    }
    
    public int getVehicleType(){
        return vehicle_type_id;
    }
    
    public void setVehicleType(int vehicle_type_id){
        this.vehicle_type_id = vehicle_type_id;
    }
}
