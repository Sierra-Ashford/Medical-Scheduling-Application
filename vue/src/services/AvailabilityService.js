import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {

  async create(availability) {
    const response = await http.post('/availability', availability);
    return response.data
  },
  async getAllAvailability() {
    const response = await http.get('/availability');
    return response.data;
  },
  async getAvailabilityById(availabilityId) {
    const response = await http.get(`/availability/${availabilityId}`);
    return response.data;
  },
  
  async deleteAvailability(availability) {
    const response = await http.delete(`/availability/${availability.availabilityId}`);
    return response.data;
  },
  async updateAvailability(availability) {
    const response = await http.put(`/availability/${availability.availabilityId}`, availability);
    return response.data;
  }

}