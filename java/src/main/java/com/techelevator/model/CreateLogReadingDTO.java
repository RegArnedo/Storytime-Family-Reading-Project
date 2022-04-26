package com.techelevator.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CreateLogReadingDTO {

    private Long user_id;
    private int book_id;
    private int minutes_read;
    private String reading_format;
    private int times_read;
    private String review;
    private int session_points;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getMinutes_read() {
        return minutes_read;
    }

    public void setMinutes_read(int minutes_read) {
        this.minutes_read = minutes_read;
    }

    public String getReading_format() {
        return reading_format;
    }

    public void setReading_format(String reading_format) {
        this.reading_format = reading_format;
    }

    public int getTimes_read() {
        return times_read;
    }

    public void setTimes_read(int times_read) {
        this.times_read = times_read;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getSession_points() {
        return session_points;
    }

    public void setSession_points(int session_points) {
        this.session_points = session_points;
    }
}
