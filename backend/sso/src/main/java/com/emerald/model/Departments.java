package com.emerald.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Departments")
public class Departments {
    @Id
    @Column(name="ID")
    private int id;

    // private int ID;
    private String name;

    public Departments() {
    }

    public Departments(String name) {
        // this.ID = ID;
        this.name = name;
    }

    //Getters and setters
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
    
}