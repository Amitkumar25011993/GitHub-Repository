package com.example.amit.github.network;

import com.example.amit.github.model.GithubResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amit on 5/19/2018.
 */

public class RetroFitClient {


    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();




    // Trailing slash is needed
    public static final String BASE_URL = "https://api.github.com/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();



    GithubApi apiService =
            retrofit.create(GithubApi.class);


    public void getUser(String githubUsername, Callback callback ){

        Call<List<GithubResponse>> call = apiService.fetchUser(githubUsername);
        call.enqueue(callback);
    }




}
