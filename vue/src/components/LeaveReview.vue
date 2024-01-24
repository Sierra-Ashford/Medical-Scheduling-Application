<template>
  <div class="container">
  </div>
  <div class="reviews-page-container">
    <div class="reviews-list">
      <div class="review-entry" v-for="review in reviews" :key="review.reviewId">
        <h3>{{ review.response }}</h3>

        <!-- Star display for each review entry -->
        <div class="star-display">
          <span v-for="star in 5" :key="star" class="star"
          :class="{ 'filled': star <= review.rating }">&#9733;</span>
        </div>

        <p class="review-excerpt">{{ review.reviewNote }}</p>
        <button @click="deleteReview(review.reviewId)" class="delete-review-button">Delete</button>
      </div>
    </div>

    <div class="review-form-container">
      <h2>Write a Review</h2>
      <input type="text" v-model="review.response" placeholder="Review Title" class="review-title-input" />
      <div class="star-rating">
        <span v-for="star in 5" :key="star" class="star" @click="setRating(star)"
          :class="{ 'filled': rating >= star }">&#9733;</span>
      </div>
      <textarea v-model="review.reviewNote" placeholder="Share about your experience"></textarea>
      <button @click="submitReview" class="submit-review">Submit</button>
    </div>
  </div>
</template>

<script>
import ReviewService from '../services/ReviewService';

export default {
  data() {
    return {
      reviews: [],
      rating: 0,
      review: {
        response: '', // Review Title
        reviewNote: '' // Review comment
      },
      doctorId: this.$route.params.doctorId,
    };
  },
  created() {
    this.fetchReviews();
  },
  methods: {
    fetchReviews() {
      ReviewService.getReviewsByDoctorId(this.doctorId)
      .then (response => {
        this.reviews = response.data
      })
      .catch(error => {
        console.error('Error fetching reviews:', error);
      });

    },
    deleteReview(reviewId) {
      ReviewService.deleteReview(reviewId)
        .then(() => {
          this.fetchReviews(); // Refresh the list of reviews
        })
        .catch(error => {
          console.error('Error deleting review:', error);
        });
    },

    setRating(selectedRating) {
      this.rating = selectedRating;
    },
    submitReview() {
      if (this.review.response && this.review.reviewNote && this.rating != 0) {
        ReviewService.createReview({
          doctorId: this.doctorId,
          response: this.review.response, // Review Title       
          rating: this.rating,
          reviewNote: this.review.reviewNote // Review comment
        })
        .then(() => {
          this.fetchReviews();
          this.review.response = '';
          this.review.reviewNote = '';
          this.rating = 0;
        })
        .catch(error => {
          console.error('Error submitting review:', error);
        });
      } else {
        alert('Please fill in your Review Title, Review, and Select a Rating.');
      }
    }
  }

};
</script>

<style scoped>

.reviews-page-container {
  display: flex;
  justify-content: center;
  align-items: flex-start; 
  max-width: 1200px;
  margin: auto;
  padding: 100px;
  gap: 20px;
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

.review-form-container {
  max-width: 60%;
  padding: 50px;
  text-align: center;
  border: 1px solid #ddd;
  border-radius: 5px;
}
.review-author-input {
  width: 100%;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.star-rating {
  margin-bottom: 20px;
}
.star-rating .star,
.star-display .star {
  font-size: 30px;
  color: #ccc;
  cursor: pointer;
}
.star-rating .star.filled,
.star-display .star.filled {
  color: #ffd055;
}


textarea {
  width: 100%;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  height: 200px;
}

.submit-review {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.submit-review:hover {
  background-color: #0056b3;
}

@media (max-width: 768px) {
  .reviews-page-container {
    flex-direction: column;
  }

  .reviews-list,
  .review-form-container {
    width: 100%
  }
}
</style>
