package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofit.Post;
import com.example.retrofit.PostClient;
import com.example.retrofit.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Retrofit retrofit;
    PostClient postClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tv=findViewById(R.id.body);
         Gson gson=new GsonBuilder().serializeNulls().create();
         HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient okHttpClient=new OkHttpClient.Builder()
                 .addInterceptor(loggingInterceptor)
                 .build();
        //Builder
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient) //Must be Taken
                .build();
        postClient=retrofit.create(PostClient.class);
               // getPost();
               CreatePost();
                //UpdatePut();
                //UpdatePatch();
    }

     void getPost(){
        //Call
        Call<List<Post>> call=postClient.getPost("1");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                tv.setText(response.body().get(1).getId() + "\n" + response.body().get(1).getUserId()+"\n"+response.body().get(1).getTitle()+"\n"+response.body().get(1).getBody());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv.setText(t.getMessage());
            }
    });
}
    void CreatePost(){
        Post post=new Post(23,"MoBadr","sdfaaaaaaaaaaaaa");
        Call <Post> call=postClient.CreatePost(26,"Ahmed Badr","sdfaaaaaaaaaaaaaaa");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                tv.setText( response.body().getId()+"\n"+response.body().getUserId() + "\n" + response.body().getTitle() +"\n"+response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });
    }
    void UpdatePut(){
        Post post=new Post(1,"MoBadr","xzzzzzzzzzzzzzzzzzzzzzzsd");
        Call<Post> call=postClient.UpdateOrCreate(5,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                tv.setText( response.body().getId()+"\n"+response.body().getUserId() + "\n" + response.body().getTitle() +"\n"+response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });
    }
    void UpdatePatch(){
        Post post=new Post(1,"Mo","ksamkkCXKkzKckZc");
        Call<Post> call=postClient.Update(5,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                tv.setText( response.body().getId()+"\n"+response.body().getUserId() + "\n" + response.body().getTitle() +"\n"+response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });

}}
