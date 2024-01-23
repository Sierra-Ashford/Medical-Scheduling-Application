<template>
  <section>
    <h1>Prescription for {{ patient.firstName }} {{ patient.lastName }}</h1>
    <div>
        <!-- List of prescriptions for the selected patient -->
      <div v-if="prescriptions.length > 0">
        <div v-for="prescription in prescriptions" :key="prescription.id">
          <p><strong>Name:</strong> {{ prescription.name }}</p>
          <p><strong>Cost:</strong> {{ prescription.cost }}</p>
          <!-- <button @click="updatePrescription(prescription.id)">Update</button> -->
          <button @click="deletePrescription(prescription.prescriptionId)">Delete</button>
        </div>
      </div>
      <p v-else>No prescriptions available</p>
    </div>

    <div>
        <!-- Form for creating a new prescription -->
      <form @submit.prevent="createPrescription">
        <label>Name:</label>
        <input v-model="newPrescription.name" required />
        <label>Cost:</label>
        <input v-model="newPrescription.cost" type="number" required />
        <button type="submit">Prescribe this Medicine</button>
      </form>
    </div>
  </section>
</template>

<script>
import PrescriptionService from '../services/PrescriptionService';
import PatientService from '../services/PatientService';

export default {
  data() {
    return {
      newPrescription: {
        name: '',
        cost: null,
      },
      prescriptions: [],
      patient: {}, // Object to store patient information
    };
  },
  computed: {
    patientId() {
      // Assume patientId is in the store
      return this.$store.state.selectedPatientId;
    },
    doctorId() {
      return this.$store.state.doctorId;
    }
  },
  mounted() {
    // Fetch patient and prescriptions when the component is mounted
    this.getPatient();
    this.getPrescriptions();
  },
  methods: {
    getPatient() {
      // Fetch patient information
      // You need to implement PatientService.getPatientById method
      // Example: 
      PatientService.getPatientById(this.patientId)
        .then(response => {
          this.patient = response.data;
        })
        .catch(error => {
          console.error('Error fetching patient:', error);
        });
    },
    getPrescriptions() {
      // Fetch prescriptions for the selected patient
      PrescriptionService.getPrescriptionByPatientId(this.patientId)
        .then(response => {
          this.prescriptions = response.data;
        })
        .catch(error => {
          console.error('Error fetching prescriptions:', error);
        });
    },
    createPrescription() {
      // Call your service method to create a prescription
      PrescriptionService.createPrescription({
        patientId: this.patientId, // Make sure to pass the patientId from the store
        doctorId: this.doctorId,
        name: this.newPrescription.name,
        cost: this.newPrescription.cost,
      })
        .then(() => {
          // After creating, refresh the list
          this.getPrescriptions();

          // Clear the form fields
          this.newPrescription.name = '';
          this.newPrescription.cost = null;
        })
        .catch(error => {
          console.error('Error creating prescription:', error);
        });
    },

    updatePrescription(prescriptionId) {
      // Call your service method to update a prescription
      // You need to implement PrescriptionService.updatePrescription method
      // Example: PrescriptionService.updatePrescription(prescriptionId, { name: 'Updated Medicine', cost: 25 })
      //   .then(() => {
      //     // After updating, refresh the list
      //     this.getPrescriptions();
      //   })
      //   .catch(error => {
      //     console.error('Error updating prescription:', error);
      //   });
    },
    deletePrescription(prescriptionId) {
      // Call your service method to delete a prescription
      PrescriptionService.deletePrescription(prescriptionId)
        .then(() => {
          // After deleting, refresh the list
          this.getPrescriptions();
        })
        .catch(error => {
          console.error('Error deleting prescription:', error);
        });
    },
  },
};
</script>

<style scoped>
/* Add your component-specific styles here */
</style>