<template>
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div>

        <AppointmentCalendar v-bind=getAppointmentsByDoctorId(doctorId)></AppointmentCalendar>
    </div>
</template>

<script>
import Navbar from '../components/NavBar.vue';
import AppointmentCalendar from '../components/AppointmentCalendar.vue';

export default {
    components: {
        AppointmentCalendar,
        Navbar,
    },
    computed: {
      navbarButtonText() {
        return "Log Out";
      },
      navbarButtonDestination() {
        return "logout";
      }
    },
    methods:{
      getDoctorId() {
       this.$store.state.find(doctor=> doctor.id == id)
    }
    }
}
</script>