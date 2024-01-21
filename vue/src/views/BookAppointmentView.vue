<template>
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div>
        <BookAppointments></BookAppointments>
        <!-- <NotificationsList :currentDoctorId="1"></NotificationsList> -->
    </div>
  </template>
  
  <script>
  import BookAppointments from '../components/BookAppointments.vue';
  import NotificationsList from '../components/NotificationsList.vue';
  import Navbar from '../components/NavBar.vue'
  
  export default {
    components: {
        BookAppointments,
        NotificationsList,
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
  }
  </script>