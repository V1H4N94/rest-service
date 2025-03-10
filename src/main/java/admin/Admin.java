/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

/**
 *
 * @author User
 */
public class Admin {
    private int id;
    private String user;
    private String pass;
    private String name;

    public Admin() {
        this.id = -1;
        this.user = "";
        this.pass = "";
        this.name = "";
    }

    public Admin(int id, String user, String pass, String name) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.name = name;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}

