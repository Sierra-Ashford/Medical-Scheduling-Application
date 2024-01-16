package com.techelevator.dao;

import com.techelevator.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPatientDao implements PatientDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcPatientDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
        while (row.next()) {
            patientList.add(mapRowToPatient(row));
        }
        return patientList;
    }

    @Override
    public Patient getPatientById(int patientId) {
        Patient myPatient = null;
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, patientId);
        if (row.next()) {
            myPatient = mapRowToPatient(row);
        }
        return myPatient;
    }
    public Patient mapRowToPatient(SqlRowSet row){
        Patient myPatient = new Patient();

        myPatient.setPatientId(row.getInt("patient_id"));
        myPatient.setUserId(row.getInt("user_id"));
        myPatient.setFirstName(row.getString("first_name"));
        myPatient.setLastName(row.getString("last_name"));
        myPatient.setBirthDate(row.getDate("birthdate").toLocalDate());
        myPatient.setPhoneNumber(row.getString("phone_number"));
        myPatient.setEmail(row.getString("email"));

        return myPatient;
    }
}
