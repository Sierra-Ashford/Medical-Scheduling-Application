import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import BookAppointmentView from '../views/BookAppointmentView.vue'
import FindDoctorView from '../views/FindDoctorView.vue';
import MyOffice from '../views/MyOfficeView.vue';
import SetMyHours from '../views/SetMyHoursView.vue';
import PatientHomeView from '../views/PatientHomeView.vue';
import DoctorHomeView from '../views/DoctorHomeView.vue';
import DoctorReviews from '../views/DoctorReviewsView.vue';
import LeaveReview from '../views/LeaveReviewView.vue';

import PrescriptionView from '../views/PrescriptionView.vue';
import MyPatientsView from '../views/MyPatientsView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/MyReviews',
    name: 'MyReviews',
    component: DoctorReviews,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/set-my-hours',
    name: 'SetMyHours',
    component: SetMyHours,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/MyOffice',
    name: 'MyOffice',
    component: MyOffice,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/patient',
    name: 'patientHome',
    component: PatientHomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/doctor',
    name: 'doctorHome',
    component: DoctorHomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/',
    name: 'home',
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/book-appointment",
    name: "bookAppointment",
    component: BookAppointmentView,
  }, 
  {
    path: "/find",
    name: "find",
    component: FindDoctorView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/reviews/:doctorId",
    name: "leave-review",
    component: LeaveReview,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/prescriptions",
    name: "prescriptions",
    component: PrescriptionView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/myPatients",
    name: "myPatients",
    component: MyPatientsView,
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return { name: "login" };
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
