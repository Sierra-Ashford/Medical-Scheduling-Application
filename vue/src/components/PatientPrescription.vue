<template>
  <section class="grid-container">
    <div class="header">
    <h1>My Prescriptions</h1>
  </div>
    <div v-if="prescriptions.length > 0">
      <div v-for="prescription in prescriptions" :key="prescription.id" class="prescription-container">
        <p><strong>Medication:</strong> {{ prescription.name }}</p>
        <p><strong>Doctor Who Prescribed:</strong> {{ displayDoctorName(prescription.doctorId) }}</p>
        <p><strong>Cost: $</strong> {{ prescription.cost }}</p>
        <!-- Add any other details you want to display for each prescription -->
      </div>
    </div>
    <p v-else>No prescriptions available</p>
  
  <img src="src\images\telemedicine-digital-pharmacy-concept-online-600nw-2138185031.webp" alt="">
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
.grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "header header"
    "med img"; 
  gap: 20px; 
  padding: 20px; 
  align-items: center;
  justify-items: center; 
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.header {
  text-align: center;
  margin-bottom: 20px; 
  grid-area: header;
  
}
.prescription-container {
  border: 2px solid #587DFF;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 15px;
 width: 400px;
 grid-area: med;
}
img {
  grid-area: img;
  align-self: start;
  height: auto;
  margin: 0 auto; 
  display: block;
}
</style>
