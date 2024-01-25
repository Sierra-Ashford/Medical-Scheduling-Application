<template>
  <section class="patient-profile-container">
    <h1 class="header">My Patient Dashboard</h1>
    <div class="patient-container">
        <h2 class="title">{{ patient.firstName }} {{ patient.lastName }}</h2>
        <h3>Patient #{{ patient.patientId }}</h3>
        <h3>DOB: {{ patient.birthDate }}</h3>
    </div>
    <div class="doctor-container">
        <h2 class="title">Your Doctors</h2>
        <div v-if="doctors.length > 0">
            <div v-for="doctor in doctors" :key="doctor.id">
                <h3>Dr. {{ doctor.data.firstName }} {{ doctor.data.lastName }}</h3>
            </div>
        </div>
        <h3 v-else>No Doctor Assigned</h3>
    </div>
    <div class="contact-container">
        <h2 class="title">Contact Info</h2>
        <h3>Email: {{ patient.email }} </h3>
        <h3>Phone: {{ patient.phoneNumber }}</h3>
    </div>
    <!-- <img src="../images/medical_image.jpg" alt=""> -->
  </section>
</template>
<script>
import PatientService from "../services/PatientService";
import DoctorService from "../services/DoctorService";
import AppointmentsService from "../services/AppointmentsService";
export default {
  data() {
    return {
      patient: {},
      doctors: [], // Change to an object to store doctor information by ID
      appointments: [],
    };
  },
  computed: {
    patientId() {
      return this.$store.state.patientId;
    },
  },
  created() {
    // Call the method to fetch prescriptions when the component is created
    this.getPatientByPatientId();
  },
  methods: {
    getPatientByPatientId() {
      PatientService.getPatientById(this.patientId).then((response) => {
        if (response.status === 200) {
          this.patient = response.data;
          this.getAppointmentsByPatientId();
        }
      });
    },
    getAppointmentsByPatientId() {
        AppointmentsService.getAppointmentsByPatientId(this.patientId)
        .then((response) => {
          if (response.status === 200) {
            this.appointments = response.data;
            console.log("Appointments for patient:", this.appointments);
            // Extract unique doctor IDs from appointments
            const uniqueDoctorIds = [...new Set(this.appointments.map((appointment) => appointment.doctorId))];
            // Fetch doctor details for each unique doctorId
            const doctorPromises = uniqueDoctorIds.map((doctorId) =>
              DoctorService.getDoctorById(doctorId)
            );
            Promise.all(doctorPromises)
              .then((doctors) => {
                // Now 'doctors' contains the details of unique doctors
                console.log("Doctors details:", doctors);
                this.doctors = doctors;
              })
              .catch((error) => {
                console.error("Error fetching doctors:", error);
                // Handle errors as needed
              });
          }
        })
        .catch((error) => {
          console.error("Error fetching appointments:", error);
          // Handle errors as needed
        });
    },
  },
};
</script>
<style scoped></style><style scoped>
.patient-profile-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "header header"
    "patient doctor"
    "contact img";
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
.patient-container {
  border: 2px solid #587DFF;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 15px;
 width: 400px;
 grid-area: patient;
}
.doctor-container {
  border: 2px solid #587DFF;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 15px;
 width: 400px;
 grid-area: doctor;
}
.contact-container {
  border: 2px solid #587DFF;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 15px;
 width: 400px;
 grid-area: contact;
}
.title {
    font-size: 150%;
}
</style>
