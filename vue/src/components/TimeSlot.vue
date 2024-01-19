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
                            {{ data.startTime }}:00 - {{ data.endTime }}:00
                        </h3>
                        <button v-if=!data.isBooked v-on:click="bookAppointment">Book this time</button>
                        <button v-if=data.isBooked v-bind:disabled="data.isBooked">UNAVAILABLE</button>
                    </div>

                </div>

    </div>
</template>

<script>
 export default {
    props: {
      data: {
        type: Object,
        default: null,
      }
    
    },
    methods:{
        bookAppointment(){
            console.log("Booking time for " + this.data.startTime)
        }
    }
  };

</script>