package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    JsonPlaceHolderApi api;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(JsonPlaceHolderApi.class);

        deletePost();
//        updatePost();
        //putPost

//        getPosts();

//        createPost();

    }
    public void deletePost(){

        Call<Void> call = api.deletePost(96);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    result.setText("Post has been deleted");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });



    /*public void updatePost(){
        Post post = new Post ("New And Old Texts Are Shown Here");
        Call<Post> call = api.patchPost(post, 96);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Post post1 = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + post1.getId() + "\n";
                content += "UserId: " + post1.getUserId() + "\n";
                content += "Title: " + post1.getPostTitle() + "\n";
                content += "Text: " + post1.getText() + "\n";

                result.setText(content);

            } */

    /*public void putPost(){
        Post post = new Post (10, null, "New Text");
        Call<Post> call = api.putPost(96, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Post post1 = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + post1.getId() + "\n";
                content += "UserId: " + post1.getUserId() + "\n";
                content += "Title: " + post1.getPostTitle() + "\n";
                content += "Text: " + post1.getText() + "\n";

                result.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText("Failure");
            }
        });*/
    }

    public void getPosts(){
        Call<List<Post>> request = api.getPosts(new Integer[]{3,5,7,9,10},  "id", "desc");

        request.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    List<Post> posts = response.body();
                    for (Post post : posts){
                        String content = "";
                        content += "ID: " + post.getId() + "\n";
                        content += "User ID" + post.getUserId() + "\n";
                        content += "Title " + post.getPostTitle() + "\n";
                        content += "Body " + post.getText() + "\n";
                        result.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //Ошибка
                Toast.makeText(MainActivity.this,

                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createPost(){
        Post post = new Post(23,"IT Academy Title", "IT Academy Text");
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "IT Academy Title");
        fields.put("body", "IT Academy Text");
        Call<Post> call = api.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Post post1 = response.body();
                String content = "";
                content += "Code: " + response.code() + " \n";
                content += "ID: " + post1.getId() + "\n";
                content += "UserId: " + post1.getUserId() + "\n";
                content += "Title: " + post1.getPostTitle() + "\n";
                content += "Text: " + post1.getText() + "\n";

                result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    //Retrofit

    //HTTP запросов
    //Volley
    //JSONObject
    //HTTP
    //Все они устарели кроме Retrofit

    //Object - Post
    //Mockup
    //REQUEST - List<Post>

    //Interface
    //https://jsonplaceholder.typicode.com/posts
    //https://jsonplaceholder.typicode.com/posts/1/comments

}