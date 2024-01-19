package com.techelevator.dao;

import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {
    Review getReviewById(int reviewId);
    List<Review> getReviewsByDoctorId(int doctorId);

    // CREATED THE CRUD

    Review createReview(Review review);
    void deleteReview(int reviewId);

    // optional
    Review updateReview(Review review);
}
