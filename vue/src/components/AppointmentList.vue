<template>
    <div class="appointment-container">

        <Appointment v-for="appointment of appointments" :key="appointment" :appointment="appointment"
            class="appointment-element" />
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
        appointments:{
            type:Array
        }
    },
   

}

</script>

<style scoped>
.appointment-container {
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.appointment-element {
    margin: auto;
    text-align: center;
    margin-bottom: 20px;
}</style>