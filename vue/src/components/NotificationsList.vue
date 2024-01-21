
<template>
    <div class="notification__container">
        <Notifications v-for="notification of notifications"
            :key="notification"
            />
    </div>
</template>

<script>
import Notifications from '../components/Notifications.vue';



export default {
    name: 'NotificationContainer',
    components: {
        Notifications,
    },
    props: {
        notificationsList: {
            type: Array,
            required: true
        }
    }
}
</script>
<style>


</style>