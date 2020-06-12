package com.example.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.Post;
import com.example.retrofit.PostClient;
import com.example.retrofit.R;
import com.example.service.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    AdapterPost adapterPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.body);
        adapterPost = new AdapterPost();
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv.setAdapter(adapterPost);
        getPost();
    }

    void getPost() {
        PostClient postClient = ServiceGenerator.createService(PostClient.class);
        Call<List<Post>> call = postClient.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    adapterPost.submitList(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
    void rcycle(){


    }
}
