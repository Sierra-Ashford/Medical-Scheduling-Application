<template>
  <section class="patient-profile-container">
    <h1>My Profile</h1>
    <div>
      <h2>{{ patient.firstName }} {{ patient.lastName }}</h2>
    </div>
    <div class="patient-info">
        <h3>Patient #{{ patient.patientId }}</h3>
        <h3>DOB: {{ patient.birthDate }}</h3>
    </div>
    <div class="doctor-info">
        <h3>Your Doctors</h3>
        <div v-if="doctors.length > 0">
            <div v-for="doctor in doctors" :key="doctor.id">
                <h4>Dr. {{ doctor.data.firstName }} {{ doctor.data.lastName }}</h4>
            </div>
        </div>
        <h2 v-else>No Doctor Assigned</h2>
    </div>
    <div class="contact-info">
        <h3>Contact Info</h3>
        <h3>Email: {{ patient.email }} </h3>
        <h3>Phone: {{ patient.phoneNumber }}</h3>
    </div>
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

<style scoped></style>
