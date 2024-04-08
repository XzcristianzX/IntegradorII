package com.example.integradorii.Api;

import com.example.integradorii.estructura.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/user")
    Call<List<User>> getUsers();

    //<--login-->
    @PUT("/user")
    Call<User> loginUser(@Query("username") String username, @Query("password") String password);

    //<--register-->
    @POST("/user")
    Call<User> register(@Query("username") String username, @Query("name") String name,
                         @Query("birthdate") String birthdate, @Query("mail") String mail,
                         @Query("password") String password, @Query("img_profile") String img_profile,
                         @Query("phone") String phone, @Query("gender") String gender);
    @PUT("/user")
    Call<User> actulizar(@Query("name") String name, @Query("password") String password,
                         @Query("img_profile") String img_profile, @Query("phone") String phone,@Query("username") String username);

    @PUT("/users/{id}")
    Call<User> updateUser(@Path("id") int userId, @Body User user);
}
