import {format} from 'date-fns';

export const getDayNames = () => ['SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'];

export function getDayName(day) {
    switch(day) {
        case 0: return 'SUNDAY';
        case 1: return 'MONDAY';
        case 2: return 'TUESDAY';
        case 3: return 'WEDNESDAY';
        case 4: return 'THURSDAY';
        case 5: return 'FRIDAY';
        case 6: return 'SATURDAY';
    }
}

export function createDateFromString(date) {
    const regex = /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2})/g;
    const [year, month, day, hours, minutes] = [...date.matchAll(regex)].at(0).slice(1).map(n => parseInt(n));
    //console.log({year, month: month - 1, day, hours, minutes, date: new Date(year, month - 1, day, hours, minutes), string: date});
    return new Date(year, month - 1, day, hours, minutes);
}

export function createStringFromDate(date) {
    return format(date, "yyyy-MM-dd'T'HH':'mm':'ss");
}