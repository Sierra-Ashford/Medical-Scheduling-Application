package com.techelevator.dao;

import com.techelevator.model.Appointment;

import java.util.List;

public interface AppointmentDao {

    public List<Appointment> getAllAppointmentByDoctorId(int doctorId);
    public List<Appointment> getAllAppointmentByPatientId(int patientId);
    public Appointment getAppointmentById(int appointmentId);
    public Appointment createNewAppointment(Appointment appointment);
    public Appointment updateAppointment(Appointment appointment);
    public void deleteAppointment(int appointmentId);

    List<Appointment> getAllAppointments();
}
