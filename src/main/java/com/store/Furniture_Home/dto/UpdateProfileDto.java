package com.store.Furniture_Home.dto;

public class UpdateProfileDto {
    private String name;
    private String email;
    private String password;
    private int phone;
    public UpdateProfileDto(String name, String email, String password, int phone, String oldPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public int getPhone() {
        return phone;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
}
