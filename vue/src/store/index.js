import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      doctorId: undefined,
      patientId: undefined,
      currentLoggedInPatient: "",
      selectedPatientId: null,
    },
    getters: {
      getRole: state => {
        if(Object.keys(state.user).length != 0){
          return state.user.authorities[0].name;
        }
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
        state.doctorId = id;
        localStorage.setItem('doctorId', JSON.stringify(id));
      },
      SET_PATIENT_ID(state, id) {
        state.patientId = id;
        localStorage.setItem('patientId', JSON.stringify(id));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        localStorage.removeItem('doctorId');
        localStorage.removeItem('patientId');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_ERROR_MESSAGE(state, errorMessage) {
        state.errorMessage = errorMessage;
      },
      SET_SELECTED_PATIENT_ID(state, patientId) {
        state.selectedPatientId = patientId;
      },
    },
  });
  return store;
}
