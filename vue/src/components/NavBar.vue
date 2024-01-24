<template>
  <div class="navbar">
    <div class="brand-container">
    <img src="../Images/image.png" alt="Logo" class="logo" />
    <h1 class="brand">MedConnect</h1>
    </div>
    <router-link v-if="isPatient" to="/patient"  class="nav-link">Home</router-link>
    <router-link v-if="isPatient" to="/find"  class="nav-link">Find a Doctor</router-link>
    <router-link v-if="isPatient" to="/book-appointment"  class="nav-link">Schedule An Appointment</router-link>
    <router-link v-if="isPatient" to="/prescriptions"  class="nav-link">My Prescriptions</router-link>
   <!-- <router-link v-if="showPatientLink" to="/bookAppointment"  class="nav-link">Book Appointment</router-link> -->
    <!-- <router-link v-if="showPatientLink" to="/reviews"  class="nav-link">Settings</router-link> -->

    <router-link v-if="isDoctor" to="/doctor"  class="nav-link">Home</router-link>
    <router-link v-if="isDoctor" to="/MyPatients"  class="nav-link">My Patients</router-link>
    <router-link v-if="isDoctor" to="/MyOffice"  class="nav-link">My Office</router-link>
    <router-link v-if="isDoctor" to="/set-my-hours"  class="nav-link">Set My Hours</router-link>
    <router-link v-if="isDoctor" to="/MyReviews"  class="nav-link">Reviews</router-link>
    <!-- <router-link v-if="showDoctorLink" to="/Notifications"  class="nav-link">Notifications</router-link> -->
    <router-link :to="{ name: buttonDestination }" class="login-link">
      {{ buttonText }}
    </router-link>
  </div>
</template>


  
  <script>
  export default {
    props: {
      buttonText: {
        type: String,
        default: "Sign Up",
      },
      buttonDestination: {
        type: String,
        default: "register",
      },
    },
    computed: {
      isPatient() {
      return this.$store.getters.getRole === 'ROLE_PATIENT';
    },
    isDoctor() {
      return this.$store.getters.getRole === 'ROLE_DOCTOR';
    },
  },
  methods: {
    handleButtonClick() {
      // handle button click
      if (this.buttonDestination) {
        this.$router.push({ name: this.buttonDestination });
      }
    },
  },
};
  </script>
  <style scoped>
  .navbar {
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 3px solid #2720FF;
  }
  
  .brand-container {
    display: flex;
    align-items: center;
  }
  
  .logo {
    width: 110px;
    height: auto;
    margin-right: 10px; /* Add margin between logo and brand */
  }
  
  .brand {
    margin: 0;
    font-size: 30px;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;

  }
  
  .nav-links {
    display: flex;
  }
  
  .nav-link {
    color: #888;
    text-decoration: none;
    margin-right: 15px;
    font-size: 18px;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    transition: color 0.3s ease, border-bottom 0.3s ease;
  }
  
  .nav-link:hover {
    color: #333;
    border-bottom: 2px solid #333;
  }
  
  .login-link {
    color: #fff;
    text-decoration: none;
    font-weight: bold;
    padding: 12px 24px;
    background-color: #587DFF;
    border-radius: 6px;
    transition: background-color 0.3s ease;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  }
  
  .login-link:hover {
    background-color: #B6E2EF;
  }
  </style>
  