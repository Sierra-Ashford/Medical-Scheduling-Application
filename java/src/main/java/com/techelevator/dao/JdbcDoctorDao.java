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

    @Override
    public Doctor getDoctorById(int doctorId) {
        Doctor doctor = null;
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, doctorId);
        if (row.next()) {
            doctor = mapRowToDoctor(row);
        }
        return doctor;
    }

    @Override
    public List<Doctor> getDoctorsByOfficeId(int officeId) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT d.doctor_id, user_id, first_name, last_name, d.specialty" +
                " FROM doctors d JOIN office o " +
                "ON d.doctor_id = o.doctor_id WHERE office_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, officeId);
        while (row.next()) {
            doctors.add(mapRowToDoctor(row));
        }
        return doctors;
    }
    public Doctor mapRowToDoctor(SqlRowSet row){
        Doctor doctor = new Doctor();

        doctor.setDoctorId(row.getInt("doctor_id"));
        doctor.setUserId(row.getInt("user_id"));
        doctor.setFirstName(row.getString("first_name"));
        doctor.setLastName(row.getString("last_name"));
        doctor.setSpecialty(row.getString("specialty"));


        return doctor;
    }
}
