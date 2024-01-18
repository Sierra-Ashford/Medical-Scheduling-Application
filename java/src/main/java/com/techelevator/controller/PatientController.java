package com.techelevator.controller;

import com.techelevator.dao.PatientDao;
import com.techelevator.model.Doctor;
import com.techelevator.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/patients")
public class PatientController {

 @Autowired
    private PatientDao patientDao;

    @RequestMapping(method=RequestMethod.POST)
    public Patient addPatientToDB(@RequestBody Patient patient) {
        return patientDao.createPatient(patient);
    }
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }
    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId) {
        Patient patient = patientDao.getPatientById(patientId);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
