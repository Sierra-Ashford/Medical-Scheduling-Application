package com.techelevator.dao;

import com.techelevator.model.Doctor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDoctorDao implements DoctorDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcDoctorDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
        while (row.next()) {
            doctors.add(mapRowToDoctor(row));
        }
        return doctors;
    }

    //tested in postman
    @Override
    public Doctor getDoctorById(int doctorId) {
        Doctor doctor = null;
        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, doctorId);
        if (row.next()) {
            doctor = mapRowToDoctor(row);
        }
        return doctor;
    }

    //tested in postman
        @Override
        public Doctor createDoctor(Doctor newDoctor) {
            String sql = "INSERT INTO doctors (user_id, first_name, last_name, specialty, cost_per_hr, headshot) VALUES (?, ?, ?, ?, ?, ?) RETURNING doctor_id;";
            int createdDoctorId = jdbcTemplate.queryForObject(sql, Integer.class, newDoctor.getUserId(),
                    newDoctor.getFirstName(), newDoctor.getLastName(), newDoctor.getSpecialty(), newDoctor.getSpecialty(), newDoctor.getCostPerHour());

            newDoctor.setDoctorId(createdDoctorId);

            return newDoctor;
        }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        String sql = "UPDATE doctors SET user_id = ?, first_name = ?, last_name = ?, specialty = ? , cost_per_hr = ?,  headshot = ? WHERE doctor_id = ?";
        jdbcTemplate.update(sql, doctor.getUserId(), doctor.getFirstName(), doctor.getLastName(), doctor.getSpecialty(), doctor.getCostPerHour(), doctor.getHeadshot(), doctor.getDoctorId());

        return getDoctorById(doctor.getDoctorId());
    }

    @Override
    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctors WHERE doctor_id = ?";
        jdbcTemplate.update(sql, doctorId);
    }

    public Doctor mapRowToDoctor(SqlRowSet row){
        Doctor doctor = new Doctor();

        doctor.setDoctorId(row.getInt("doctor_id"));
        doctor.setUserId(row.getInt("user_id"));
        doctor.setFirstName(row.getString("first_name"));
        doctor.setLastName(row.getString("last_name"));
        doctor.setSpecialty(row.getString("specialty"));
        doctor.setCostPerHour(row.getDouble("cost_per_hr"));
        doctor.setHeadshot(row.getString("headshot"));


        return doctor;
    }
}
