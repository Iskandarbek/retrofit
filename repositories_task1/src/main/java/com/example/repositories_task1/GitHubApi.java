package com.example.repositories_task1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("{username}/repos")
    Call<List<Repo>> getRepos(@Path("username") String name);



}
