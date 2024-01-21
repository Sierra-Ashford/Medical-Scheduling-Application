

<template>
    <div class="find-doctor-container">
      <div class="search-bar">
        <input type="text" v-model="searchQuery" placeholder="Search by: Physician Name, Specialty, Office Hours" />
        <button @click="searchDoctors">🔍</button>
      </div>
      
      <div class="doctors-list">
        <div v-for="doctor in doctors" :key="doctor.doctor_id" class="doctor-card">
          <img :src="doctor.headshot" alt="Doctor's headshot" class="doctor-headshot" />
          <h2>{{ doctor.first_name }} {{ doctor.last_name }}</h2>
          <p>{{ doctor.specialty }}</p>
          <button @click="createAppointment(doctor.doctor_id)">Create Appointment</button>
          <button @click="viewReviews(doctor.doctor_id)">Reviews</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import DoctorService from '../services/DoctorService';
  
  export default {
    data() {
      return {
        searchQuery: '',
        doctors: [],
        loading: false
      };
      
    },
    methods: {
      async searchDoctors() {
        this.loading = true;
        try {
          const response = await axios.get(`http://localhost:9000/api/doctors?search=${this.searchQuery}`);
          this.doctors = response.data;
        } catch (error) {
          console.error('Error fetching doctors:', error);
        } finally {
          this.loading = false;
        }
      },
      createAppointment(doctorId) {
        // Logic to create an appointment
        console.log('Creating appointment for doctor ID:', doctorId);
        
      }
    },
    created() {
        DoctorService.getAllDoctors().then(response =>{
            this.doctors = response.data
        } )

    }
  };
  </script>
  
  <style scoped>
  .find-doctor-container {
    padding: 1rem;
  }
  
  .search-bar {
    margin-bottom: 2rem;
  }
  
  .search-bar input {
    width: 100%;
    padding: 0.5rem;
    margin-right: 0.5rem;
  }
  
  .search-bar button {
    padding: 0.5rem;
    cursor: pointer;
  }
  
  .doctors-list {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
  }
  
  .doctor-card {
    flex-basis: calc(33.333% - 1rem);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    text-align: center;
    padding: 1rem;
    border-radius: 8px;
  }
  
  .doctor-headshot {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    object-fit: cover;
    margin-bottom: 1rem;
  }
  
  .doctor-card button {
    background-color: #2720FF;
    color: white;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .doctor-card button:hover {
    background-color: #B6E2EF;
  }
  </style>
  
   

  
  