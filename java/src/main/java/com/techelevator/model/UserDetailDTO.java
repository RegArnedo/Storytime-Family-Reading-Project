package com.techelevator.model;


import java.time.LocalDateTime;

public class UserDetailDTO {

    private int book_id;
    private String title;
    private String author;
    private String genre;
    private int minutes_per_book;
    private int times_read_total;
    private LocalDateTime last_read;

    public LocalDateTime getLast_read() {
        return last_read;
    }

    public void setLast_read(LocalDateTime last_read) {
        this.last_read = last_read;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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

    public int getMinutes_per_book() {
        return minutes_per_book;
    }

    public void setMinutes_per_book(int minutes_per_book) {
        this.minutes_per_book = minutes_per_book;
    }

    public int getTimes_read_total() {
        return times_read_total;
    }

    public void setTimes_read_total(int times_read_total) {
        this.times_read_total = times_read_total;
    }
}
