package com.carservice.car_service_tracker.model;

import java.time.LocalDateTime;

public class Review {
    private int rating;        // 1-5
    private String comment;
    private LocalDateTime reviewedAt;

    public Review() {}

    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
        this.reviewedAt = LocalDateTime.now();
    }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime reviewedAt) { this.reviewedAt = reviewedAt; }

    public String getStars() {
        return "★".repeat(rating) + "☆".repeat(5 - rating);
    }
}
