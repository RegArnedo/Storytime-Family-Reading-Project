package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class UserDashInfo {

    private Long user_id;
    private String first_name;
    private int books_read;
    private Integer total_minutes_read;
    private String current_book;
    private Integer points_balance;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getBooks_read() {
        return books_read;
    }

    public void setBooks_read(int books_read) {
        this.books_read = books_read;
    }

    public Integer getTotal_minutes_read() {
        return total_minutes_read;
    }

    public void setTotal_minutes_read(Integer total_minutes_read) {
        this.total_minutes_read = total_minutes_read;
    }

    public String getCurrent_book() {
        return current_book;
    }

    public void setCurrent_book(String current_book) {
        this.current_book = current_book;
    }

    public Integer getPoints_balance() {
        return points_balance;
    }

    public void setPoints_balance(Integer points_balance) {
        this.points_balance = points_balance;
    }
}
