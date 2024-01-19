import axios from 'axios';

export default {

  create(doctor) {
    return axios.post('/doctors', doctor);
  },
  getAllDoctors(){
    return axios.get('/doctors/all');
  },
  getDoctorById(doctorId){
    return axios.get('/doctors/' + doctorId);
  },
  getDoctorsByOfficeId(officeId){
    return axios.get('/doctors/' + officeId);
  }

}
