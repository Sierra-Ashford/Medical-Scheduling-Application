<template>
    <div class="appointment-container">

        <Appointment v-for="appointment of appointments" :key="appointment" :appointment="appointment" />
        <div v-if="appointments != null && appointments.length == 0">
            <h4>No appointments to display</h4>
        </div>
    </div>
</template>

<script>
import Appointment from '../components/Appointment.vue';
import AppointmentsService from '../services/AppointmentsService';

export default {
    name: 'AppointmentContainer',
    components: {
        Appointment,
    },
    props: {
        currentDoctorId: {
            type: Number,
            default: 2
        },
        selectedDate: {
            type: Date,
            default: null
        }
    },
    data() {
        return {

        }
    },
    methods: {
        getAppointmentsForDoctor() {
            return AppointmentsService.getAppointmentsByDoctorId(this.currentDoctorId).then((results) => {
                return AppointmentsService.getAllAppointments().then((results) => {
                    let doctorAppointments = [];
                    for (let appt of results) {
                        if (appt.doctorId == this.currentDoctorId) {
                            doctorAppointments.push(appt);
                        }
                    }
                    this.appointments = doctorAppointments;
                    this.appointments = results;
                });
            });
        },


        beforeMount() {
            this.getAppointmentsForDoctor();
        }

    }
}
</script>

<style scoped>
.appointment-container {
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;

}
</style>