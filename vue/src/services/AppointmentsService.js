import axios from 'axios';
import {getDate} from 'date-fns';
import { createDateFromString } from '../utilities';
// import {getDate, isEqual} from 'date-fns'

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
  async getAppointmentsByDoctorId(doctorId) {
    const appointments = await this.getAllAppointments();
    const filteredByDoctor = appointments.filter(appointment => appointment.doctorId === doctorId);
    return filteredByDoctor;
  },
  async getAppointmentsByDoctorIdAndDateString(doctorId, date) {
    const appointments = await this.getAllAppointments();
    const filteredByDoctor = appointments.filter(appointment => appointment.doctorId === doctorId);
    const filteredByDate = filteredByDoctor.filter(appointment => 
      new Date(appointment.appointmentStartTime).getUTCDay() == new Date(date).getUTCDay()
    );
    const mappedToDates = filteredByDate.map(appointment => ({ ...appointment, startDateTime: new Date(appointment.appointmentStartTime), endDateTime: new Date(appointment.appointmentEndTime) }))
    //console.log('Calling from getAppointmentsByDoctorIdAndDate');
    //console.log(filteredByDoctor.map(appt => getDate(new Date(appt.appointmentStartTime))))
    //console.log({doctorId, date, appointments, filteredByDoctor, filteredByDate, mappedToDates});
    return mappedToDates;
  },
  async getAppointmentsByDoctorIdAndDate(doctorId, date) {
    const appointments = await this.getAllAppointments();
    const filteredByDoctor = appointments.filter(appointment => appointment.doctorId === doctorId);
    const filteredByDate = filteredByDoctor.filter(appointment => {
      // console.log({
      //   date,
      //   startTimeFromAPI: appointment.appointmentStartTime,
      //   startTimeUsingStringCtor: new Date(appointment.appointmentStartTime),
      //   startTimeUsingNumberCtor: createDateFromString(appointment.appointmentStartTime),
      //   getDateFromApptUsingString: getDate(new Date(appointment.appointmentStartTime)),
      //   getDateFromApptUsingNumber:getDate(createDateFromString(appointment.appointmentStartTime)),
      //   getDateFromInput: getDate(date) })
      return getDate(createDateFromString(appointment.appointmentStartTime)) === getDate(date)});
    const mappedToDates = filteredByDate.map(appointment => ({
      ...appointment,
      startDateTime: createDateFromString(appointment.appointmentStartTime),
      endDateTime: createDateFromString(appointment.appointmentEndTime)}));
    return mappedToDates;
  },
  async getAppointmentsByPatientId(patientId) {
    const appointments = await this.getAllAppointments();
    const filteredByPatient = appointments.filter(appointment => appointment.patientId === patientId);
    return filteredByPatient;
  }
}