<template>
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div class="home">
      <h1> My Reviews</h1>
    </div>
  </template>
  
  <script>
  import Navbar from '../components/NavBar.vue'
  export default {
    components: {
      Navbar,
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