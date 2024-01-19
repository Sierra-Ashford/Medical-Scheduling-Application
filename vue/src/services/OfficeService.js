import axios from 'axios';

export default {

    getAllOffices(){
        return axios.get("/offices/all");
    },
    getOfficeById(officeId){
        return axios.get('/offices/' + officeId);
    },
    getOfficeByDoctorId(doctorId){
        return axios.get('/offices/' + doctorId);
    }
}