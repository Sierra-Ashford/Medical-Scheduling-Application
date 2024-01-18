package com.techelevator.controller;

import com.techelevator.dao.OfficeDao;
import com.techelevator.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offices")
public class OfficeController {

    @Autowired
    private OfficeDao officeDao;

    @RequestMapping(path = "/all", method= RequestMethod.GET)
    public List<Office> getAllOffices() {
        return officeDao.getAllOffices();
    }

    @RequestMapping(path="/{officeId}", method= RequestMethod.GET)
    public Office getOfficeById(@PathVariable int officeId) {
        return officeDao.getOfficeById(officeId);
    }

    @RequestMapping(path="/{doctorId}", method= RequestMethod.GET)
    public Office getOfficeByDoctorId(@PathVariable int doctorId) {
        return officeDao.getOfficeByDoctorId(doctorId);
    }

    // Are we going to have functions to add or remove offices?
        // Or are we going to have predetermined office locations

}
