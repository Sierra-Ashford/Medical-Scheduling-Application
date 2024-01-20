<template>
  <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
  <div id="login" class="flex-container">
    <form v-on:submit.prevent="login" class="registration-form">
      <h1>Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials" class="error-message">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration" class="registration-success">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username" class="label">Username</label>
        <input type="text" id="username" v-model="user.username" required class="blue-border" autofocus />
      </div>
      <div class="form-input-group">
        <label for="password" class="label">Password</label>
        <input type="password" id="password" v-model="user.password" required class="blue-border" />
      </div>
      <button type="submit" class="sign-in-btn">Sign in</button>
      <!-- <p>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p> -->
    </form>

    <!-- Blurb section -->
    <div class="blurb-container">
      <div class="blurb-section">
        <div class="blurb-header">
          <h2>Medicine with a different approach</h2>
          <img src="src\Images\image.png" alt="Logo" class="blurb-logo" />
        </div>
        <p>TimeRx is a comprehensive medical scheduling app designed to streamline and optimize the appointment
          management process for healthcare providers and their patients.</p>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import Navbar from '../components/NavBar.vue';
import doctorService from '../services/DoctorService.js';
import patientService from '../services/PatientService.js';

export default {
  components: {
    Navbar,
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false
    };
  },
  computed: {
    navbarButtonText() {
      return "Create Account";
    },
    navbarButtonDestination() {
      return "register";
    }
  },
  methods: {
    async login() {
      try {
        const loginResponse = await authService.login(this.user);
        if (loginResponse.status == 200) {
          this.$store.commit("SET_AUTH_TOKEN", loginResponse.data.token);
          this.$store.commit("SET_USER", loginResponse.data.user);
          if (loginResponse.data.user.authorities.at(0)?.name === 'ROLE_DOCTOR') {
            const doctors = (await doctorService.getAllDoctors()).data;
            const doctor = doctors.find(doctor => doctor.userId === loginResponse.data.user.id);
            
            console.log({loginResponse, doctors, doctor});

            if(doctor)
              this.$store.commit("SET_DOCTOR_ID", doctor.doctorId);

          } else {
            const patients = (await patientService.getAllPatients()).data;
            const patient = patients.find(patient => patient.userId === loginResponse.data.user.id);

            console.log({loginResponse, patients, patient});

            if(patient)
              this.$store.commit("SET_PATIENT_ID", patient.patientId)
          }

        }
        if (this.$store.getters.getRole === 'ROLE_USER') {
          this.$router.push("/");
        } else if (this.$store.getters.getRole === 'ROLE_ADMIN') {
          this.$router.push("/")
        } else if (this.$store.getters.getRole === "ROLE_PATIENT") {
          this.$router.push("/patient");
        } else if (this.$store.getters.getRole === 'ROLE_DOCTOR') {
          this.$router.push("/doctor");
        }
      } catch (error) {
        const response = error.response;

        if (response?.status === 401) {
          this.invalidCredentials = true;
        }
        console.log(error);
      }

    }
  }
};
</script>

<style scoped>
.text-center {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

.registration-form {
  max-width: 600px;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
}

.form-input-group {
  margin-bottom: 15px;
}

.sub-form-group {
  margin-bottom: 10px;
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

.blue-border {
  border: 2px solid #587DFF;
  padding: 15px;
  width: 100%;
  box-sizing: border-box;
  border-radius: 10px;
  color: darkgrey;
}

.label {
  color: darkgrey;
  /* Light gray label text color */
}

.error-message {
  color: #587DFF;
  margin-bottom: 15px;
}

.login-link {
  margin-top: 15px;
}

.sign-in-btn {
  color: #fff;
  text-decoration: none;
  font-weight: bold;
  padding: 10px 20px;
  background-color: #587DFF;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.sign-in-btn:hover {
  background-color: #B6E2EF;
}



.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  /* Align items to the top */
  height: 100vh;
  padding: 20px;
  /* Add padding to create space around the form */
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.form-container {
  max-width: 600px;
  width: 100%;
}

.blurb-container {
  max-width: 600px;
  margin: 0 auto;
}

.blurb-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* Center horizontally within blurb-section */
  text-align: center;
  /* Center text within blurb-section */
}

.blurb-header {
  display: flex;
  align-items: center;
  /* margin-bottom: 5px; */
  font-size: 30px;
  /* Adjust as needed */
}

.blurb-logo {
  width: 200px;
  /* Adjust the size of the logo */
  height: auto;
  /* Adjust as needed */
}

.blurb-section p {
  font-size: 20px;
  /* Adjust the font size as needed */
}
</style>