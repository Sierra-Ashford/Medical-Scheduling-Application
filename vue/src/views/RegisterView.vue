<template>
  <div>
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div id="register" class="flex-container">
      <div class="form-container">
        <form v-on:submit.prevent="register" class="registration-form">
          <h1>Create Account</h1>
          <div role="alert" v-if="registrationErrors" class="error-message">
            {{ registrationErrorMsg }}
          </div>
          <div class="form-input-group">
            <label for="username" class="label">Username:</label>
            <input type="text" id="username" v-model="user.username" required class="blue-border" />
          </div>
          <div class="form-input-group">
            <label for="password" class="label">Password:</label>
            <input type="password" id="password" v-model="user.password" required class="blue-border" />
          </div>
          <div class="form-input-group">
            <label for="confirmPassword" class="label">Confirm Password:</label>
            <input type="password" id="confirmPassword" v-model="user.confirmPassword" required class="blue-border" />
          </div>
          <div class="form-input-group">
            <label for="role" class="label">Select Role:</label>
            <select id="role" v-model="user.role" required class="blue-border">
          <option value="ROLE_PATIENT">Patient</option>
          <option value="ROLE_DOCTOR">Doctor</option>
        </select>
          </div>
          <!-- Additional fields for doctors -->
          <div v-if="user.role === 'ROLE_DOCTOR'" class="doctor-form-input-group">
            <div id="doctorFirstName" class="sub-form-group">
              <label for="doctorFirstName" class="label">First Name:</label>
              <input type="text" id="doctorFirstName" v-model="doctor.firstName" required class="blue-border" />
            </div>
            <div id="doctorLastName" class="sub-form-group">
              <label for="doctorLastName" class="label">Last Name:</label>
              <input type="text" id="doctorLastName" v-model="doctor.lastName" required class="blue-border" />
            </div>
            <div id="specialty" class="sub-form-group">
              <label for="specialty" class="label">Specialization:</label>
              <input type="text" id="specialty" v-model="doctor.specialty" required class="blue-border" />
            </div>
          </div>

          <div v-if="user.role === 'ROLE_PATIENT'" class="patient-form-input-group">
            <div id="patientFirstName" class="sub-form-group">
              <label for="patientFirstName" class="label">First Name:</label>
              <input type="text" id="patientFirstName" v-model="patient.firstName" required class="blue-border" />
            </div>
            <div id="patientLastName" class="sub-form-group">
              <label for="patientLastName" class="label">Last Name:</label>
              <input type="text" id="patientLastName" v-model="patient.lastName" required class="blue-border" />
            </div>
            <div id="patientBirthdate" class="sub-form-group">
              <label for="patientBirthdate" class="label">Date Of Birth:</label>
              <input type="date" id="patientBirthdate" v-model="patient.birthDate" required class="blue-border" />
            </div>
            <div id="patientPhoneNumber" class="sub-form-group">
              <label for="patientPhoneNumber" class="label">Phone Number:</label>
              <input type="text" id="patientPhoneNumber" v-model="patient.phoneNumber" required class="blue-border" />
            </div>
            <div id="patientEmail" class="sub-form-group">
              <label for="patientEmail" class="label">Email:</label>
              <input type="text" id="patientEmail" v-model="patient.email" required class="blue-border" />
            </div>
          </div>

          <button type="submit" class="create-account-btn">Create Account</button>
          <!-- <p class="login-link">
        <router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link>
      </p>  -->
        </form>
      </div>

       <!-- Blurb section -->
       <div class="blurb-container">
        <div class="blurb-section">
          <div class="blurb-header">
            <h2>Medicine with a different approach</h2>
            <img src="src\Images\image.png" alt="Logo" class="blurb-logo" />
          </div>
          <p>TimeRx is a comprehensive medical scheduling app designed to streamline and optimize the appointment management process for healthcare providers and their patients.</p>
        </div>
      </div>
    </div>
    </div>
</template>

<script>
import authService from '../services/AuthService';
import PatientService from '../services/PatientService';
import DoctorService from '../services/DoctorService';
import Navbar from '../components/NavBar.vue'

export default {
  components: {
    Navbar,
  },
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: '',
      },
      doctor: {
        firstName: '',
        lastName: '',
        specialty: '',
      },
      patient: {
        firstName: '',
        lastName: '',
        birthDate: '',
        phoneNumber: '',
        email: '',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  computed: {
    navbarButtonText() {
      return "Log In";
    },
    navbarButtonDestination() {
      return "login";
    }
  },
  methods: {
    async register() {
      try {
        if (this.user.password != this.user.confirmPassword) {
          this.registrationErrors = true;
          this.registrationErrorMsg = 'Password & Confirm Password do not match.';
        } else {
          //console.log(`Calling auth service`);
          //console.log({ user: this.user, doctor: this.doctor, patient: this.patient });

          const response = await authService.register(this.user);
          //console.log(response);
          if (this.user.role === 'ROLE_PATIENT') {
            const patient = await PatientService.create({ ...this.patient, userId: response.data.id });
            //console.log(patient);
          } else {
            const doctor = await DoctorService.create({ ...this.doctor, userId: response.data.id });
            //console.log(doctor);
          }

          this.$router.push({
            path: '/login',
            query: { registration: 'success' },
          });
        }
      }
      catch (error) {
        //console.log(`Error during registration.`);
        //console.log(error);
        const response = error.response;
        this.registrationErrors = true;
        if (response.status === 400) {
          this.registrationErrorMsg = 'Bad Request: Validation Errors';
        }
      }
    },
    register2() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        console.log(`Calling auth service`);
        console.log({ user: this.user, doctor: this.doctor, patient: this.patient });
        authService
          .register(this.user)
          .then((response) => {
            console.log(response);
            if (response.status == 201) {
              // create patient or doctor
              if (this.user.role === 'ROLE_PATIENT') {
                PatientService
                  .create({ ...this.patient, userId: response.body.userId })
                  .then(response => {
                    console.log(response);
                    this.$router.push({
                      path: '/login',
                      query: { registration: 'success' },
                    });
                  });
              } else {
                DoctorService
                  .create({ ...this.doctor, userId: response.body.userId })
                  .then(response => {
                    console.log(response);
                    this.$router.push({
                      path: '/login',
                      query: { registration: 'success' },
                    });
                  });
              }
            }
          })
          .catch((error) => {
            console.log(`Error during auth call`);
            console.log(error.response);
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
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

.create-account-btn {
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
  
  .create-account-btn:hover {
    background-color: #B6E2EF;
  }



.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-start; /* Align items to the top */
  height: 100vh;
  padding: 20px; /* Add padding to create space around the form */
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
  align-items: center; /* Center horizontally within blurb-section */
  text-align: center; /* Center text within blurb-section */
}
.blurb-header {
  display: flex;
  align-items: center;
  /* margin-bottom: 5px; */
  font-size: 30px; /* Adjust as needed */
}

.blurb-logo {
  width: 200px; /* Adjust the size of the logo */
  height: auto;
   /* Adjust as needed */
}
.blurb-section p {
  font-size: 20px; /* Adjust the font size as needed */
}
</style>

