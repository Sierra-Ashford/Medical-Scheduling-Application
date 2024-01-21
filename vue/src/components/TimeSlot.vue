<template>
  <div>
    <div style="height:80px; 
                width:400px; 
                border:2px solid #587DFF; 
                border-radius:10px; 
                margin-bottom:20px;
                padding:10px 30px;
                align-content: center;">

      <div style="display:flex; 
                            flex-direction: row; 
                            flex-wrap: wrap;">
        <h3 style="display:flex; 
                            flex-direction:column; 
                            flex-basis:60%; 
                            flex:1">
          {{ appointment.startTime.toTimeString().split(' ')[0] }} to {{ appointment.endTime.toTimeString().split(' ')[0]
          }}
        </h3>
        <button
          v-on:click="() => { bookAppointment(appointment); $emit('appt-booked'); console.log('event emitted') }">Book this
          time</button>
        <button v-if=appointment.isBooked v-bind:disabled="data.isBooked">UNAVAILABLE</button>

      </div>

    </div>
  </div>


  <footer>

 <!-- route to patient home with below confirmation message when patient chooses time slot for appointment -->
        <!-- on-click route to home-->
        <!-- sent doctor notification upon appointment being booked with them -->


       <router-link :to="{ name: 'patientHome' }"> <button v-on:click-="showAlert"> Submit </button></router-link>

       

  </footer>
</template>

<script>
import appointmentsService from '../services/AppointmentsService.js';

export default {
  props: {
    appointment: {
      type: Object,
      default: null,
    },
    patientId: {
      type: Number,
      default: null
    },
    doctorId: {
      type: Number,
      default: null
    }

  },
  methods: {
    async bookAppointment(appointment) {
      await appointmentsService.create({
        doctorId: this.doctorId,
        patientId: this.patientId,
        appointmentStartTime: appointment.startTime,
        appointmentEndTime: appointment.endTime,
        notes: ''
      });


    }
  },
  // <!-- on-click give patient success message or error code -->
  showAlert() {
    alert('We look forward to caring for you! If you need assistance with your recent booking please contact our office.')
  }
};

</script>

<style scoped></style>