package com.techelevator.model;

import java.time.LocalDateTime;

public class UserHistoryDTO {
    private long user_id;
    private String first_name;
    private String title;
    private String author;
    private String genre;
    private int difficulty;
    private String format;
    private int minutes_read;
    private String finished_book;
    private String review;
    private int session_points;
    private LocalDateTime date_logged;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getMinutes_read() {
        return minutes_read;
    }

    public void setMinutes_read(int minutes_read) {
        this.minutes_read = minutes_read;
    }

    public String getFinished_book() {
        return finished_book;
    }

    public void setFinished_book(String finished_book) {
        this.finished_book = finished_book;
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

    public LocalDateTime getDate_logged() {
        return date_logged;
    }

    public void setDate_logged(LocalDateTime date_logged) {
        this.date_logged = date_logged;
    }
}
