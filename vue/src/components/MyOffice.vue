<template>
    <div>
      <h1>My Office</h1>
      <div v-if="officeDetails">
        <p><strong>Name:</strong> {{ officeDetails.name }}</p>
        <p><strong>Address:</strong> {{ officeDetails.address }}</p>
        <p><strong>Phone Number:</strong> {{ officeDetails.phoneNumber }}</p>
        <p><strong>Cost per Hour:</strong> ${{ officeDetails.costPerHour }}</p>
        <p><strong>Office Hours:</strong> {{ officeDetails.officeHours }}</p>
      </div>
      <div v-else>
        <p>No office details available.</p>
      </div>
    </div>
  </template>
  
  <script>
  import officeService from '../services/OfficeService' 
  
  export default {
    data() {
      return {
        officeDetails: null,
      };
    },
    methods: {
      fetchOfficeDetails() {
        officeService.getOfficeByDoctorId()// how to get doctors ID? 
          .then(response => {
            this.officeDetails = response.data;
          })
          .catch(error => {
            console.error("Error fetching office details:", error);
          });
      },
    },
  };
  </script>
  
  <style scoped>
  
  </style>