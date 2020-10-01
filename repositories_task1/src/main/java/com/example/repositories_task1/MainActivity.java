package com.example.repositories_task1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button request;
    RecyclerView recyclerView;
    EditText userName;
    GitHubApi api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        request = (Button) findViewById(R.id.request);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        userName = (EditText) findViewById(R.id.editText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(GitHubApi.class);


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                makeRequest();
            }
        });





    }

    public void makeRequest(){
        if(!TextUtils.isEmpty(userName.getText())){
            Call<List<Repo>> call = api.getRepos(userName.getText().toString());
            call.enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    progressBar.setVisibility(View.GONE);
                  if(response.isSuccessful()){
                      List<Repo> repoList = response.body();
                      RepoAdapter repoAdapter = new RepoAdapter(MainActivity.this, repoList);
                      recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                      recyclerView.setAdapter(repoAdapter);
                  }
                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {

                }
            });
        }
    }

}
