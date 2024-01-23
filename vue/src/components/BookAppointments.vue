<template>
    <h1>Book an Appointment</h1>
    <div>
        <label for="doctor" style="margin-right: 40px;">Select your doctor</label>
        <select name="doctor" id="doctor" style="min-width:200px" v-model="selectedDoctorId"
            @change="onSelectedDoctorChanged">
            <option value="">Select a Doctor</option>
            <option v-for="doctor in availableDoctors" v-bind:key="doctor.doctorId" :value="doctor.doctorId">Dr. {{
                doctor.firstName }} {{ doctor.lastName }}</option>

        </select>
    </div>
    <div>
        <label for="description">Reason for visit:</label>
        <input type="text" v-model="notesForVisit" placeholder="Briefly describe your reason">
    </div>
    <div v-if="selectedDoctorId != null && selectedDoctorId != ''">
        <label for="selectedDate" style="margin-right: 40px;">Select Date</label>
        <input type="date" name="selectedDate" id="selectedDate" v-model="selectedDateString"
            @change="onSelectedDateChanged">
    </div>
    <div v-if="selectedDoctorId && selectedDate">
        <!-- <div>{{ availableTimeslotsForDay }}</div> -->
        <div v-if="availableTimeslotsForDay.length > 0">
            <label>Please select available time slot</label>
            <div v-for="timeslot in availableTimeslotsForDay" :key="timeslot.startDateTime.toString()">
                <TimeSlot :timeslot="timeslot" :doctorId="selectedDoctorId" :patientId="patientId" :notes="notesForVisit"
                    @appt-booked="onAppointmentBooked" />


                <!-- on-click route to home--give patient success message or error code -->

            </div>
        </div>
        <div v-else>
            <label>No timeslots available on this day.</label>
        </div>
    </div>
    <!-- <div>
        <label>DEBUG</label>
        <code><pre>{{ JSON.stringify({ selectedDateString, selectedDate, availabilityForSelectedDoctor, appointmentsForSelectedDoctor, possibleTimeslotsForDay, availableTimeslotsForDay }, null, 2) }}</pre></code>
    </div> -->
</template>
   
<script>
import TimeSlot from '../components/TimeSlot.vue';
import doctorService from '../services/DoctorService';
import appointmentService from '../services/AppointmentsService.js';
import availabilityService from '../services/AvailabilityService.js';
import timeslotService from '../services/CreateTimeSlotsService.js';
import { add, set, isWithinInterval } from 'date-fns';


export default {
    components: {
        TimeSlot,

    },
    data() {
        return {
            patientId: null,
            selectedDateString: null,
            selectedDoctorId: null,
            notesForVisit: '',
            availableDoctors: [],
            availabilityForSelectedDoctor: [],
            appointmentsForSelectedDoctor: [],
        }
    },
    computed: {
        navbarButtonText() {
            return "Log Out";
        },
        navbarButtonDestination() {
            return "logout";
        },
        // selectedDate() {
        //     return this.selectedDateString ? new Date(this.selectedDateString) : undefined;
        // },

        selectedDate() {
            if (this.selectedDateString) {
                const [year, month, day] = this.selectedDateString.split('-').map(Number);
                return new Date(year, month - 1, day); // Month is zero-indexed in JavaScript Dates
            }
            return undefined;
        },

        possibleTimeslotsForDay() {
            if (!this.selectedDate) return [];

            // should recalc when date changes
            // run algorithm to generate timeslots based on date


            const timeslots = timeslotService.generateTimeslots(
                this.selectedDate, // the date the user selected
                add(this.selectedDate, { days: 1 }), // one day past that (to get the whole 24 hours)
                30); // NOTE: timeslot intervals are hardcoded to 30 mins here
            return timeslots;
        },
        availableTimeslotsForDay() {
            if (!this.selectedDate) {
                return [];
            }
            // should recalc when and of availability, appointments, or timeslots change
            // start with available timeslots

            // filter out availability of doctor
            const filteredByAvailability = this.possibleTimeslotsForDay.filter(timeslot =>
                this.availabilityForSelectedDoctor.some(availability => {

                    // attach the hour and minute to the selected date
                    // we do this because availabilities are set to a day of the week, not a date
                    // they have already been filtered by day of week using the service so we know they are matching the day of the week already
                    const availabilityStartDateTime = set(this.selectedDate, { hours: availability.startTimeHours, minutes: availability.startTimeMinutes });
                    const availabilityEndDateTime = set(this.selectedDate, { hours: availability.endTimeHours, minutes: availability.endTimeMinutes });
                    const availabilityInterval = { start: availabilityStartDateTime, end: availabilityEndDateTime };

                    //console.log({ availabilityStartDateTime, availabilityEndDateTime, availabilityInterval, startHours: availability.startTimeHours, startMinutes: availability.startTimeMinutes});

                    // both timeslot start and end must be within the availability interval
                    return isWithinInterval(timeslot.startDateTime, availabilityInterval)
                        && isWithinInterval(timeslot.endDateTime, availabilityInterval);

                })

            );
            //return filteredByAvailability;

            //check if its necessary to filter this then run it?

            // filter out appointments of doctor
            const filteredByAppointments = filteredByAvailability.filter(timeslot =>
                // appointments are already filtered by doctor ID and date
                !this.appointmentsForSelectedDoctor.some(appointment => {
                    //console.log({appointment, timeslot});

                    // create an interval for the appointment
                    const appointmentInterval = { start: appointment.startDateTime, end: appointment.endDateTime };

                    // both timeslot start and end must be within the availability interval
                    return isWithinInterval(timeslot.startDateTime, appointmentInterval)
                        && isWithinInterval(timeslot.endDateTime, appointmentInterval);
                })
            );

            //console.log('Computing availableTimeslotsForDay');
            //console.log({ filteredByAvailability, filteredByAppointments })
            return filteredByAppointments;
        }
    },
    methods: {
        async onSelectedDateChanged() {
            // selected date has changed, so availability and appointments need to be fetched
            this.availabilityForSelectedDoctor = await availabilityService.getAvailabilityByDoctorIdAndDayOfWeek(this.selectedDoctorId, this.selectedDate);
            this.appointmentsForSelectedDoctor = await appointmentService.getAppointmentsByDoctorIdAndDate(this.selectedDoctorId, this.selectedDate);

            // also, we need to regenerate the timeslots -- this is done by a computed property
        },
        async onSelectedDoctorChanged() {
            // we only want to call these after a date has been selected, but we want to call again if the date is changed
            if (!this.selectedDate) return;

            // selected doctor id has changed, so availability and appointments need to be fetched
            this.availabilityForSelectedDoctor = await availabilityService.getAvailabilityByDoctorIdAndDayOfWeek(this.selectedDoctorId, this.selectedDate);
            this.appointmentsForSelectedDoctor = await appointmentService.getAppointmentsByDoctorIdAndDate(this.selectedDoctorId, this.selectedDate);

            // date didn't change, so timeslots computer property should not recalc
        },
        async onAppointmentBooked() {
            //console.log('event caught');
            // an appointment has been booked, so appointments need to be fetched
            this.appointmentsForSelectedDoctor = await appointmentService.getAppointmentsByDoctorIdAndDate(this.selectedDoctorId, this.selectedDate);
        },
    },
    async beforeMount() {
        this.patientId = parseInt(localStorage.getItem('patientId'));

        const response = await doctorService.getAllDoctors();
        this.availableDoctors = response.data;
    }
};
</script>
