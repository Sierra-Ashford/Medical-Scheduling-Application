package com.techelevator.model;

public class Office {
    private int office_id;
    private int doctor_id;
    private String name;
    private String address;
    private String phoneNumber;
    private int costPerHr;
    private String specialty;
    private int officeHr;

    public Office() {};
    public Office(int office_id, int doctor_id, String name, String address, String phoneNumber, int costPerHr, String specialty, int officeHr) {
        this.office_id = office_id;
        this.doctor_id = doctor_id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.costPerHr = costPerHr;
        this.specialty = specialty;
        this.officeHr = officeHr;
    }

    public int getOffice_id() {
        return office_id;
    }

    public void setOffice_id(int office_id) {
        this.office_id = office_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
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

    public int getCostPerHr() {
        return costPerHr;
    }

    public void setCostPerHr(int costPerHr) {
        this.costPerHr = costPerHr;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getOfficeHr() {
        return officeHr;
    }

    public void setOfficeHr(int officeHr) {
        this.officeHr = officeHr;
    }
}
