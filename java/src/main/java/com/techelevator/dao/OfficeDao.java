package com.techelevator.dao;

import com.techelevator.model.Office;

import java.util.List;

public interface OfficeDao {
    List<Office> getAllOffices();
    Office getOfficeById(int officeId);

    Office getOfficeByDoctorId(int doctorId);

}
