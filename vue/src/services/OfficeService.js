import axios from 'axios';

export default {
    create(officeData) {
        return axios.post('/offices', officeData);
      },
    getAllOffices(){
        return axios.get("/offices");
    },
    getOfficeById(officeId){
        return axios.get('/offices/' + officeId);
    },
    update(officeId, officeDetails) {
        return axios.put(`/offices/${officeId}`, officeDetails);
      },
}