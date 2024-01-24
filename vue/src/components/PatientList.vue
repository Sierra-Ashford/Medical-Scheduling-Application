<template>
  <section>
    <div class="grid-container">
    <h1 class="header">My Patients</h1>
    <div v-if="patients.length > 0">
      <div v-for="patient in patients" :key="patient.id" class="prescription-container">
        <p>
          <strong>Name:</strong> {{ patient.firstName }}
          {{ patient.lastName }}
        </p>
        <p><strong>Date of Birth:</strong> {{ patient.birthDate }}</p>
        <p><strong>Phone Number:</strong> {{ patient.phoneNumber }}</p>
        <p><strong>Email:</strong> {{ patient.email }}</p>
        <div class="button-container">
        <!-- Button to route to PrescriptionView with patientId -->
        <button @click="viewPrescriptions(patient.patientId)" class="button">
          View Prescriptions
        </button>
      </div>
      </div>
    </div>
    <p v-else>No Patients Available</p>
    <img src="src\images\pngtree-tiny-pharmacists-with-prescription-drugs-for-patients-png-image_6319570.png" alt="">
  </div>
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
  async beforeMount() {
    // Call the method to fetch prescriptions when the component is created
    await this.getAppointmentsByDoctorId();
  },
  methods: {
    // getAppointmentsByDoctorId(){
    //     AppointmentsService.getAppointmentsByDoctorId(this.doctorId).then(response=> {
    //         if (response.status == 200){
    //             this.appointments = response.data;
    //         }
    //     })
    // },
    async getAppointmentsByDoctorId() {
      try {
        // getAppointmentsByDoctorId doesn't return a promise anymore, you get the appointments directly
        const appointments = await AppointmentsService.getAppointmentsByDoctorId(this.doctorId);
        const patientPromises = appointments.map(appointment => PatientService.getPatientById(appointment.patientId));
        const patientResponses = await Promise.all(patientPromises);
        this.patients = Object.values(patientResponses
          .map(response => response.data)
          .reduce((patients, patient) => {
            patients[patient.patientId] = patient;
            return patients;
          }, {}));

        console.log({doctorId: this.doctorId, appointments, patientPromises, patientResponses, patients: this.patients})
      }
      catch (error) {
        console.error("Error fetching patients:", error);
      }


        // .then((response) => {
        //   if (response.status === 200) {
        //     const patientIds = response.data.map(
        //       (appointment) => appointment.patientId
        //     );
        //     // Use the patientIds to fetch patient details
        //     const patientPromises = patientIds.map((patientId) =>
        //       PatientService.getPatientById(patientId)
        //     );
        //     Promise.all(patientPromises)
        //       .then((patients) => {
        //         // Now 'patients' contains the details of all patients
        //         this.patients = patients;
        //       })
        //       .catch((error) => {
        //         console.error("Error fetching patients:", error);
        //         // Handle errors as needed
        //       });
        //   }
        // })
        // .catch((error) => {
        //   console.error("Error fetching appointments:", error);
        //   // Handle errors as needed
        // });
    },
    viewPrescriptions(patientId) {
      // Dispatch the mutation to set the selectedPatientId in the store
      this.$store.commit("SET_SELECTED_PATIENT_ID", patientId);
      this.$router.push({ name: "prescriptions" });
    },
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
.button {
    color: #fff;
    text-decoration: none;
    font-weight: bold;
    padding: 10px 20px;
    background-color: #587DFF;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    margin-top: 10px;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  }
  .button:hover {
    background-color: #B6E2EF;
  }
  .prescription-container {
  border: 2px solid #587DFF;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 15px;
 width: 400px;
 grid-area: med;
}

.button-container {
  display: flex;
  justify-content: center;
}
</style>
