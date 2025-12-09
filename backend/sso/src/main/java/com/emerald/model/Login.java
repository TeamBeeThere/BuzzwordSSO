package com.emerald.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="LoginID")
    private int id;

    @Column(name="userID")
    private int userID;

    @Column(name="PasswordHash")
    private String PasswordHash;

    public Login() {
    }

    public Login(int userID, String PasswordHash) {
        this.userID = userID;
        this.PasswordHash = PasswordHash;
    }

    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String PasswordHash) {
        this.PasswordHash = PasswordHash;
    }

}