<template>
    <div>
        <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
        <component v-bind:is="prescriptionComponent"/>
    </div>
</template>

<script>
import Navbar from "../components/NavBar.vue";
import DoctorPrescription from "../components/DoctorPrescription.vue";
import PatientPrescription from "../components/PatientPrescription.vue";

export default {
  components: {
    Navbar,
    DoctorPrescription,
    PatientPrescription
  },
  computed: {
    role() {
        return this.$store.getters.getRole
    },
    prescriptionComponent() {
      return this.role === 'ROLE_PATIENT' ? PatientPrescription : DoctorPrescription;
    },
    navbarButtonText() {
        return "Log Out";
    },
    navbarButtonDestination() {
        return "logout";
    },
  },
};
</script>
