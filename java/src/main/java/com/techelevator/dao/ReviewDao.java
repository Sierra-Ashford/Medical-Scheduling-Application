package com.techelevator.dao;

import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {
    Review getReviewById(int reviewId);
    List<Review> getReviewsByOfficeId(int officeId);

}
