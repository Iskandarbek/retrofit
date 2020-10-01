package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private int id;
    @SerializedName("title")
    private String postTitle;
    @SerializedName("body")
    private String text;

    public Post(String postText){
        this.text = postText;
    }


    public Post(int userId, String postTitle, String text) {
        this.userId = userId;
        this.postTitle = postTitle;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getText() {
        return text;
    }


}
