package com.emerald.dto;

public class EmployeeDTO {
    private int id;
    private int userId;
    private String firstName;
    private String lastName;
    private int department;
    private String title;
    private String email;
    private int locationId;

    public EmployeeDTO(
        String firstName,
        String lastName, 
        int department,
        String title,
        String email,
        int locationId
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.title = title;
        this.email = email;
        this.locationId = locationId;
    }

    public EmployeeDTO(
        int id,
        int userId,
        String firstName,
        String lastName, 
        int department,
        String title,
        String email,
        int locationId
    ) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.title = title;
        this.email = email;
        this.locationId = locationId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 

    public String getLastName() {
        return lastName;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getDepartment() {
        return department;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }
 }
