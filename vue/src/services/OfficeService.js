import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
})

export default {
    getOfficeById(officeId) {
        return http.get(`/offices/${officeId}`);
    },

    getAllOffices() {
        return http.get('/offices');
    },

    addOffice(officeData) {
        return http.post('/offices', officeData);
    },

    updateOffice(officeId, officeData) {
        return http.put(`/offices/${officeId}`, officeData);
    },

    getOfficesByDoctor(doctorId) {
        return http.get(`/offices/doctor/${doctorId}`);
    }
}