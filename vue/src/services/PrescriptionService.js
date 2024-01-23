import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {

  createPrescription(prescription) {
    return http.post('/prescriptions', prescription)
  },
  getPrescriptionByPatientId(patientId) {
    return http.get(`/prescriptions/patient/${patientId}`);
  },
  deletePrescription(prescriptionId) {
    return http.delete(`/prescriptions/${prescriptionId}`);
  },
  updatePrescription(prescriptionId, prescription) {
    return http.put(`/prescriptions/${prescriptionId}`, prescription);
  }

}
