package com.techelevator.controller;

//Jill Added
import com.techelevator.dao.PatientDao;
import com.techelevator.dao.DoctorDao;
//

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
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


   
    
}
