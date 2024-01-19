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
        prescription.setDoctorId(row.getInt("doctor_id"));
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
        String sql = "SELECT * FROM prescriptions WHERE prescription_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, prescriptionId);
        if (row.next()) {
            prescription = mapRowToPrescription(row);
        }
        return prescription;
    }

    @Override
    public Prescription createPrescription(Prescription prescription) {
        String sql = "INSERT INTO prescriptions (patient_id, doctor_id, name, cost) VALUES (?, ?, ?, ?) RETURNING prescription_id";
        int newPrescriptionId = jdbcTemplate.queryForObject(sql, Integer.class,
                prescription.getPatientId(), prescription.getDoctorId(), prescription.getName(), prescription.getCost());

        return getPrescriptionById(newPrescriptionId);
    }

    @Override
    public void deletePrescription(int prescriptionId) {
        String sql = "DELETE FROM prescriptions WHERE prescription_id = ?";
        jdbcTemplate.update(sql, prescriptionId);
    }

    @Override
    public Prescription updatePrescription(Prescription prescription) {
        String sql = "UPDATE prescriptions SET patient_id = ?, doctor_id = ?, name = ?, cost = ? WHERE prescription_id = ?";
        jdbcTemplate.update(sql, prescription.getPatientId(), prescription.getDoctorId(), prescription.getName(), prescription.getCost(), prescription.getPrescriptionId());

        return getPrescriptionById(prescription.getPrescriptionId());
    }

}
