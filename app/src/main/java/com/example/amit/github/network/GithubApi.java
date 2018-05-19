package com.example.amit.github.network;


import com.example.amit.github.model.GithubResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by amit on 5/19/2018.
 */

public interface GithubApi {

//     users/nitinnatural/repos

    @GET("users/{username}/repos")
    Call<List<GithubResponse>> fetchUser(@Path("username") String username);

}
