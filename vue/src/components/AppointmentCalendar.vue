<template>
    <div>
        <label for="dateToDisplay">Select Date to display</label>
        <input type="date" v-model="selectedDateString" @change="onSelectedDateChanged">
    </div>
    <h2>Schedule for {{ selectedDate }}</h2>

    <appointment-list :selectedDate="selectedDateString"></appointment-list>
</template>

<script>
import AppointmentList from './AppointmentList.vue';


</script>