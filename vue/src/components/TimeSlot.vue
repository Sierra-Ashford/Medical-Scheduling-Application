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
          {{getTimeslotString(timeslot)}}
        </h3>
        <button
          v-on:click="() => { bookAppointment(appointment); $emit('appt-booked'); console.log('event emitted') }">Book this
          time</button>
        <!-- <button v-if=appointment.isBooked v-bind:disabled="data.isBooked">UNAVAILABLE</button> -->
      </div>

    </div>

  </div>
</template>

<script>
import appointmentsService from '../services/AppointmentsService.js';
import { getHours, getMinutes } from 'date-fns';

export default {
  props: {
    timeslot: {
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
    getTimeslotString(timeslot) {
      console.log(timeslot);

      const timeslotStartTimeHours = getHours(timeslot.startDateTime).toString();
      const timeslotStartTimeMinutes = getMinutes(timeslot.startDateTime).toString().padStart(2, '0');
      const timeslotEndTimeHours = getHours(timeslot.endDateTime).toString();
      const timeslotEndTimeMinutes = getMinutes(timeslot.endDateTime).toString().padStart(2, '0');

      return `${timeslotStartTimeHours}:${timeslotStartTimeMinutes} to ${timeslotEndTimeHours}:${timeslotEndTimeMinutes}`;
    },
    async bookAppointment(appointment) {
      await appointmentsService.create({
        doctorId: this.doctorId,
        patientId: this.patientId,
        appointmentStartTime: appointment.startTime,
        appointmentEndTime: appointment.endTime,
        notes: ''
      });
    }
  }
};

</script>

<style scoped></style>