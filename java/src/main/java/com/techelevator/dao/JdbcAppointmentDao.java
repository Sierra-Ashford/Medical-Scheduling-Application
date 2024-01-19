package com.techelevator.dao;

import com.techelevator.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAppointmentDao implements AppointmentDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcAppointmentDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Appointment mapRowToAppointment(SqlRowSet row){
        Appointment appointment = new Appointment();

        appointment.setAppointmentId(row.getInt("appointment_id"));
        appointment.setDoctorId(row.getInt("doctor_id"));
        appointment.setPatientId(row.getInt("patient_id"));
        appointment.setAppointmentStartTime(LocalDateTime.parse(row.getString ("appointment_start_time").replace(' ', 'T')));
        appointment.setAppointmentEndTime(LocalDateTime.parse(row.getString ("appointment_end_time").replace(' ', 'T')));
        appointment.setNotes(row.getString("notes"));

        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
        while (row.next()) {
            appointments.add(mapRowToAppointment(row));
        }
        return appointments;
    }


    @Override
    public List<Appointment> getAllAppointmentByDoctorId(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE doctor_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, doctorId);
        while (row.next()) {
            appointments.add(mapRowToAppointment(row));
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAllAppointmentByPatientId(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE patient_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, patientId);
        while (row.next()) {
            appointments.add(mapRowToAppointment(row));
        }
        return appointments;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, appointmentId);
        if (row.next()) {
            appointment = mapRowToAppointment(row);
        }
        return appointment;
    }

    @Override
    public Appointment createNewAppointment() {
        return null;
    }

    @Override
    public Appointment updateAppointment(int appointmentId) {
        return null;
    }

    @Override
    public Appointment deleteAppointment(int appointmentId) {
        return null;
    }
}
