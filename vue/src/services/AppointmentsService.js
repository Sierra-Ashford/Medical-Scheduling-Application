import axios from 'axios';
import {getDate, isEqual} from 'date-fns';

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
  },
  async getAppointmentsByDoctorIdAndDate(doctorId, date) {
    const appointments = await this.getAllAppointments();
    const filteredByDoctor = appointments.filter(appointment => appointment.doctorId === doctorId);
    const filteredByDate = filteredByDoctor.filter(appointment => isEqual(getDate(new Date(appointment.appointmentStartTime)), getDate(date)));
    const mappedToDates = filteredByDate.map(appointment => ({...appointment, startDateTime: new Date(appointment.appointmentStartTime), endDateTime: new Date(appointment.appointmentEndTime)}))
    //console.log('Calling from getAppointmentsByDoctorIdAndDate');
    //console.log(filteredByDoctor.map(appt => getDate(new Date(appt.appointmentStartTime))))
    //console.log({doctorId, date, appointments, filteredByDoctor, filteredByDate, mappedToDates});
    return mappedToDates;
  },
  async getAppointmentsByDoctorId(doctorId){
    const appointments = await this.getAllAppointments();
    const filteredByDoctor = appointments.filter(appointment => appointment.doctorId === doctorId);
    return filteredByDoctor;
  }
}