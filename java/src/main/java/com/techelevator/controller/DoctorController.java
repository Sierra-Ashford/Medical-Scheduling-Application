package com.techelevator.controller;

import com.techelevator.dao.DoctorDao;
import com.techelevator.dao.PatientDao;
import com.techelevator.model.Doctor;
import com.techelevator.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorDao doctorDao;

    //tested in postman
    @RequestMapping(method = RequestMethod.POST)
    public Doctor addDoctorToDB(@RequestBody Doctor doctor) {

        try {
            return doctorDao.createDoctor(doctor);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            throw e; // Rethrow the exception to return a 500 status
        }
    }
    //tested in postman
    @RequestMapping(method = RequestMethod.GET)
    public List<Doctor> getAllDoctors() {
        return doctorDao.getAllDoctors();
    }


    @RequestMapping(path = "/{doctorId}", method = RequestMethod.GET)
    public Doctor getDoctorById(@PathVariable int doctorId) {
        return doctorDao.getDoctorById(doctorId);
    }

    @RequestMapping(path = "/{doctorId}", method = RequestMethod.DELETE)
    public void deleteDoctor(@PathVariable int doctorId) {
        doctorDao.deleteDoctor(doctorId);
    }

    @RequestMapping(path = "/{doctorId}", method = RequestMethod.PUT)
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor, @PathVariable int doctorId) {
        doctor.setDoctorId(doctorId);

        return new ResponseEntity<>(doctorDao.updateDoctor(doctor), HttpStatus.OK);
    }

}
