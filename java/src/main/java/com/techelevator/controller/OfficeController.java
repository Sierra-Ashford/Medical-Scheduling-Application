package com.techelevator.controller;

import com.techelevator.dao.OfficeDao;
import com.techelevator.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/offices")
public class OfficeController {

    //Do not need since we are using only one office now:

    @Autowired
    private OfficeDao officeDao;

    @RequestMapping( method= RequestMethod.POST)
    public Office addOfficeToDB(@RequestBody Office office) {
        return officeDao.createOffice(office);
    }

    @RequestMapping( method= RequestMethod.GET)
    public List<Office> getAllOffices() {
        return officeDao.getAllOffices();
    }

    @RequestMapping(path="/{officeId}", method= RequestMethod.GET)
    public Office getOfficeById(@PathVariable int officeId) {
        return officeDao.getOfficeById(officeId);
    }

    //    @RequestMapping(path="/doctor/{doctorId}", method= RequestMethod.GET)
//    public Office getOfficeByDoctorId(@PathVariable int doctorId) {
//        return officeDao.getOfficeByDoctorId(doctorId);
//    }
//    @RequestMapping(path="/user/{userId}", method= RequestMethod.GET)
//    public Office getOfficeByUserId(@PathVariable int userId) {
//        return officeDao.getOfficeByUserId(userId);
//    }

    @RequestMapping(path = "/{officeId}", method = RequestMethod.PUT)
    public Office updateOfficeDetails(@PathVariable int officeId, @RequestBody Office updatedOfficeData) {
        // Set the office ID in the updated data
        updatedOfficeData.setOfficeId(officeId);

        // Update the office details
        return officeDao.updateOfficeDetails(updatedOfficeData);


    }
}




