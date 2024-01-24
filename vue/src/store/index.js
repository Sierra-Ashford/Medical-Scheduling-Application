import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      doctorId: parseInt(localStorage.getItem('doctorId')) || null,
      patientId: parseInt(localStorage.getItem('patientId')) || null,
      currentLoggedInPatient: "",
      selectedPatientId: null,
      officeId: 1,
      doctorMode: !!localStorage.getItem('doctorId'),
      patientMode: !!localStorage.getItem('patientId'),
    },
    getters: {
      getRole: state => {
        return state.doctorMode ? 'ROLE_DOCTOR' : state.patientMode ? 'ROLE_PATIENT' : null;
      },
      getCurrentUser: state => {
        return state.user;
      }
      },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
      
        localStorage.setItem('user', JSON.stringify(user));
      },
      SET_DOCTOR_ID(state, id) {
        console.log({from:'SET_DOCTOR_ID', state, id});
        state.doctorId = id;
        state.patientId = null;
        state.doctorMode = true;
        state.patientMode = false;
        localStorage.setItem('doctorId', JSON.stringify(id));
        localStorage.removeItem('patientId');
      },
      SET_PATIENT_ID(state, id) {
        console.log({from:'SET_PATIENT_ID', state, id});
        state.patientId = id;
        state.doctorId = null;
        state.patientMode = true;
        state.doctorMode = false;
        localStorage.setItem('patientId', JSON.stringify(id));
        localStorage.removeItem('doctorId');
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        localStorage.removeItem('doctorId');
        localStorage.removeItem('patientId');
        state.token = '';
        state.user = {};
        state.doctorId = null;
        state.patientId = null;
        axios.defaults.headers.common = {};
      },
      SET_ERROR_MESSAGE(state, errorMessage) {
        state.errorMessage = errorMessage;
      }
    },
  });
  return store;
}
