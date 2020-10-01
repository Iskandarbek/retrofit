package com.example.retrofit;

import android.content.Intent;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    //List<Post>


    //posts?userId=1
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] id,//=5 // Integer для того что бы мы могли ввести Null В конструктаре
//            @Query("userId") int id2,
            @Query("_sort") String sortId,//=id
            @Query("_order") String order//=desc
    );
    //Call<List<Post>>

    @GET("posts")
    Call<List<Post>> getPosts();


    //Post
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
      @Field("userId") int userId,
      @Field("title") String title,
      @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String > fields);
    //3 вида post   запроса

    //BASE_URL/posts/?userId=23&title=Academy&body=Text

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id")int id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Body Post post, @Path("id") int id);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id")int id);

}
