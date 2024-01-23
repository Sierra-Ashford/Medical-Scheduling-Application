<template>
    <div>
        <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
        <PatientList/>
    </div>
</template>

<script>
import Navbar from "../components/NavBar.vue";
import PatientList from "../components/PatientList.vue";

export default {
  components: {
    Navbar,
    PatientList
  },
  computed: {
    navbarButtonText() {
        return "Log Out";
    },
    navbarButtonDestination() {
        return "logout";
    },
  },
};
</script>
