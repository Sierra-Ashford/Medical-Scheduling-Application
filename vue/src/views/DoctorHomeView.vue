<template>
    <div class="grid-container">
      <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
      <NotificationsList></NotificationsList>
      <div class="container">
        <h1 class="welcome">Welcome!</h1>
        <img src="src\images\Daily-Schedule-Light-Blue.jpg" class="placeHolder">
        <notifications/>
      </div>
    </div>
  </template>
    
    <script>
    import Navbar from '../components/NavBar.vue'
    import NotificationsList from '../components/NotificationsList.vue';
    export default {
      components: {
        Navbar,
        NotificationsList,
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
  
  <style>
    .grid-container {
      display: grid;
      grid-template-columns: 1fr; /* Single column layout for the entire page */
      grid-template-rows: auto 1fr; /* First row for Navbar, second row for content */
      height: 100vh; /* Full height of the viewport */
      font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }
  
    .container {
      display: grid;
      grid-template-columns: auto 1fr; /* First column for Welcome, second column for image and notifications */
      grid-gap: 20px; /* Adjust the gap as needed */
      /* align-items: center; Center items vertically */
    }
    .notifications {
      grid-column: span 1; /* Make the "Notifications" component span one column */
      text-align: center; /* Center text horizontally */
    }
  
    .welcome {
      grid-column: span 2; /* Make the "Welcome" header span across both columns */
      text-align: center; /* Center the text horizontally */
      margin: 0; /* Remove default margin */
    }
  
    .placeHolder {
      width: 900px;
      height: auto; /* Ensure the image doesn't exceed the column width */
    }
  </style>