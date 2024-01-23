<template>
  <section>
    <h1>My Prescriptions</h1>
    <div v-if="prescriptions.length > 0">
      <div v-for="prescription in prescriptions" :key="prescription.id">
        <p><strong>Name:</strong> {{ prescription.name }}</p>
        <p><strong>Doctor Who Prescribed:</strong> {{ displayDoctorName(prescription.doctorId) }}</p>
        <p><strong>Cost:</strong> {{ prescription.cost }}</p>
        <!-- Add any other details you want to display for each prescription -->
      </div>
    </div>
    <p v-else>No prescriptions available</p>
  </section>
</template>

<script>
import PrescriptionService from '../services/PrescriptionService';
import DoctorService from '../services/DoctorService';

export default {
data() {
  return {
    prescriptions: [],
    doctors: [] // Change to an object to store doctor information by ID
  };
},
computed: {
  patientId() {
    return this.$store.state.patientId;
  }
},
created() {
  // Call the method to fetch prescriptions when the component is created
  this.getPrescriptionByPatientId();
  this.getDoctorList();
},
methods: {
  getPrescriptionByPatientId() {
    PrescriptionService.getPrescriptionByPatientId(this.patientId).then(response => {
      if (response.status === 200) {
        this.prescriptions = response.data;
        // After getting prescriptions, fetch doctors for each prescription
      }
    });
  },
  getDoctorList() {
    DoctorService.getAllDoctors().then(response => {
      if (response.status === 200) {
        // Save doctor information in the object using doctor ID as the key
        this.doctors = response.data;
      }
    });
  },
  displayDoctorName(doctorId) {
      const matchingDoctor = this.doctors.find(doctor => doctor.doctorId === doctorId);
      return matchingDoctor ? `${matchingDoctor.firstName} ${matchingDoctor.lastName}` : 'Unknown Doctor';
    }
  },
};
</script>

<style scoped>
</style>
