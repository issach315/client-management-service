package com.clientmanagement.dto.request;

public class ClientSearchRequest {

    private String clientName;
    private String email;
    private String phoneNumber;
    private String industry;
    private String status;
    private String location;

    public ClientSearchRequest() {
    }

    public ClientSearchRequest(String clientName, String email, String phoneNumber, String industry, String status, String location) {
        this.clientName = clientName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.industry = industry;
        this.status = status;
        this.location = location;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
