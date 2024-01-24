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
    // methods:{
    //   getDoctorId() {
    //    this.$store.state.find(doctor=> doctor.id == id)
    // }
  // }
    navbarButtonDestination() {
    return "logout";
  }
},
beforeMount() {
  this.doctorId = this.$store.state.doctorId;
  //this.doctorId = parseInt(localStorage.getItem('doctorId'));
}
}
</script>