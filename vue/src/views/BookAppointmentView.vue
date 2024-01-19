<template>
    <NavBar />
    <div>
        

        <h1>Book an Appointment</h1>
        <div>
            <label for="doctor" style="margin-right: 40px;">Select your doctor</label>
            <select name="doctor" id="doctor" style="min-width:200px" v-model=selectedDoctor>
                <option value=null>Select a Doctor</option>
                <option v-for="doctor in this.doctors" v-bind:key="doctor">Dr. {{ doctor.firstName }} {{ doctor.lastName }}</option>

            </select>
        </div>
        <div v-if="selectedDoctor != null && selectedDoctor != ''">
            <label for="desiredDay" style="margin-right: 40px;">Select Date</label>
            <input type="date" name="desiredDay" id="desiredDay" v-model="selectedDate" @change="dateSelected">
        </div>
        <div v-if="selectedDate != null">
            <label for="possibleOptions">Please select available time slot</label>
            <div v-for="slot in appointmentsForDay" :key="slot">

                <TimeSlot :data="slot" />

                <!-- on-click route to home--give patient success message or error code -->

            </div>
        </div>
    </div>
</template>
  
<script>
import TimeSlot from '../components/TimeSlot.vue';
import NavBar from '../components/NavBar.vue';
import DoctorService from '../services/DoctorService';
export default {
    components: {
        TimeSlot,
        NavBar
    },
    data() {
        return {
            selectedDate: null,
            selectedDoctor: null,
            showTimeslots: false,
            doctors: [],
            appointmentsForDay: [
                {
                    appointmentStartTime: 8,
                    appointmentEndTime: 9,
                    isBooked: false
                },
                {
                    startTime: 9,
                    endTime: 10,
                    isBooked: false
                },
                {
                    startTime: 10,
                    endTime: 11,
                    isBooked: false
                },
                {
                    startTime: 11,
                    endTime: 12,
                    isBooked: true
                },
                {
                    startTime: 12,
                    endTime: 1,
                    isBooked: false
                },
                {
                    startTime: 1,
                    endTime: 2,
                    isBooked: false
                },
                {
                    startTime: 2,
                    endTime: 3,
                    isBooked: true
                },
                {
                    startTime: 3,
                    endTime: 4,
                    isBooked: false
                },
                {
                    startTime: 4,
                    endTime: 5,
                    isBooked: false
                }
            ]
        }
    },
    methods: {
        dateSelected() {
            if (this.selectedDate == null || this.selectedDoctor == null) {
                alert("please select a doctor and date");
            } else {
                // get appointments for selectedDate and selectedDoctor
                console.log("selected time is " + this.selectedDate);

            }

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
  