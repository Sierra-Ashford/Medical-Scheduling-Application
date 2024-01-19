package com.techelevator.controller;

import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewDao reviewDao;

    @GetMapping("/{reviewId}")
    public Review getReviewById(@PathVariable int reviewId) {
        return reviewDao.getReviewById(reviewId);
    }

    @RequestMapping(path = "/doctors/{doctorId}", method = RequestMethod.GET)
    public List<Review> getReviewsByDoctorId(@PathVariable int doctorId) {
        return reviewDao.getReviewsByDoctorId(doctorId);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Review review) {
        try {
            reviewDao.createReview(review);
            return new ResponseEntity<>("Review made successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to make review " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/{reviewId}", method = RequestMethod.DELETE)
    public void deleteReview(@PathVariable int reviewId) {
        reviewDao.deleteReview(reviewId);
    }

    @RequestMapping(path = "/{reviewId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateReview(@RequestBody Review review, @PathVariable int reviewId) {
        try {
            review.setReviewId(reviewId);
            reviewDao.updateReview(review);
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update review " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
