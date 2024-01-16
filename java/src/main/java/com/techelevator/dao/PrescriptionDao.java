package com.techelevator.dao;

import com.techelevator.model.Prescription;

import java.util.List;

public interface PrescriptionDao {
    List<Prescription> getPrescriptionsByPatientId(int patientId);
    Prescription getPrescriptionById(int prescriptionId);

}
