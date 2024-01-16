package com.techelevator.model;

public class Prescription {
    private int prescriptionId;
    private int patientId;
    private String name;
    private int cost;

    public Prescription() {};
    public Prescription(int prescriptionId, int patientId, String name, int cost) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.name = name;
        this.cost = cost;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
