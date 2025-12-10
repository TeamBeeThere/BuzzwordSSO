package com.emerald.dto;

public class CredentialsDTO {
    private String userName;
    private String password;

    public CredentialsDTO() {
        
    }

    public CredentialsDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
