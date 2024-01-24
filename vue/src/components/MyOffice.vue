<template>
  <div class="grid-container">
    <div class="header">
      <h1>My Office</h1>
    </div>
    <div class="left-panel">
      <img src="src\images\map.png" class="office-image">
    </div>
    <div v-if="!editMode" class="officeDetails">
      <h2> MedConnect</h2>
      <p> {{ officeDetails.address }}</p>
      <p> {{ officeDetails.phoneNumber }}</p>
      <p>Office Hours: {{ officeDetails.officeHr }}</p>
      <button @click="toggleEditMode" class="button">Edit Office Details</button>
    </div>
    <div v-else class="right-panel">
      <form @submit.prevent="updateOfficeDetails" class="updateForm">
        <label for="address" class="label">Address:</label>
        <input v-model="officeDetails.address" id="address" required class="blue-border">

        <label for="phoneNumber" class="label">Phone Number:</label>
        <input v-model="officeDetails.phoneNumber" id="phoneNumber" required class="blue-border">

        <label for="officeHr" class="label">Office Hours:</label>
        <input v-model="officeDetails.officeHr" id="officeHr" required class="blue-border">

        <button type="submit" class="button">Save</button>
      </form>
    </div>
  </div>
</template>
<script>
import OfficeService from '../services/OfficeService';
import AuthService from '../services/AuthService';

export default {
  data() {
    return {
      editMode: false,
      officeDetails: {
        address: '',
        phoneNumber: '',
        officeHr: '',

      },
    };
  },
  async beforeMount() {
    try {
      await this.fetchOfficeDetails();

    } catch (userError) {
      console.error("Error fetching user details:", userError);

    }
  },

  methods: {
    async fetchOfficeDetails() {
      try {
        const office = await OfficeService.getOfficeById(this.$store.state.officeId);

        this.officeDetails.address = office.data.address;
        this.officeDetails.phoneNumber = office.data.phoneNumber;  
        this.officeDetails.officeHr = office.data.officeHr; 

      } catch (officeError) {
        console.error("Error fetching doctor's office details:", officeError);
      }
    },
    toggleEditMode() {
      this.editMode = !this.editMode;
    },

    async updateOfficeDetails() {
  try {
    //const currentUserID = this.$store.getters.getCurrentUser.id;
  
    //if (currentUserID) {
      //const office = await OfficeService.getOfficeById(this.$store.state.officeId);
      //this.officeDetails.id = office.data.officeId;

      // Update office details
      await OfficeService.update(this.$store.state.officeId, this.officeDetails);

      // Fetch updated office details
      //await this.fetchOfficeDetails();

      // Move the console.log statement here

      console.log('Office details updated successfully');
      this.editMode = false;
    //} else {
      //console.error("User ID is null.");
      // Handle the situation where the user ID is null
    //}
  } catch (error) {
    console.error("Error updating office details:", error);
  }
},
  },
};
</script>

<style scoped>
.updateForm {
  max-width: 600px;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
}
.blue-border {
  border: 2px solid #587DFF;
  padding: 15px;
  width: 100%;
  box-sizing: border-box;
  border-radius: 10px;
  color: darkgrey;
}
.label {
  color: darkgrey;
  /* Light gray label text color */
}
.button {
    color: #fff;
    text-decoration: none;
    font-weight: bold;
    padding: 10px 20px;
    background-color: #587DFF;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  }
  
 .button:hover {
    background-color: #B6E2EF;
  }
.header {
  text-align: center;
  margin-bottom: 20px; 
  grid-area: header;/* Adjust the margin as needed */
  
}


.grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "header header"
    "img form"; /* Two equal columns */
  gap: 20px; /* Adjust the gap between columns */
  padding: 20px; /* Add padding to the grid container */
  align-items: center; /* Center items vertically */
}

.left-panel {
  grid-area: img;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.right-panel{
  grid-area: form;
}

.office-image {
  width: 100%; /* Adjust the image width */
  max-width: 500px; /* Set a maximum width for the image */
  height: auto; /* Maintain aspect ratio */
}
p{
  font-size: large;
}
.officeDetails {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.details-container {
  max-width: 400px; /* Adjust the max-width as needed */
  padding: 20px;
  box-sizing: border-box;
  background-color: #f8f8f8; /* Light background color */
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Box shadow for a subtle effect */
}

</style>