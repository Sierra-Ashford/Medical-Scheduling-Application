package com.techelevator.controller;

//Jill Added
import com.techelevator.dao.PatientDao;
import com.techelevator.dao.DoctorDao;
//

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
import com.techelevator.model.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Jill Added
import java.time.LocalDate;
//

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

//    @RequestMapping(path = "/doctors/{appointmentId}", method = RequestMethod.GET)
//    public Appointment getAllAppointmentByDoctorId(@PathVariable int doctorId) {
//        return appointmentDao.getAppointmentById(doctorId);
//    }

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
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        List<Appointment> appointments = appointmentDao.getAppointmentsByDoctorId(doctorId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable int patientId) {
        List<Appointment> appointments = appointmentDao.getAllAppointmentByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
