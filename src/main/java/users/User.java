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
    private String name;
    private String pass;
    private String email;
    private String tel;
    private String nic;
    
    public User() {
        this.id = -1;
        this.name = "";
        this.pass = "";
        this.email = "";
        this.tel = "";
        this.nic = "";
    }

    public User(int id, String name, String pass, String email, String tel, String nic) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.tel = tel;
        this.nic = nic;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
