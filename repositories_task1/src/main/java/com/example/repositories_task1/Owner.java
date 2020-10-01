package com.example.repositories_task1;

import com.google.gson.annotations.SerializedName;

public class Owner {
    private  String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
