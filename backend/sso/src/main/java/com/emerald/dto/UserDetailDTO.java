package com.emerald.dto;

public class UserDetailDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String department;
    private String title;
    private String location;

    public UserDetailDTO() {
        
    }

    public UserDetailDTO(
        String userName,
        String firstName,
        String lastName, 
        String department,
        String title,
        String location
    ) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.title = title;
        this.location = location;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
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

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
