package com.techelevator.controller;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
import com.techelevator.model.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentDao appointmentDao;

    @GetMapping()
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentDao.getAllAppointments();
        if (appointments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @RequestMapping(path = "/{appointmentId}", method = RequestMethod.GET)
    public Appointment getAvailabilityById(@PathVariable int appointmentId) {
        return appointmentDao.getAppointmentById(appointmentId);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(appointmentDao.createNewAppointment(appointment), HttpStatus.OK);
    }

    @RequestMapping(path = "/{appointmentId}", method = RequestMethod.DELETE)
    public void deleteAppointment(@PathVariable int appointmentId) {
        appointmentDao.deleteAppointment(appointmentId);
    }

    @RequestMapping(path = "/{appointmentId}", method = RequestMethod.PUT)
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment, @PathVariable int appointmentId) {
        appointment.setAppointmentId(appointmentId);
        return new ResponseEntity<>(appointmentDao.updateAppointment(appointment), HttpStatus.OK);
    }
}
