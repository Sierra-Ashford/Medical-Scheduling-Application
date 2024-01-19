<template>
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div class="home">
      <my-office/>

    </div>
  </template>
  
  <script>
  import Navbar from '../components/NavBar.vue'
  import MyOffice from '../components/MyOffice.vue'
  export default {
    components: {
      Navbar,
      MyOffice,
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