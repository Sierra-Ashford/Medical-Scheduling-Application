<template>
  <div class="container">
  </div>
  <div class="reviews-page-container">
    <div class="reviews-list">
      <div class="review-entry" v-for="review in reviews.slice().reverse()" :key="review.id">
        <h3>{{ review.author }}</h3>

        <!-- Star display for each review entry -->
        <div class="star-display">
          <span v-for="star in 5" :key="star" class="star"
          :class="{ 'filled': star <= review.rating }">&#9733;</span>
        </div>

        <p class="review-excerpt">{{ review.excerpt }}</p>
      </div>
    </div>

    <div class="review-form-container">
      <h2>Write a Review</h2>
      <input type="text" v-model="review.author" placeholder="Your Name" class="review-author-input" />
      <div class="star-rating">
        <span v-for="star in 5" :key="star" class="star" @click="setRating(star)"
          :class="{ 'filled': rating >= star }">&#9733;</span>
      </div>
      <textarea v-model="review.comment" placeholder="Share about your experience"></textarea>
      <button @click="submitReview" class="submit-review">submit</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      reviews: [
        // Example reviews
        {
          id: 1,
          author: 'Nick Perry',
          excerpt: 'First time seeing Dr. Garcia, but already a fan! Super thorough and answered all my questions.'
        },
        {
          id: 2,
          author: 'Linda Hudson',
          excerpt: 'Could not ask for a better dermatologist. Dr. Garcia is meticulous, explains everything clearly, and my skin has never looked better.'
        }
  
      ],
      nextReviewId: 3,
      rating: 0,
      review: {
        author: '',
        comment: ''
      },
    };
  },
  methods: {
    setRating(selectedRating) {
      this.rating = selectedRating;
    },
    submitReview() {
      if (this.review.author.trim() && this.review.comment.trim() && this.rating !== 0) {
        this.reviews.push({
          id: this.nextReviewId++,
          author: this.review.author,
          rating: this.rating,
          excerpt: this.review.comment
        });
        this.review.author = '';
        this.review.comment = '';
        this.rating = 0;
      } else {
        alert('Please fill in your name, review, and select a rating.');
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
