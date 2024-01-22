import {add, set} from 'date-fns';

function getDaysInRange(startDate, endDate) {
    var date = new Date(startDate);
    var days = [];
    while(date < endDate){
        days.push(new Date(date));
        date.setDate(date.getDate() + 1);
    }
    return days;
}


export default {
    
    generateTimeslots(startDate, endDate, increment) {
        const incrementsPerHour = 60 / increment;
        const timeslotIntervalIndicies = [...Array(incrementsPerHour).keys()];
        const hoursOfDay = [...Array(24).keys()];
    
        const timeslots = hoursOfDay
            .flatMap(hours => timeslotIntervalIndicies
                .map(intervalIndex => ({hours, minutes: Math.floor(intervalIndex * increment)})));
    
        const timeslotsPerDayOfMonth = getDaysInRange(startDate, endDate)
            .flatMap(date => timeslots.map(({hours, minutes}) => { 
                const startDateTime = set(date, {hours, minutes});
                return { startDateTime, endDateTime: add(startDateTime, {minutes: increment})};
            }));
    
        return timeslotsPerDayOfMonth;
    }
    
}