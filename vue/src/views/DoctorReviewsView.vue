<template>
  <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
  <div class="reviews-page-container">
    <h1>My Reviews</h1>
    <div class="reviews-list">
      <div class="review-entry" v-for="review in reviews" :key="review.id">
        <h3>{{ review.response }}</h3>
        <div class="star-display">
          <span v-for="star in 5" :key="star" class="star" :class="{ 'filled': star <= review.rating }">&#9733;</span>
        </div>
        <p class="review-excerpt">{{ review.reviewNote }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '../components/NavBar.vue'
import ReviewService from '../services/ReviewService';

export default {
  components: {
    Navbar,
  },
  data() {
    return {
      reviews: [],
      doctorId: null, // Default value, or you can set it to null or any other appropriate default
    };
  },
  computed: {
    navbarButtonText() {
      return "Log Out";
    },
    navbarButtonDestination() {
      return "logout";
    }
  },
  created() {
    // Parse the doctorId from localStorage to an integer
    this.doctorId = parseInt(localStorage.getItem('doctorId'), 10);

    // Fetch reviews for the doctorId
    this.fetchReviews();
  },
  methods: {
    fetchReviews() {
      console.log(`Fetching reviews for doctor ID: ${this.doctorId}`);
      ReviewService.getReviewsByDoctorId(this.doctorId)
        .then(response => {
          console.log('Reviews fetched:', response.data); 
          this.reviews = response.data;
        })
        .catch(error => {
          console.error('Error fetching reviews:', error);
        });
    },
  }
};
</script>

  <style scoped>
  .reviews-page-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 1200px;
  margin: auto;
  padding: 100px;
}

.reviews-header {
  text-align: center;
}

.reviews-list {
  max-width: 80%;
}

.review-entry {
  background-color: #f4f4f4;
  padding: 30px;
  margin-bottom: 20px;
  border-radius: 5px;
}

.review-excerpt {
  margin-bottom: 20px;
}

.star-display {
  font-size: 30px;
  color: #ccc;
}

.star-display .star.filled {
  color: #ffd055;
}
</style>