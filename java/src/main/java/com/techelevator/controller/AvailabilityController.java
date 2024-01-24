package com.techelevator.controller;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.model.Availability;
import com.techelevator.model.Doctor;
import com.techelevator.model.Review;
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

        return new ResponseEntity<>(availability, HttpStatus.OK);
    }
    @RequestMapping(path = "/{availabilityId}", method = RequestMethod.GET)
    public Availability getAvailabilityById(@PathVariable int availabilityId) {
        return availabilityDao.getAvailabilityById(availabilityId);
    }

    @PostMapping
    public ResponseEntity<String> createAvailability(@RequestBody Availability availability) {
        try {
            availabilityDao.createAvailability(availability);
            return new ResponseEntity<>("Availability made successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to make Availability " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/{availabilityId}", method = RequestMethod.DELETE)
    public void deleteAvailability(@PathVariable int availabilityId) {
        availabilityDao.deleteAvailability(availabilityId);
    }

    @RequestMapping(path = "/{availabilityId}", method = RequestMethod.PUT)
    public ResponseEntity<Availability> updateAvailability(@RequestBody Availability availability, @PathVariable int availabilityId) {
        availability.setAvailabilityId(availabilityId);
        return new ResponseEntity<>(availabilityDao.updateAvailability(availability), HttpStatus.OK);
    }

}
