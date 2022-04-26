package com.techelevator.model;

public class UserIdDTO {
    private String user_id;

    public long getUser_id() {
        return Long.parseLong(user_id);
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
