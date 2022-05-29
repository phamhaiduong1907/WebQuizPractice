/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Account {
    private String username;
    private String password;
    private Role role;
    private ArrayList<Feature> inactiveFeatures;

    public Account() {
        inactiveFeatures = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ArrayList<Feature> getInactiveFeatures() {
        return inactiveFeatures;
    }

    public void setInactiveFeatures(ArrayList<Feature> inactiveFeatures) {
        this.inactiveFeatures = inactiveFeatures;
    }
    
}
