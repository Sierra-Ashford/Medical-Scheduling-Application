<template>
    
      <div id="set-my-hours" class="flex-container">
        <div class="form-container">
          <form v-on:submit.prevent="confirmChanges">
            <h1>Set My Hours</h1>
            <!-- <div role="alert" v-if="registrationErrors" class="error-message">
            {{ registrationErrorMsg }}
          </div> -->
            <div class="form-input-group">
              <label for="day-of-week" class="label">Select Day:</label>
              <select id="day-of-week" v-model="selectedDayOfWeek" required class="blue-border">
                <option value="MONDAY">Monday</option>
                <option value="TUESDAY">Tuesday</option>
                <option value="WEDNESDAY">Wednesday</option>
                <option value="THURSDAY">Thursday</option>
                <option value="FRIDAY">Friday</option>
                <option value="SATURDAY">Saturday</option>
                <option value="SUNDAY">Sunday</option>
  
              </select>
            </div>
            <div
              v-for="availability in availabilities[selectedDayOfWeek].filter(availability => availability.state !== 'removed')"
              :key="availability.availabilityId">
             
              <div class="form-input-group">
                <label :for="`start-time-${availability.availabilityId}`">Start Time</label>
                <input :id="`start-time-${availability.availabilityId}`" v-model="availability.startTime" type="text"
                  name="start-time" placeholder="00:00:00" required
                  @change.prevent="event => updateAvailability(availability, event)" />
              </div>
  
              <div class="form-input-group">
                <label :for="`end-time-${availability.availabilityId}`">End Time</label>
                <input :id="`end-time-${availability.availabilityId}`" v-model="availability.endTime" type="text"
                  name="end-time" placeholder="00:00:00"
                  @change.prevent="event => updateAvailability(availability)" />
              </div>
              <button @click.prevent="event => deleteAvailability(availability)">Delete</button>
            </div>
  
  
            <button @click.prevent="createAvailability">Add New Availability</button>
  
            <button type="submit" class="create-account-btn">Confirm All Changes</button>
  
  
          </form>
  
          <label>DEBUG</label>
          <div v-for="availability in availabilities[selectedDayOfWeek]" :key="availability.availabilityId">
            <code><pre>{{ JSON.stringify(availability, null, 2) }}</pre></code>
          </div>
        </div>

      </div>
  </template>
  
  <script>
  import availabilityService from '../services/AvailabilityService.js';
  
  export default {
    components: {
    },
    data() {
      return {
        selectedDayOfWeek: 'MONDAY',
        availabilities: {
          'SUNDAY': [],
          'MONDAY': [],
          'TUESDAY': [],
          'WEDNESDAY': [],
          'THURSDAY': [],
          'FRIDAY': [],
          'SATURDAY': [],
        },
  
      }
    },
    async beforeMount() {
      const doctorId = parseInt(localStorage.getItem('doctorId'));
      const allAvailability = await availabilityService.getAllAvailability();
      const availabilitySet = allAvailability
        .filter(availability => availability.doctorId === doctorId);
      console.log({ allAvailability, availabilitySet, doctorId, availabilityState: this.availabilities });
      availabilitySet
        .forEach(availability => this.availabilities[availability.dayOfTheWeek].push(availability));
    },
    computed: {
      navbarButtonText() {
        return "Log Out";
      },
      navbarButtonDestination() {
        return "logout";
      }
    },
    methods: {
      createAvailability() {
        this.availabilities[this.selectedDayOfWeek].push({
          doctorId: parseInt(localStorage.getItem('doctorId')),
          dayOfTheWeek: this.selectedDayOfWeek,
          startTime: "",
          endTime: "",
          state: "new"
        })
      },
      deleteAvailability(availability) {
        if (availability.availabilityId)
          availability.state = "removed";
        else {
          const index = this.availabilities[availability.dayOfTheWeek].indexOf(availability);
          console.log({ index });
          this.availabilities[availability.dayOfTheWeek].splice(index, 1);
        }
      },
      updateAvailability(availability) {
        if (availability.state !== "new")
          availability.state = "changed";
      },
      async confirmChanges() {
        console.log('confirmChanges called')
        // flatten this.availabilities 
        const availabilities = Object.values(this.availabilities).flat();
  
        // forEach on the array
        availabilities.forEach(async availability => {
          switch (availability.state) {
            case "new":
              await availabilityService.create(availability);
              break;
            case "changed":
              await availabilityService.updateAvailability(availability)
              break;
            case "removed":
              await availabilityService.deleteAvailability(availability)
              break;
          }
        });
  
        this.availabilities = {
          'SUNDAY': [],
          'MONDAY': [],
          'TUESDAY': [],
          'WEDNESDAY': [],
          'THURSDAY': [],
          'FRIDAY': [],
          'SATURDAY': [],
        };
        const doctorId = parseInt(localStorage.getItem('doctorId'));
        const allAvailability = await availabilityService.getAllAvailability();
        const availabilitySet = allAvailability
          .filter(availability => availability.doctorId === doctorId);
        console.log({ allAvailability, availabilitySet, doctorId, availabilityState: this.availabilities });
        availabilitySet
          .forEach(availability => this.availabilities[availability.dayOfTheWeek].push(availability));
        // based on status of availability
        // new -> call availabilityService.create
        // changed -> call availabilityService.update
        // removed -> call availabilityService.delete
      }
    }
  };
  </script>