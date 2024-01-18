package com.techelevator.controller;

import com.techelevator.dao.DoctorDao;
import com.techelevator.dao.PatientDao;
import com.techelevator.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorDao doctorDao;

    //how can I retrieve the user id if I am creating them at the same time?
    //how do I connect this?
    @RequestMapping( method= RequestMethod.POST)
    public Doctor addDoctorToDB(@RequestBody Doctor doctor) {
        return doctorDao.createDoctor(doctor);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Doctor> getAllDoctors() {
        return doctorDao.getAllDoctors();
    }

    @RequestMapping(path = "/{doctorId}", method = RequestMethod.GET)
    public Doctor getDoctorById(@PathVariable int doctorId) {
        return doctorDao.getDoctorById(doctorId);
    }
    // this one too not sure if this should be in doctors controller
    @RequestMapping("/{officeId}")
    public List<Doctor> getDoctorsByOfficeId(@PathVariable int officeId) {
        return doctorDao.getDoctorsByOfficeId(officeId);
    }


}
