<template>
  <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div class="leave-review-page">
      <h1>Doctor Reviews</h1>
      <LeaveReview :doctorId="doctorId" />
    </div>
  </template>
  
  <script>
  import LeaveReview from '../components/LeaveReview.vue';
  import Navbar from '../components/NavBar.vue'
  
  export default {
    components: {
      LeaveReview,
      Navbar,
    },
    computed: {
      doctorId() {
        return parseInt(this.$route.params.doctorId);
      },
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
  .leave-review-page {
    padding: 30px;
    display: flex;
    justify-content: flex-end;
    flex-direction: column;
    align-items: center;
  }
  
  .leave-review-page h1 {
    text-align: center;
    margin-bottom: 20px;
  }
  </style>
  
