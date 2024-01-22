import axios from 'axios';
import {intlFormat} from 'date-fns';
import { getDayName } from '../utilities';

const extractHoursAndMinutesRegExp = /(\d\d):(\d\d):\d\d/g;

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {

  async create(availability) {
    console.log('in create availability');
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
  },
  async getAllAvailabilityByDoctorId(doctorId) {
    const availabilities = await this.getAllAvailability();
    const filteredByDoctor = availabilities.filter(availability => availability.doctorId === doctorId);

    return filteredByDoctor;
  },

  async getAvailabilityByDoctorIdAndDayOfWeek(doctorId, date) {
    // turn day of week number into string representation for API
    const dayOfWeek = getDayName(date.getDay());
    console.log({dayOfWeek, day: date.getDay(), date});

    const availabilities = await this.getAllAvailability();
    const filteredByDoctor = availabilities.filter(availability => availability.doctorId === doctorId);
    const filteredByDayOfWeek = filteredByDoctor.filter(availability => availability.dayOfTheWeek === dayOfWeek);

    // convert startTime and endTime to startTimeHours, startTimeMinutes, endTimeHours and endTimeMinutes
    // 00:00:00 -> hours and minutes numbers
    const mappedToHoursAndMinutes = filteredByDayOfWeek.map(availability => {
      //console.log(availability);
      const [startTimeHours, startTimeMinutes] = [...availability.startTime.matchAll(extractHoursAndMinutesRegExp)]
        .at(0)
        .slice(1)
        .map(n => parseInt(n));
      const [endTimeHours, endTimeMinutes] = [...availability.endTime.matchAll(extractHoursAndMinutesRegExp)]
        .at(0)
        .slice(1)
        .map(n => parseInt(n));

      return {...availability, startTimeHours, startTimeMinutes, endTimeHours, endTimeMinutes}
    });

    return mappedToHoursAndMinutes;
  }

}