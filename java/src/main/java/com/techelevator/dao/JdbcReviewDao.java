package com.techelevator.dao;

import com.techelevator.model.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcReviewDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Review mapRowToReview(SqlRowSet row){
        Review review = new Review();

        review.setReviewId(row.getInt("review_id"));
        review.setDoctorId(row.getInt("doctor_id"));
        review.setResponse(row.getString("response"));
        review.setRating(row.getInt("rating"));
        review.setReviewNote(row.getString("review_note"));

        return review;
    }

    @Override
    public Review getReviewById(int reviewId) {
        Review review = null;
        String sql = "SELECT * FROM reviews WHERE review_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, reviewId);
        if (row.next()) {
            review = mapRowToReview(row);
        }
        return review;
    }

    @Override
    public List<Review> getReviewsByDoctorId(int doctorId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE doctor_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, doctorId);
        while (row.next()) {
            reviews.add(mapRowToReview(row));
        }
        return reviews;
    }

    @Override
    public Review createReview(Review review) {
        String sql = "INSERT INTO reviews (doctor_id, response, rating, review_note) VALUES (?, ?, ?, ?) RETURNING review_id";
        int newReviewId = jdbcTemplate.queryForObject(sql, Integer.class,
                review.getDoctorId(), review.getResponse(), review.getRating(), review.getReviewNote());

        return getReviewById(newReviewId);
    }

    @Override
    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM reviews WHERE review_id = ?";
        jdbcTemplate.update(sql, reviewId);
    }

    @Override
    public Review updateReview(Review review) {
        String sql = "UPDATE reviews SET doctor_id = ?, response = ?, rating = ?, review_note = ? WHERE review_id = ?";
        jdbcTemplate.update(sql, review.getDoctorId(), review.getResponse(), review.getRating(), review.getReviewNote(), review.getReviewId());

        return getReviewById(review.getReviewId());
    }
}
