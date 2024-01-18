<template>
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <div class="form-input-group">
        <label for="role">Role</label>
        <select id="role" v-model="user.role" required>
          <option value=""></option>
          <option value="ROLE_PATIENT">Patient</option>
          <option value="ROLE_DOCTOR">Doctor</option>
        </select>
      </div>
      <div v-if="user.role === 'ROLE_PATIENT'">
        <div class="form-input-group">
          <label for="firstName">First Name</label>
          <input type="text" id="firstName" v-model="patient.firstName" required />
        </div>
        <div class="form-input-group">
          <label for="lastName">Last Name</label>
          <input type="text" id="lastName" v-model="patient.lastName" required />
        </div>
        <div class="form-input-group">
          <label for="birthDate">Birth Date</label>
          <input type="date" id="birthDate" v-model="patient.birthDate" required />
        </div>
        <div class="form-input-group">
          <label for="phoneNumber">Phone Number</label>
          <input type="text" id="phoneNumber" v-model="patient.phoneNumber" required />
        </div>
        <div class="form-input-group">
          <label for="email">Email</label>
          <input type="text" id="email" v-model="patient.email" required />
        </div>
      </div>

      <div v-if="user.role === 'ROLE_DOCTOR'">
        <div class="form-input-group">
          <label for="firstName">First Name</label>
          <input type="text" id="firstName" v-model="doctor.firstName" required />
        </div>
        <div class="form-input-group">
          <label for="lastName">Last Name</label>
          <input type="text" id="lastName" v-model="doctor.lastName" required />
        </div>
        <div class="form-input-group">
          <label for="specialty">Specialty</label>
          <input type="text" id="specialty" v-model="doctor.specialty" required />
        </div>
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';
import PatientService from '../services/PatientService';
import DoctorService from '../services/DoctorService';

export default {
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
.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}
</style>
