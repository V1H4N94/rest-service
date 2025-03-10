/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookings;

/**
 *
 * @author User
 */
public class populator {
    private int package_id;
    private String package_name;
    private String description;
    private double price;
    
    public populator(){
        this.package_id = -1;
        this.package_name = "";
        this.description = "";
        this.price = -1.0;
    }
    
    public int getId() {
        return package_id;
    }

    public void setId(int package_id) {
        this.package_id = package_id;
    }
    
    public String getName() {
        return package_name;
    }

    public void setName(String package_name) {
        this.package_name = package_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
