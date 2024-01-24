package com.techelevator.dao;

import com.techelevator.model.Office;

import java.util.List;

public interface OfficeDao {
    Office createOffice(Office newOffice);

    List<Office> getAllOffices();
    Office getOfficeById(int officeId);

    //Office getOfficeByDoctorId(int doctorId);

    Office updateOfficeDetails(Office updatedOfficeData);

    //Office getOfficeByUserId(int userId);
}
