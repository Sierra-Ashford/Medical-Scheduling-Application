<template>
    <div id="set-my-hours" class="grid-container">
        <div class="header">
            <h1>Set Hours</h1>
        </div>
        <div class="form-container">
            <form v-on:submit.prevent="confirmChanges">
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
                <div v-for="availability in availabilitiesBySelectedDay" :key="availability.availabilityId">
                    <div class="form-input-group">
                        <label :for="`start-time-${availability.availabilityId}`" class="label">Start Time</label>
                        <input :id="`start-time-${availability.availabilityId}`" v-model="availability.startTime"
                            type="text" name="start-time" placeholder="00:00:00" required
                            @change.prevent="() => updateAvailability(availability)" class="blue-border" />
                    </div>
                    <div class="form-input-group">
                        <label :for="`end-time-${availability.availabilityId}`" class="label">End Time</label>
                        <input :id="`end-time-${availability.availabilityId}`" v-model="availability.endTime" type="text"
                            name="end-time" placeholder="00:00:00" @change="() => updateAvailability(availability)"
                            class="blue-border" />
                    </div>
                    <div class="delete">
                        <button @click.prevent="() => deleteAvailability(availability)"
                            class="create-account-btn">Delete</button>
                       
                    </div>
                </div>
                <div class="button-row">
                    <div class="add-new" >
                    <button @click.prevent="createAvailability" class="create-account-btn">Add New Availability</button>
                    </div >
                    <div class="confirm changes">
                    <button @click.prevent="confirmChanges" type="submit" id="confirmChanges"
                        class="create-account-btn">Save Changes</button>
                    </div>
                </div>
            </form>
             <!-- <label>DEBUG</label>
            <div v-for="availability in availabilities?.[selectedDayOfWeek] ?? []" :key="availability.availabilityId">
          <code><pre>{{ JSON.stringify(availability, null, 2) }}</pre></code>
            </div> -->
        </div>
        <img src="src\images\woman-talking-man-doctor-his-600nw-1513783433.webp">
    </div>
</template>
        

<script>
import availabilityService from '../services/AvailabilityService.js';
import { getDayNames } from '../utilities';

export default {
    components: {},
    data() {
        return {
            doctorId: null,
            selectedDayOfWeek: 'MONDAY',
            availabilities: null,
            availabilityClicked: false,
        };
    },
    async beforeMount() {
        this.doctorId = parseInt(localStorage.getItem('doctorId'));
        const availabilitySet = await availabilityService.getAllAvailabilityByDoctorId(this.doctorId);

        this.availabilities = getDayNames()
            .reduce((result, day) => {
                result[day] = availabilitySet.filter(availability => availability.dayOfTheWeek === day);
                return result;
            }, {});
    },
    computed: {
        availabilitiesBySelectedDay() {
            return this.availabilities?.[this.selectedDayOfWeek]
                ?.filter(availability => availability.state !== 'removed');
        },
        navbarButtonText() {
            return 'Log Out';
        },
        navbarButtonDestination() {
            return 'logout';
        },
    },
    methods: {
        onAvailabilityClick(availability) {
            // Handle availability click event
            this.availabilityClicked = true;
        },
        createAvailability() {
            this.availabilityClicked = true;
            this.availabilities[this.selectedDayOfWeek].push({
                doctorId: this.doctorId,
                dayOfTheWeek: this.selectedDayOfWeek,
                startTime: '',
                endTime: '',
                state: 'new',
            });
        },
        deleteAvailability(availability) {
            this.availabilityClicked = false;
            if (availability.availabilityId) {
                availability.state = 'removed';
            } else {
                const index = this.availabilities[availability.dayOfTheWeek].indexOf(availability);
                this.availabilities[availability.dayOfTheWeek].splice(index, 1);
            }
        },
        updateAvailability(availability) {
            if (availability.state !== 'new')
                availability.state = 'changed';
        },
        async confirmChanges() {
    console.log('confirmChanges called');

    try {
        // Flatten this.availabilities
        const availabilities = Object.values(this.availabilities).flat();

        // Use Promise.all to parallelize asynchronous operations
        await Promise.all(availabilities.map(async (availability) => {
            switch (availability.state) {
                case 'new':
                    await availabilityService.create(availability);
                    break;
                case 'changed':
                    await availabilityService.updateAvailability(availability);
                    break;
                case 'removed':
                    await availabilityService.deleteAvailability(availability);
                    break;
            }
        }));

        // Fetch updated availabilitySet after changes
        const availabilitySet = await availabilityService.getAllAvailabilityByDoctorId(this.doctorId);
        console.log({ availabilitySet });

        // Update this.availabilities based on the new availabilitySet
        this.availabilities = getDayNames()
            .reduce((result, day) => {
                result[day] = availabilitySet.filter(availability => availability.dayOfTheWeek === day);
                return result;
            }, {});

        // Show success message
        alert('Hours set successfully');


    } catch (error) {
        console.error('Error setting hours:', error);
        // Handle error if needed
    }
},
    },
};

</script>
<style scoped>
.grid-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas:
        "header header"
        "form image";
    gap: 20px;
    padding: 20px;
    align-items: center;
   
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.form-container {
    grid-area: form;
    max-width: 600px;
    width: 100%;
    padding: 20px;
    box-sizing: border-box;
    align-self: start;
}

img {
    grid-area: image;
    align-items: center;
    align-self: start;
}

.header {
    grid-area: header;
    text-align: center;
    margin-bottom: 20px;
}

.form-input-group {
    margin-bottom: 15px;
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
}

.error-message {
    color: #587DFF;
    margin-bottom: 15px;
}

select.blue-border {
    border: 2px solid #587DFF;
    padding: 15px;
    box-sizing: border-box;
    border-radius: 10px;
    color: darkgrey;
}

button.create-account-btn {
    color: #fff;
    text-decoration: none;
    font-weight: bold;
    padding: 10px 20px;
    background-color: #587DFF;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
 
}
.create-account-btn {
    margin-right: 10px; 
}

button.create-account-btn:hover {
    background-color: #B6E2EF;
}
.button-row {
        margin-top: 10px;
    }
button.create-account-btn+button.create-account-btn {
    margin-left: 10px;

}

.sub-form-group {
    margin-bottom: 10px;
}
.create-account-btn {
    margin-right: 10px; 
}

.confirm.changes {
    margin-top: 10px; 
}

</style>