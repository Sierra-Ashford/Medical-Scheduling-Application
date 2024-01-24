import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {
    getReviewById(reviewId) {
        return http.get(`/reviews/${reviewId}`);
    },
    
    getReviewsByDoctorId(doctorId) {
        return http.get(`/reviews/doctors/${doctorId}`);
    },

    createReview(review) {
        return http.post('/reviews', review);
    },

    deleteReview(reviewId) {
        return http.delete(`/reviews/${reviewId}`);
    },

    updateReview(reviewId, review) {
        return http.put(`/reviews/${reviewId}`, review);
    }
};