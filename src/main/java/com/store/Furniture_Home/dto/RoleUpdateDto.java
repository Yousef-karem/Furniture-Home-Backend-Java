package com.store.Furniture_Home.dto;

public class RoleUpdateDto {
    private String userEmail;
    private String newRole;

    public RoleUpdateDto() {}

    public RoleUpdateDto(String userEmail, String newRole) {
        this.userEmail = userEmail;
        this.newRole = newRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
}
