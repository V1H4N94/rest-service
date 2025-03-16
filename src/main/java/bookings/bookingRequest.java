/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookings;

/**
 *
 * @author User
 */
public class bookingRequest {
    private int id;
    private String email;
    private String packageType;
    private String date;
    private String location;
    private String carType;
    private String status;
    private double payment;

    public bookingRequest() {
        this.id = -1;
        this.email = "";
        this.packageType = "";
        this.date = "";
        this.location = "";
        this.carType = "";
        this.status = "";
        this.payment = 0.0;
    }

    public bookingRequest(int id, String email, String packageType, String date, String location, String carType, String status, double payment) {
        this.id = id;
        this.email = email;
        this.packageType = packageType;
        this.date = date;
        this.location = location;
        this.carType = carType;
        this.status = status;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
