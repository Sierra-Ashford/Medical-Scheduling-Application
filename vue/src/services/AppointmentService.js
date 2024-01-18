import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
})

export default {
    getAllAppointments() {
        return http.get('/appointments');
    },
    getAppointmentById(appointmentId) {
        return http.get(`/appointments/${appointmentId}`);
    },
    addNewAppointment(appointment) {
        return http.post('/appointments', appointment);
    },
    deleteAppointment(appointmentId) {
        return http.delete(`/appointments/${appointmentId}`);
    },
    modifyAppointment(appointmentId, appointment) {
        return http.put(`/appointments/${appointmentId}`, appointment);
    },
    getAppointmentsByDoctor(doctorId) {
        return http.get(`/appointments/doctor/${doctorId}`);
    },
    getAppointmentsByPatient(patientId) {
        return http.get(`/appointments/patient/${patientId}`);
    }
}