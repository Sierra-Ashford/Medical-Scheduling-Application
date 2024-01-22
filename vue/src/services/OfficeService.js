import axios from 'axios';

export default {
    create(officeData) {
        return axios.post('/offices', officeData);
      },
    getAllOffices(){
        return axios.get("/offices/all");
    },
    getOfficeById(officeId){
        return axios.get('/offices/' + officeId);
    },
    getOfficeByDoctorId(doctorId){
        return axios.get(`/offices/doctor/${doctorId}`);
    },
    update(officeId, officeDetails) {
        return axios.put(`/offices/update/${officeId}`, officeDetails);
      },
    getOfficeByUserId(userId){
        return axios.get('/offices/user/' + userId);
    },
}