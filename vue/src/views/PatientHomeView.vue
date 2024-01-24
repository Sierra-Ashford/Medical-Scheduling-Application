<template>
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div class="home">
      <h1> Patient Home</h1>
      <!-- <NotificationsList :currentPatientId="1"></NotificationsList> -->
      <PatientInfo />
    </div>
  </template>
  
  <script>
  import Navbar from '../components/NavBar.vue';
  import NotificationsList from '../components/NotificationsList.vue';
  import PatientInfo from '../components/PatientInfo.vue';

  export default {
    components: {
      Navbar,
      //  NotificationsList,
      PatientInfo
    },
    computed: {
      navbarButtonText() {
        return "Log Out";
      },
      navbarButtonDestination() {
        return "logout";
      }
    },
  };
  </script>



  