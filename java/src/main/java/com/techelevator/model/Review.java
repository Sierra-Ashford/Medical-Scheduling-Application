package com.techelevator.model;

public class Review {
    private int reviewId;
    private int officeId;
    private String response;
    private int rating;
    private String reviewNote;

    public Review() {};
    public Review(int reviewId, int officeId, String response, int rating, String reviewNote) {
        this.reviewId = reviewId;
        this.officeId = officeId;
        this.response = response;
        this.rating = rating;
        this.reviewNote = reviewNote;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewNote() {
        return reviewNote;
    }

    public void setReviewNote(String reviewNote) {
        this.reviewNote = reviewNote;
    }
}
