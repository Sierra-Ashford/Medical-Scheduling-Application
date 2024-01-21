<template>
    <div class="notification">
        <p class="notification-message">
            {{ notification.message }}</p>
        <button class="notification-button" v-on:click="markAsRead">Mark as Read</button>

    </div>
</template>

<script>
export default {
    props: {
        notification: {
            type: Object,
            default: null
        }
    },
    methods: {
        markAsRead() {
            this.$parent.markNotificationAsRead(this.notification.appointmentId);
        }
    }
}
</script>

<style scoped>
.notification {
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

.notification-message {
    display: flex;
    flex-direction: column;
    flex-basis: 60%;
    flex: 1
}

.notification-button {
    font-size: 0.8em;
    height: 20px;
    border-radius: 3px;
}
</style>