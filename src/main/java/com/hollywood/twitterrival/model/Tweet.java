package com.hollywood.twitterrival.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Tweet {

    @SerializedName("created_at")
    private Date createdTime;
    private Long id;
    private String text;

    public Date getCreatedTime() {
        return createdTime;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "createdTime=" + createdTime +
                ", id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
