import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {

  create(doctor) {
    return axios.post('/doctors', doctor)
  },
  getAllDoctors() {
    return http.get('/doctors');
  },
  getDoctorById(doctorId) {
    return http.get(`/doctors/${doctorId}`);
  },
  addNewDoctor(doctor) {
    return http.post('/doctors', doctor);
  },
  deleteDoctor(doctorId) {
    return http.delete(`/doctors/${doctorId}`);
  },
  modifyDoctor(doctorId, doctor) {
    return http.put(`/doctors/${doctorId}`, doctor);
  }

}
