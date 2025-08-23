package com.store.Furniture_Home.dto;

public class LoginResponseDto {
    private String token;
    private String message;
    private String userEmail;
    private String userRole;

    public LoginResponseDto() {}

    public LoginResponseDto(String token, String message, String userEmail, String userRole) {
        this.token = token;
        this.message = message;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
