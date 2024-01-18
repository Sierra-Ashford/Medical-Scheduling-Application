import axios from 'axios';

export default {

  create(doctor) {
    return axios.post('/doctors', doctor)
  }

}
