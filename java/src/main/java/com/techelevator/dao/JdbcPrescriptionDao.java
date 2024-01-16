package com.techelevator.dao;

import com.techelevator.model.Prescription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPrescriptionDao implements PrescriptionDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcPrescriptionDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Prescription mapRowToPrescription(SqlRowSet row){
        Prescription prescription = new Prescription();

        prescription.setPrescriptionId(row.getInt("prescription_id"));
        prescription.setPatientId(row.getInt("patient_id"));
        prescription.setName(row.getString("name"));
        prescription.setCost(row.getInt("cost"));

        return prescription;
    }

    @Override
    public List<Prescription> getPrescriptionsByPatientId(int patientId) {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions WHERE patient_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, patientId);
        while (row.next()) {
            prescriptions.add(mapRowToPrescription(row));
        }
        return prescriptions;
    }

    @Override
    public Prescription getPrescriptionById(int prescriptionId) {
        Prescription prescription = null;
        String sql = "SELECT * FROM review WHERE prescription_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, prescriptionId);
        if (row.next()) {
            prescription = mapRowToPrescription(row);
        }
        return prescription;
    }
}
