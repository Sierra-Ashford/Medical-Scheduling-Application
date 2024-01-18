package com.techelevator.dao;

import com.techelevator.model.Appointment;

import java.util.List;

public interface AppointmentDao {
    List<Appointment> getAllAppointmentByDoctorId(int doctorId);
    List<Appointment> getAllAppointmentByPatientId(int patientId);
    Appointment getAppointmentById(int appointmentId);
}
