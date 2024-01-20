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
        const hours = [...Array(24).keys()];
    
        const timeslots = hours
            .flatMap(hour => timeslotIntervalIndicies
                .map(intervalIndex => ({hour, minute: Math.floor(intervalIndex * increment)})));
    
        const timeslotsPerDayOfMonth = getDaysInRange(startDate, endDate)
            .flatMap(date => timeslots.map(({hour, minute}) => { 
                const newDate = new Date(date);
                newDate.setHours(hour);
                newDate.setMinutes(minute);
                return newDate;
            }));
    
        return timeslotsPerDayOfMonth;
    }
    
}