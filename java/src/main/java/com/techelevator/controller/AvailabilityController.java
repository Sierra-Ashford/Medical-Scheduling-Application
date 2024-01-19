package com.techelevator.controller;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.model.Availability;
import com.techelevator.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityDao availabilityDao;

    @GetMapping()
    public ResponseEntity<List<Availability>> getAllAvailableTimes() {
        List<Availability> availability = availabilityDao.getAvailableTimes();
        if (availability.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }
    @PostMapping()
    public Availability createAvailability(@RequestBody com.techelevator.model.Availability availability) {
        return availabilityDao.createAvailability(availability);
    }

}
