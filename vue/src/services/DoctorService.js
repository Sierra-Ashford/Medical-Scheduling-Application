import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {

  create(doctorData) {
    return axios.post('/doctors', doctorData, { params: { officeID: doctorData.officeID } });
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
