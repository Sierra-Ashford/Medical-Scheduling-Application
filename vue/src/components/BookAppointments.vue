<template>
        <h1 class="font-style-center">How can we help?</h1>
        <h2 class="font-style">Book an Appointment</h2>
        <div>
             <label for="doctor" style="margin-right: 40px;">Select your doctor</label>
             <select name="doctor" id="doctor" style="min-width:200px" v-model=selectedDoctor @change="onSelectedDoctorChanged">
                 <option value="">Select a Doctor</option>
                 <option v-for="doctor in this.doctors" v-bind:key="doctor.doctorId" :value="doctor.doctorId">Dr. {{ doctor.firstName }} {{ doctor.lastName }}</option>
 
             </select>
         </div>
         <div v-if="selectedDoctor != null && selectedDoctor != ''">
             <label for="desiredDay" style="margin-right: 40px;">Select Date</label>
             <input type="date" name="desiredDay" id="desiredDay" v-model="selectedDate" @change="onSelectedDateChanged">
         </div>
         <div v-if="selectedDate != null">
             <label for="possibleOptions">Please select available time slot</label>
             <div v-for="slot in availableAppointmentsForDay" :key="slot">
                 <TimeSlot :appointment="slot" :doctorId="this.selectedDoctor" :patientId="patientId"
                 @appt-booked="async() => await on"/>
 
                 <!-- on-click route to home--give patient success message or error code -->
 
             </div>
         </div>
 </template>
   
 <script>
 import TimeSlot from '../components/TimeSlot.vue';
 import DoctorService from '../services/DoctorService';
 import AppointmentService from '../services/AppointmentsService.js';
 import AvailabilityService from '../services/AvailabilityService.js';
 import TimeSlotsService from '../services/CreateTimeSlotsService.js';
 import {add, set, isWithinInterval, isEqual} from 'date-fns';
 
 
 export default {
     components: {
         TimeSlot,
         
     },
     data() {
         return {
             patientId: parseInt(localStorage.getItem('patientId')),
             selectedDate: null,
             selectedDoctor: null,
             showTimeslots: false,
             doctors: [],
             availabilityForSelectedDoctor: [],
             appointmentsForSelectedDoctor: [],
             availableAppointmentsForDay: []
         }
     },
     computed: {
       navbarButtonText() {
         return "Log Out";
       },
       navbarButtonDestination() {
         return "logout";
       }
     },
     methods: {
         async onSelectedDateChanged() {
             //start with all available timeslots for this day
             // use CreateTimeSlotsService for this
             const intervalMinutes = 30;
             const timeslots = TimeSlotsService.generateTimeslots(this.selectedDate, add(this.selectedDate, { days: 1 }), intervalMinutes);
 
             // filter away all timeslots that are not in the doctor's availability
             const onlyAvailableTimeSlots = timeslots
                 .filter(timeslot => this.availabilityForSelectedDoctor.some(availability => {
 
                 const matches = [...availability.startTime.matchAll(/(\d\d):(\d\d):\d\d/g)].at(0);
                 const intervalStartHour = matches.at(1);
                 const intervalStartMinute = matches.at(2);
                 const intervalStart = set(this.selectedDate, { hours: intervalStartHour, minutes: intervalStartMinute });
                 const intervalEnd = set(this.selectedDate, { hours: intervalStartHour, minutes: intervalStartMinute + intervalMinutes });
                 const isWithinIntervalResult = isWithinInterval(timeslot, {
                     start: intervalStart,
                     end: intervalEnd
                 });
                 //console.log({intervalStartHour, intervalStartMinute, intervalStart, intervalEnd, isWithinIntervalResult});
                 return isWithinIntervalResult;
             }));
 
             // filter away all timeslots that are appointments for this doctor
             const onlyAvailableTimeSlotsWithAppointmentsRemoved = onlyAvailableTimeSlots
                 .filter(timeslot => !this.appointmentsForSelectedDoctor
                     .some(appointment => isEqual(appointment.appointmentStartTime, timeslot)));
             
             // set resulting array to availableAppointmentsForDay
             this.availableAppointmentsForDay = onlyAvailableTimeSlotsWithAppointmentsRemoved
                 .map(timeslot => ({startTime: timeslot, endTime: set(timeslot, {minutes: intervalMinutes})}));
             //console.log({availability: this.availabilityForSelectedDoctor, selectedDate: this.selectedDate, timeslots, onlyAvailableTimeSlots, onlyAvailableTimeSlotsWithAppointmentsRemoved});
         },
         async onSelectedDoctorChanged($event) {
             await this.getDoctorsSchedule(parseInt($event.target.value))
         },
         async getDoctorsSchedule(doctorId) {
             const availabilities = await AvailabilityService.getAllAvailability();
             const availabilitiesForDoctor = availabilities.filter(availability => availability.doctorId === doctorId);
             this.availabilityForSelectedDoctor = availabilitiesForDoctor;
 
             const appointments = await AppointmentService.getAllAppointments();
             const appointmentsForDoctor = appointments.filter(appointment => appointment.doctorId === doctorId);
             this.appointmentsForSelectedDoctor = appointmentsForDoctor;
 
             //console.log({availabilities, appointments,availabilitiesForDoctor, appointmentsForDoctor,availabilityByDoctor: this.availability, appointmentsByDoctor: this.appointments})
         },
         
         getDoctors() {
             DoctorService.getAllDoctors().then((response)=> {
                 this.doctors = response.data;
                 console.log("doctors", this.doctors);
             })
         }
     },
     beforeMount(){
         this.getDoctors()
     }
 };
 </script>
 <style>
 .font-style {
     padding-bottom: 10px;
     font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
 
 
 }
 
 .font-style-center {
 
     font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
     text-align: center;
     color: #587DFF;
 }</style>
 