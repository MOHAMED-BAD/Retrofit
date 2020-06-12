package com.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static String base_url = "https://jsonplaceholder.typicode.com/";
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    private static Gson gson = new GsonBuilder().serializeNulls().create();
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create(gson));
    private static Retrofit retrofit = builder.build();

    public static <s> s createService(Class<s> serviceClass) {
        if (!okHttpClient.interceptors().contains(loggingInterceptor)) {
            okHttpClient.addInterceptor(loggingInterceptor);
            retrofit = builder.client(okHttpClient.build()).build();
        }
        return retrofit.create(serviceClass);
    }
}