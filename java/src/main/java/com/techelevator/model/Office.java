package com.techelevator.model;

public class Office {
    private int officeId;
    private String name;
    private String address;
    private String phoneNumber;
    private int officeHr;

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getOfficeHr() {
        return officeHr;
    }

    public void setOfficeHr(int officeHr) {
        this.officeHr = officeHr;
    }
}
