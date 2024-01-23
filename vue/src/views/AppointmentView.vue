<template>
  <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
  <div>

    <AppointmentCalendar :currentDoctorId="doctorId"></AppointmentCalendar>
  </div>
</template>

<script>
import Navbar from '../components/NavBar.vue';
import AppointmentCalendar from '../components/AppointmentCalendar.vue';

export default {
  data() {
    return {
      doctorId: null
    }
  },
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
  beforeMount() {
    this.doctorId = parseInt(localStorage.getItem('doctorId'));
  }
}
</script>