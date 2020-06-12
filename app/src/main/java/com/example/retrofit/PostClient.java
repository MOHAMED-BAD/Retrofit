package com.example.retrofit;

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

//Api
public interface PostClient {
    //Retrofit GET with Query
    @GET("posts") //https://jsonplaceholder.typicode.com/posts
    Call<List<Post>> getPost();
    @POST("posts")
    Call <List<Post>>CreatePost(@Body Post post);
    //Another way
    @FormUrlEncoded
    @POST("posts")
    Call<List<Post>>CreatePost(
            @Field("userId")int userId,
            @Field("title")String title,
            @Field("body")String body);
    //Another way
    @FormUrlEncoded
    @POST("posts")
    Call <List<Post>>CreatePost(@FieldMap Map<String,String> map);
    //Update if exist and Create if not exist
    @PUT("posts/{id}")
    Call<List<Post>>UpdateOrCreate(@Path("id") int id , @Body Post post);
    // Update only if exist and Don't make any thing if not exist
    @PATCH("posts/{id}")
    Call <List<Post>>Update(@Path("id") int id , @Body Post post);
    //delete and return delete
    @DELETE("posts/{id}")
    Call <List<Post>>del(@Path("id") int id , @Body Post post);
    //delete
    @DELETE("posts/{id}")
    Call<Void>delete(@Path("id") int id , @Body Post post);





}
