<template>
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div>
        <BookAppointments></BookAppointments>
     
     
    </div>
  </template>
  
  <script>
  import BookAppointments from '../components/BookAppointments.vue';
  import Navbar from '../components/NavBar.vue'

  
  export default {
    components: {
        BookAppointments,
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