package com.store.Furniture_Home.dto;

public class ResetPasswordDto {
    private String email;
    private String oldPassword;
    private String newPassword;

    public ResetPasswordDto(String email, String oldPassword, String newPassword) {
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }
    public String getOldPassword() {
        return oldPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
