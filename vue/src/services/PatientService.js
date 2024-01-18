import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {

  create(patient) {
    return axios.post('/patients', patient)
  },
  getAllPatients() {
    return http.get('/patients');
  },
  getPatientById(patientId) {
    return http.get(`/patients/${patientId}`);
  },
  addNewPatient(patient) {
    return http.post('/patients', patient);
  },
  deletePatient(patientId) {
    return http.delete(`/patients/${patientId}`);
  },
  modifyPatient(patientId, patient) {
    return http.put(`/patients/${patientId}`, patient);
  }

}