/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cars;

/**
 *
 * @author User
 */
public class Car {
    private int id;
    private String license;
    private String owner;
    private String brand;
    private String model;
    private String category;

    public Car() {
        this.id = -1;
        this.license = "";
        this.owner = "";
        this.brand = "";
        this.model = "";
        this.category = "";
    }

    public Car(int id, String license, String owner, String brand, String model, String category) {
        this.id = id;
        this.license = license;
        this.owner = owner;
        this.brand = brand;
        this.model = model;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
   
}
