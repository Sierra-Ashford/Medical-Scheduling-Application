import axios from 'axios';

export default {

  create(patient) {
    return axios.post('/patients', patient)
  }

}