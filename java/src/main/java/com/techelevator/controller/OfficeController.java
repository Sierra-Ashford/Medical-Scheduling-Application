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


}
