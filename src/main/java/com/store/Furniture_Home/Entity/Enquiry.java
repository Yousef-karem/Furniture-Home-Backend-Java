package com.store.Furniture_Home.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "enquiry")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;
    private String message;

    public Enquiry() {}

    public Enquiry(Long id, String customerEmail, String message) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
