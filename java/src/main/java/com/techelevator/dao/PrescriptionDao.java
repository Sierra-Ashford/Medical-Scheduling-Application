package com.techelevator.dao;

import com.techelevator.model.Prescription;
import com.techelevator.model.Review;

import java.util.List;

public interface PrescriptionDao {
    List<Prescription> getPrescriptionsByPatientId(int patientId);
    Prescription getPrescriptionById(int prescriptionId);

    Prescription createPrescription(Prescription prescription);
    void deletePrescription(int prescriptionId);

    Prescription updatePrescription(Prescription prescription);

}
