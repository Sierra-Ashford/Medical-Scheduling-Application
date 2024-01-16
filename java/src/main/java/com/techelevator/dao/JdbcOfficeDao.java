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
        String sql = "SELECT * FROM office WHERE office_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, officeId);
        if (row.next()) {
            office = mapRowToOffice(row);
        }
        return office;
    }
    public Office mapRowToOffice(SqlRowSet row){
        Office office = new Office();

        office.setOffice_id(row.getInt("office_id"));
        office.setDoctor_id(row.getInt("doctor_id"));
        office.setName(row.getString("name"));
        office.setAddress(row.getString("address"));
        office.setPhoneNumber(row.getString("phone_number"));
        office.setCostPerHr(row.getInt("cost_per_hr"));
        office.setSpecialty(row.getString("specialty"));
        office.setOfficeHr(row.getInt("office_hr"));
        return office;
    }
}
