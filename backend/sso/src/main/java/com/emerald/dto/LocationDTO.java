package com.emerald.dto;

public class LocationDTO {
    private int id;
    private String phone;
    private String street;
    private String country;
    private String city;

    public LocationDTO() {
        
    }

    public LocationDTO(
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
