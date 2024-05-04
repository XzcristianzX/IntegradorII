package com.example.integradorii.Api;

import com.example.integradorii.estructura.Animal;
import com.example.integradorii.estructura.Careful;
import com.example.integradorii.estructura.Post;
import com.example.integradorii.estructura.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/user")
    Call<List<User>> getUsers();

    //<--login-->
    @POST("/login")
    Call<User> loginUser(@Body User user);

    //<--register-->
    @POST("/user")
    Call<User> register(@Body User user);

    //<--verificar-->
    @POST("verify")
    Call<User> verificar(@Body User user);

    //<--Actualizar-->
    @PUT("/user")
    Call<User> actualizar(@Body User user);

    @PUT("/users/{id}")
    Call<User> updateUser(@Path("id") int userId, @Body User user);

    @GET("/animal")
    Call<List<Animal>> getAnimals();

    @POST("/animal")
    Call<Animal> registerAnimal(@Body Animal animal);

    @PUT("/animal/{id}")
    Call<Animal> updateAnimal(@Path("id") int animalId, @Body Animal animal);

    @GET("/post")
    Call<List<Post>> getPosts();

    @POST("/post")
    Call<Post> createPost(@Body Post post);

    @PUT("/post/{id}")
    Call<Post> updatePost(@Path("id") int postId, @Body Post post);

    @GET("/cuidados")
    Call<List<Careful>> getCuidados();
}

