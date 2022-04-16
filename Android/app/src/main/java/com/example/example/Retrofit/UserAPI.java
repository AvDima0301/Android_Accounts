package com.example.example.Retrofit;

import com.example.example.users.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI {
    @GET("/api/Account/users")
    public Call<List<UserDTO>> getUsers();

    @POST("/api/Account/register")
    public Call<UserRes> addUser(@Body User user);
}
