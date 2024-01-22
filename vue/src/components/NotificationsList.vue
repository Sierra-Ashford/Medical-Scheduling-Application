
<template>
    <div class="notification_container">
        <h2>My Notifications</h2>

        <Notification v-for="notification of notifications" :key="notification" :notification="notification" />
        <div v-if="notifications != null && notifications.length == 0">
            <h4>No unread notifications</h4>

        </div>
        <button @click="resetRead">Reset notifications</button>
    </div>
</template>

<script>
import Notification from '../components/Notification.vue';
import AppointmentsService from '../services/AppointmentsService';



export default {
    name: 'NotificationContainer',
    components: {
        Notification,
    },
    props: {
        currentDoctorId: {
            type: Number,
            default: 2
        }
    },
    data() {
        return {
            notifications: null,
            appointments: null
        }
    },
    methods: {
        getAppointmentsForDoctor() {
            return AppointmentsService.getAppointmentsByDoctorId(this.currentDoctorId).then((results)=>{
            // return AppointmentsService.getAllAppointments().then((results) => {
                // let doctorAppointments = [];
                // for(let appt of results){
                //     if (appt.doctorId == this.currentDoctorId){
                //         doctorAppointments.push(appt);
                //     } 
                // }
                // this.appointments = doctorAppointments;
                this.appointments = results;
                this.buildNotificationsList(this.appointments);
            });
        },
        getAppointmentsForPatient() {
            return AppointmentsService.getAppointmentsByPatientId(this.currentPatientId).then((results)=>{
            // return AppointmentsService.getAllAppointments().then((results) => {
                // let patientAppointments = [];
                // for(let appt of results){
                //     if (appt.patientId == this.currentPatientId){
                //         patientAppointments.push(appt);
                //     } 
                // }
                // this.appointments = patientAppointments;
                this.appointments = results;
                this.buildNotificationsList(this.appointments);
            });
        },

        buildNotificationsList(appointmentsToDisplay) {
            this.notifications = [];
            let readAppointments = this.getReadAppointmentsFromLocalStorage();
            let filteredAppointments = appointmentsToDisplay.filter(function (el) {
                return readAppointments.indexOf(el.appointmentId) < 0;
            });
            for (let appointment of filteredAppointments) {
                this.notifications.push(
                    {
                        message: "A new appointment was booked for " + appointment.appointmentStartTime.split('T')[0],
                        appointmentId: appointment.appointmentId
                    })
            }
        },

        markNotificationAsRead(appointmentId) {
            this.updateReadNotifications(appointmentId);
            this.buildNotificationsList(this.appointments);
        },

        updateReadNotifications(appointmentId) {
            // if property doesnt exist in local storage, create it
            if (localStorage.getItem("readAppointments") == null) {
                localStorage.setItem("readAppointments", JSON.stringify([]));
            }

            let readAppointments = this.getReadAppointmentsFromLocalStorage();
            readAppointments.push(appointmentId)

            localStorage.setItem("readAppointments", JSON.stringify(readAppointments));
        },

        getReadAppointmentsFromLocalStorage() {
            return JSON.parse(localStorage.getItem("readAppointments")) || [];
        },

        resetRead() {
            localStorage.setItem("readAppointments", JSON.stringify([]));
            this.buildNotificationsList(this.appointments);
        }


    },
    beforeMount() {
        this.getAppointmentsForDoctor();
    }
}
</script>
<style scoped>
.notification_container {
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;

}
</style>