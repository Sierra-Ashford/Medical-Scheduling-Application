<template>
  <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
  <div>
      <SetMyHours></SetMyHours>>
  </div>
</template>

<script>

import SetMyHours from '../components/SetMyHours.vue'
import Navbar from '../components/NavBar.vue'

export default {
  components: {
      
      SetMyHours,
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
}
</script>