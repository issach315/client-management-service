package com.clientmanagement.dto.request;

public class ClientFilterParams {

    private String clientName;
    private String email;
    private String phoneNumber;
    private String industry;
    private String status;
    private String location;

    // default constructor + getters/setters (or use a record in Java 16+)
    public ClientFilterParams() {
    }

    public String getClientName() {
        return clientName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIndustry() {
        return industry;
    }

    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public void setClientName(String v) {
        this.clientName = v;
    }

    public void setEmail(String v) {
        this.email = v;
    }

    public void setPhoneNumber(String v) {
        this.phoneNumber = v;
    }

    public void setIndustry(String v) {
        this.industry = v;
    }

    public void setStatus(String v) {
        this.status = v;
    }

    public void setLocation(String v) {
        this.location = v;
    }
}