<template>
  <div class="grid-container">
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div class="container">
      <h1 class="welcome">Welcome!</h1>

    </div>
    <div class="box">



      <div class="notifications">

<!-- 
        <NotificationsList></NotificationsList> -->
      </div>

      <div class="appointments">

        <AppointmentCalendar :currentDoctorId="doctorId"></AppointmentCalendar>
      </div>
    </div>
  </div>
</template>
    
<script>
import Navbar from '../components/NavBar.vue'
// import NotificationsList from '../components/NotificationsList.vue';
import AppointmentCalendar from '../components/AppointmentCalendar.vue';

export default {
  data() {
    return {
      doctorId: null
    }
  },
  components: {
    Navbar,
    // NotificationsList,
    AppointmentCalendar,
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
};
</script>
  
<style scoped>
.grid-container {

  display: grid;
  grid-template-columns: 1fr;
  /* Single column layout for the entire page */
  grid-template-rows: auto 1fr;
  /* First row for Navbar, second row for content */
  height: 100vh;
  /* Full height of the viewport */
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  align-items: flex-start
}

.container {
  display: grid;
  grid-template-columns: auto 1fr;
  /* First column for Welcome, second column for image and notifications */
  grid-gap: 20px;
  /* Adjust the gap as needed */
  /* align-items: center; Center items vertically */

}

.box {

  display: flex;
  align-content: space-between;
  flex-direction: row;

}

.appointments {
  width: 50%;
  height: 100vh;
  background-color: #B6E2EF;
  text-align: center;
  color: rgb(0, 0, 0);
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;

}

.notifications {
  width: 50%;
  color: rgb(0, 0, 0);
  height: 100vh;
}

.welcome {

  grid-column: span 2;
  /* Make the "Welcome" header span across both columns */
  text-align: center;
  /* Center the text horizontally */
  margin: 0;
  /* Remove default margin */

}
</style>