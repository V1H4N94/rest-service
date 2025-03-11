/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;

/**
 *
 * @author User
 */
public class User {
    private int id;
    private String fullName;
    private String pass;
    private String email;
    private String tel;
    private String identity;
    
    public User() {
        this.id = -1;
        this.fullName = "";
        this.pass = "";
        this.email = "";
        this.tel = "";
        this.identity = "";
    }

    public User(int id, String fullName, String pass, String email, String tel, String identity) {
        this.id = id;
        this.fullName = fullName;
        this.pass = pass;
        this.email = email;
        this.tel = tel;
        this.identity = identity;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }    

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
