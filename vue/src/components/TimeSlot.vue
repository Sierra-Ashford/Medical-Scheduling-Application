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
          {{ timeslotString }}
        </h3>
        <button v-on:click="() => bookAppointment()">Book this
          time</button>
        <!-- <button v-if=appointment.isBooked v-bind:disabled="data.isBooked">UNAVAILABLE</button> -->
      </div>

    </div>

  </div>
</template>

<script>
import appointmentsService from '../services/AppointmentsService.js';
import { getHours, getMinutes, format } from 'date-fns';
import { createStringFromDate } from '../utilities';

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
    },
    notes:{
      type: String,
      default: null
    }
  },
  computed: {
    timeslotString() {
      //   //console.log(this.timeslot);

      //   const timeslotStartTimeHours = getHours(this.timeslot.startDateTime).toString();
      //   const timeslotStartTimeMinutes = getMinutes(this.timeslot.startDateTime).toString().padStart(2, '0');
      //   const timeslotEndTimeHours = getHours(this.timeslot.endDateTime).toString();
      //   const timeslotEndTimeMinutes = getMinutes(this.timeslot.endDateTime).toString().padStart(2, '0');

      //   return `${timeslotStartTimeHours}:${timeslotStartTimeMinutes} to ${timeslotEndTimeHours}:${timeslotEndTimeMinutes}`;
      
      const timeslotStartTime = this.timeslot.startDateTime;
      const timeslotEndTime = this.timeslot.endDateTime;

      const formatTime = (time) => {
        const hours = getHours(time);
        const minutes = getMinutes(time).toString().padStart(2, '0');
        const period = hours >= 12 ? 'PM' : 'AM';
        const formattedHours = (hours % 12 || 12).toString();

        return `${formattedHours}:${minutes} ${period}`;
      };

      const formattedStartTime = formatTime(timeslotStartTime);
      const formattedEndTime = formatTime(timeslotEndTime);

      return `${formattedStartTime} to ${formattedEndTime}`;

    }

  },
  methods: {
    async bookAppointment() {
      const appointment = {
        doctorId: this.doctorId,
        patientId: this.patientId,
        appointmentStartTime: createStringFromDate(this.timeslot.startDateTime),
        appointmentEndTime: createStringFromDate(this.timeslot.endDateTime, "yyyy-MM-dd'T'HH':'mm':'ss"),
        notes: this.notes
      };
      const appt = await appointmentsService.create(appointment);
      //console.log('creating appt');
      console.log({ appointment, roundtripAppointment: appt, timeslot: this.timeslot });

      //console.log('emitting event');
      this.$emit('appt-booked');
    }
  }
};

</script>

<style scoped></style>

