package com.techelevator.model;

public class UserBook extends Book {

    private int timesRead;

    public UserBook() {
        super();
    }

    public int getTimesRead() {
        return timesRead;
    }

    public void setTimesRead(int timesRead) {
        this.timesRead = timesRead;
    }

}