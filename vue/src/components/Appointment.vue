<template>
    <div class="appointment">
        <p class="appointment-message">
            {{ getDisplayTime(appointment.appointmentStartTime) }} - {{ getDisplayTime(appointment.appointmentEndTime) }} <em>{{ appointment.notes }}</em> </p>
           
    </div>
</template>


<script>
import { getHours, getMinutes } from 'date-fns';
export default{
    props: {
        appointment:{
            type:Object,
            default:null
        }
    },
    methods:{
        getDisplayTime(dateTime){
            let hour = getHours(dateTime);
            let convertedHour = this.convertHourFromMilitaryTime(hour);
            let minutes = getMinutes(dateTime);
            let suffix = this.getTimeSuffix(hour);
            return convertedHour + ":" + minutes + " " + suffix;
        },
        convertHourFromMilitaryTime(hour) {
            hour = (hour > 12)? hour -12 : hour;
            hour = (hour == '00')? 12 : hour;
            return hour;
        },
        getTimeSuffix(hour){
            return (hour >= 12)? 'pm' : 'am';
        }
    }
   
}








</script>

<style scoped>
.appointment {
    border-radius: 10px;
    padding: 4px 12px;
    width: 500px;
    background-color: #587DFF;
    color: white;
    font-weight: 600;
    margin-bottom: 20px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    justify-content: center;
}

.appointment-message {
    display: flex;
    flex-direction: column;
    flex-basis: 60%;
    flex: 1
}
</style>