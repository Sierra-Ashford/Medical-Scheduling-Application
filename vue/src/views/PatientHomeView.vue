<template>
  <div class="grid-container">
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div class="home">
      <!-- <h1> Patient Home</h1> -->
      <NotificationsList :currentPatientId="this.$store.state.patientId ?? 2"></NotificationsList>
      <PatientInfo />
    </div>


  </div>
</template>
  
  <script>
  import Navbar from '../components/NavBar.vue';
  import NotificationsList from '../components/NotificationsList.vue';
  import PatientInfo from '../components/PatientInfo.vue';

  export default {
    components: {
      Navbar,
       NotificationsList,
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
    navbarButtonDestination() {
      return "logout";
    }
  }

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

.placeholder {

  width: 100%;
}

.notifications {
  width: 50%;
  height: 100vh;
}

.myDashboard {

  grid-column: span 2;
  /* Make the "Welcome" header span across both columns */
  text-align: center;
  /* Center the text horizontally */
  margin: 0;
  /* Remove default margin */


}
</style>
  