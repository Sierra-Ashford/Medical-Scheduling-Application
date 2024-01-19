<template>
    <div class="navbar">
      <img src="src\Images\image.png" alt="Logo" class="logo" />
      <h1 class="brand">MedConnect</h1>
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
  };
  </script>
  
  <style scoped>
  .navbar {
    padding: 10px;
    display: flex;
    align-items: center;
    border-bottom: 3px solid #2720FF; /* Add blue line at the bottom */
  }
  
  .logo {
    width: 100px; /* Adjust the size of the logo */
    height: auto;
  }
  
  .brand {
    flex-grow: 1; /* Allow the brand to take up remaining space */
  }
  
  .brand h1 {
    margin: 0;
    font-size: 24px;
  }
  
  .login-link {
    color: #fff;
    text-decoration: none;
    font-weight: bold;
    padding: 8px 16px;
    background-color: #587DFF;
    border-radius: 4px;
    transition: background-color 0.3s ease;
  }
  
  .login-link:hover {
    background-color: #B6E2EF;
  }
  </style>
  