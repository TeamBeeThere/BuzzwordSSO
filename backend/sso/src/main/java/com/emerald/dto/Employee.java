package com.emerald.dto;

public class Employee {
    private int id;
    private int userId;
    private String firstName;
    private String lastName;
    private int department;
    private String email;
    private int locationId;

    public Employee(
        int id,
        int userId,
        String firstName,
        String lastName, 
        int department, 
        String email, 
        int locationId
    ) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
        this.locationId = locationId;
    }
}
