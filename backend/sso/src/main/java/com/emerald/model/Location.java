package com.emerald.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Locations")
public class Location {
    @Id
    private int id;
    private String phone;
    private String street;
    private String country;
    private String city;

    public Location() {
    }

    public Location(
        int id,
        String phone,
        String street,
        String country,
        String city
    ) {
        this.id = id;
        this.phone = phone;
        this.street = street;
        this.country = country;
        this.city = city;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
