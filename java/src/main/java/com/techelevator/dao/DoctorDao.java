package com.techelevator.dao;

import com.techelevator.model.Doctor;

import java.util.List;

public interface DoctorDao {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(int doctorId);
    public Doctor createDoctor(Doctor newDoctor);
    Doctor updateDoctor(Doctor doctor);
    void deleteDoctor(int doctorId);
}
