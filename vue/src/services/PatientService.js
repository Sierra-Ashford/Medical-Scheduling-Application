import axios from 'axios';

export default {

  create(patient) {
    return axios.post('/patients', patient)
  },
  getAllPatients(){
    return axios.get('/patients/all');
  },
  getPatientById(patientId){
    return axios.get('/patients/' + patientId);
  },
  

}