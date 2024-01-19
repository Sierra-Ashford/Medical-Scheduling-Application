package com.techelevator.controller;

import com.techelevator.dao.PrescriptionDao;
import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Prescription;
import com.techelevator.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionDao prescriptionDao;

    @GetMapping("/{prescriptionId}")
    public Prescription get(@PathVariable int prescriptionId) {
        return prescriptionDao.getPrescriptionById(prescriptionId);
    }

    @RequestMapping(path = "/patient/{patientId}", method = RequestMethod.GET)
    public List<Prescription> getPrescriptionByPatientId(@PathVariable int patientId) {
        return prescriptionDao.getPrescriptionsByPatientId(patientId);
    }

    @PostMapping
    public ResponseEntity<String> createPrescription(@RequestBody Prescription prescription) {
        try {
            prescriptionDao.createPrescription(prescription);
            return new ResponseEntity<>("prescription made successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to make prescription " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/{prescriptionId}", method = RequestMethod.DELETE)
    public void deletePrescription(@PathVariable int prescriptionId) {
        prescriptionDao.deletePrescription(prescriptionId);
    }

    @RequestMapping(path = "/{prescriptionId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePrescription(@RequestBody Prescription prescription, @PathVariable int prescriptionId) {
        try {
            prescription.setPrescriptionId(prescriptionId);
            prescriptionDao.updatePrescription(prescription);
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update review " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
