<template>
  <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
  <div id="login" class="flex-container">
    <form v-on:submit.prevent="login" class="registration-form">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials" class="error-message">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration" class="registration-success">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required  class="blue-border" autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required class="blue-border"  />
      </div>
      <button type="submit" class="create-account-btn">Sign in</button>
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

export default {
  components: {
    Navbar,
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
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
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
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
  color: 587DFF;
  margin-bottom: 15px;
}

.login-link {
  margin-top: 15px;
}

.create-account-btn {
  background-color: #587DFF;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
}



.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-start; /* Align items to the top */
  height: 100vh;
  padding: 20px; /* Add padding to create space around the form */
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
  font-size: 33px; /* Adjust as needed */
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