package com.emerald.dto;

public class LoginDTO {
    private int loginId;
    private int userId;
    private String password;

    public LoginDTO(int loginId, int userId, String password) {
        this.loginId = loginId;
        this.userId = userId;
        this.password = password;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
