package com.techelevator.dao;

import com.techelevator.model.Doctor;
import com.techelevator.model.Patient;

import java.util.List;

public interface PatientDao {

    List<Patient> getAllPatients();

    Patient getPatientById(int patientId);

    public Patient createPatient(Patient newPatient);
    Patient updatePatient(Patient patient);

}
