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
        review.setOfficeId(row.getInt("doctor_id"));
        review.setResponse(row.getString("response"));
        review.setRating(row.getInt("rating"));
        review.setReviewNote(row.getString("review_note"));

        return review;
    }

    @Override
    public Review getReviewById(int reviewId) {
        Review review = null;
        String sql = "SELECT * FROM review WHERE review_id = ?";
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
}
