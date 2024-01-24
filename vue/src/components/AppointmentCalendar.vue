<template>
    <div class="calendar-header">
        <h2>Appointments</h2>
        <label for="dateToDisplay">Select Date to display </label>
        <input type="date" v-model="selectedDate" @change="onSelectedDateChanged">
    </div>
    <div v-if="dateSelected">
        <h2>Schedule for {{ selectedDate }}</h2>
        <appointment-list :appointments="appointments"></appointment-list>
    </div>
</template>

<script>
import AppointmentList from '../components/AppointmentList.vue';
import AppointmentsService from '../services/AppointmentsService';

export default {
    data() {
        return {
            selectedDate: new Date(),
            dateSelected: false,
            appointments:[]
        }
    },
    props: {
        currentDoctorId:{
            type:Number,
            default:2
        }
    },
    components: {
        AppointmentList
    },
    methods: {
        onSelectedDateChanged() {
            this.dateSelected = true;
            this.getAppointmentsForDoctor();
            // TODO: get appointments for selected date from the appointmentsService and pass them into the appointmentList
        },
        getAppointmentsForDoctor() {
            return AppointmentsService.getAppointmentsByDoctorIdAndDateString(this.currentDoctorId, this.selectedDate).then((results) => {
                this.appointments = results;
            });
        },
    }



}


</script>

<style scoped>

</style>