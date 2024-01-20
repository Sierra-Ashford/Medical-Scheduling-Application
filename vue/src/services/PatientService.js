import axios from 'axios';

export default {

  create(patient) {
    return axios.post('/patients', patient)
  },
  getAllPatients(){
    return axios.get('/patients');
  },
  getPatientById(patientId){
    return axios.get('/patients/' + patientId);
  },
  

}