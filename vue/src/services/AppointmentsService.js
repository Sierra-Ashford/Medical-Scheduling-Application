import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {

  async create(appointment) {
    const response = await http.post('/appointments', appointment);
    return response.data;
  },
  async getAllAppointments() {
    const response = await http.get('/appointments');
    return response.data;
  },
  async getAppointmentsById(appointmentId) {
    const response = await http.get(`/appointments/${appointmentId}`);
    return response.data;
  },
  async deleteAppointment(appointment) {
    const response = await http.delete(`/availability/${appointment.appointmentId}`);
    return response.data;
  },
  async updateAppointment(appointment) {
    const response = await http.put(`/availability/${appointment.appointmentId}`, appointment);
    return response.data;
  }

}