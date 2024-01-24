package com.techelevator.dao;

import com.techelevator.model.Office;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOfficeDao implements OfficeDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcOfficeDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Office createOffice(Office newOffice) {
        String sql = "INSERT INTO office ( name, address, phone_number, office_hr) VALUES ( ?, ?, ?,?) RETURNING office_id;";
        int createdOfficeId = jdbcTemplate.queryForObject(sql, Integer.class,  "MedConnect", "123 Main St. Cityville, GA",
                "777-123-8888", 8);

        newOffice.setOfficeId(createdOfficeId);

        return newOffice;
    }

    @Override
    public List<Office> getAllOffices() {
        List<Office> offices = new ArrayList<>();
        String sql = "SELECT * FROM office";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
        while (row.next()) {
            offices.add(mapRowToOffice(row));
        }
        return offices;
    }

    @Override
    public Office getOfficeById(int officeId) {
        Office office = null;
        String sql = "SELECT office_id, name, address, phone_number, office_hr FROM office WHERE office_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, officeId);
        if (row.next()) {
            office = mapRowToOffice(row);
        }
        return office;
    }
    public Office mapRowToOffice(SqlRowSet row){
        Office office = new Office();

        office.setOfficeId(row.getInt("office_id"));
        //office.setDoctor_id(row.getInt("doctor_id"));
        office.setName(row.getString("name"));
        office.setAddress(row.getString("address"));
        office.setPhoneNumber(row.getString("phone_number"));
        //office.setSpecialty(row.getString("specialty"));
        office.setOfficeHr(row.getString("office_hr"));
        return office;
    }

//     Created getOfficeByDoctorId
//    @Override
//    public Office getOfficeByDoctorId(int doctorId) {
//        Office office = null;
//        String sql = "SELECT * FROM office WHERE doctor_id = ?";
//        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, doctorId);
//        if (row.next()) {
//            office = mapRowToOffice(row);
//        }
//        return office;
//    }

    @Override
    public Office updateOfficeDetails(Office updatedOfficeData) {
        try {
            String sql = "UPDATE office SET address = ?, phone_number = ?, " +
                    "office_hr = ? WHERE office_id = ?";
            int rowsUpdated = jdbcTemplate.update(
                    sql,
                    updatedOfficeData.getAddress(),
                    updatedOfficeData.getPhoneNumber(),
                    updatedOfficeData.getOfficeHr(),
                    updatedOfficeData.getOfficeId()
            );

            if (rowsUpdated > 0) {
                // Rows were updated, fetch the updated office details
                return getOfficeById(updatedOfficeData.getOfficeId());
            } else {
                // No rows were updated, handle accordingly (return null or throw an exception)
                return null;
            }
        } catch (Exception ex) {
            // Log the exception
            ex.printStackTrace(); // You can use a logging framework here

            // Rethrow the exception or handle it as needed
            throw new RuntimeException("Error updating office details", ex);
        }
    }

//    @Override
//    public Office getOfficeByUserId(int userId) {
//        String sql = "SELECT o.office_id, o.name, o.address, o.phone_number, o.office_hr\n" +
//                "FROM office o\n" +
//                "JOIN doctors d ON d.office_id = o.office_id\n" +
//                "WHERE user_id =?;";
//        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, userId);
//        if (row.next()) {
//            return mapRowToOffice(row);
//        } else {
//            return null;
//        }
//    }
}