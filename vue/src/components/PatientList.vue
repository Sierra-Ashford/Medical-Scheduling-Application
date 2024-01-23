<template>
  <section>
    <h1>My Patients</h1>
    <div v-if="patients.length > 0">
      <div v-for="patient in patients" :key="patient.id">
        <p>
          <strong>Name:</strong> {{ patient.data.firstName }}
          {{ patient.data.lastName }}
        </p>
        <p><strong>Date of Birth:</strong> {{ patient.data.birthDate }}</p>
        <p><strong>Phone Number:</strong> {{ patient.data.phoneNumber }}</p>
        <p><strong>Email:</strong> {{ patient.data.email }}</p>

        <!-- Button to route to PrescriptionView with patientId -->
        <button @click="viewPrescriptions(patient.data.patientId)">
          View Prescriptions
        </button>
      </div>
    </div>
    <p v-else>No Patients Available</p>
  </section>
</template>

<script>
import AppointmentsService from "../services/AppointmentsService";
import PatientService from "../services/PatientService";
export default {
  data() {
    return {
      patients: [
        // Add more prescription objects as needed
      ],
      appointments: [],
    };
  },
  computed: {
    doctorId() {
      return this.$store.state.doctorId;
    },
  },
  created() {
    // Call the method to fetch prescriptions when the component is created
    this.getAppointmentsByDoctorId();
  },
  methods: {
    // getAppointmentsByDoctorId(){
    //     AppointmentsService.getAppointmentsByDoctorId(this.doctorId).then(response=> {
    //         if (response.status == 200){
    //             this.appointments = response.data;
    //         }
    //     })
    // },
    getAppointmentsByDoctorId() {
      AppointmentsService.getAppointmentsByDoctorId(this.doctorId)
        .then((response) => {
          if (response.status === 200) {
            const patientIds = response.data.map(
              (appointment) => appointment.patientId
            );
            // Use the patientIds to fetch patient details
            const patientPromises = patientIds.map((patientId) =>
              PatientService.getPatientById(patientId)
            );
            Promise.all(patientPromises)
              .then((patients) => {
                // Now 'patients' contains the details of all patients
                this.patients = patients;
              })
              .catch((error) => {
                console.error("Error fetching patients:", error);
                // Handle errors as needed
              });
          }
        })
        .catch((error) => {
          console.error("Error fetching appointments:", error);
          // Handle errors as needed
        });
    },
    viewPrescriptions(patientId) {
      // Dispatch the mutation to set the selectedPatientId in the store
      this.$store.commit("SET_SELECTED_PATIENT_ID", patientId);
      this.$router.push({ name: "prescriptions" });
    },
  },
};
</script>

<style scoped></style>
