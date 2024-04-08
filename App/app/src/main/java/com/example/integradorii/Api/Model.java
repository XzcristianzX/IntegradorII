package com.example.integradorii.Api;
import android.util.Log;

import com.example.integradorii.estructura.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Model {
    private static final String BASE_URL = "http://192.168.137.1:3000";

    private ApiService apiService;

    public Model() {
        // Inicializar ApiService
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }



    public void loginUser(String username, String password, final LoginCallback callback) {
        Call<User> call = apiService.loginUser(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Usuario autenticado correctamente
                    callback.onSuccess(response.body());
                } else {
                    // Error de autenticación
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Error en la solicitud
                callback.onFailure();
            }
        });
    }

    public void registerUser(String user_name,String name,String birthdate,String mail,String password,String img,String phone,String gender, final LoginCallback callback) {
        Call<User> call = apiService.register(user_name, name,birthdate,mail,password,img,phone,gender);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Usuario autenticado correctamente
                    callback.onSuccess(response.body());
                } else {
                    // Error de autenticación
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Error en la solicitud
                callback.onFailure();
            }
        });
    }
    public void actulizarUser(String nombre, String password,String img,String phone,String user_name, final LoginCallback callback) {
        Call<User> call = apiService.actulizar(nombre,password,img,phone,user_name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Usuario autenticado correctamente
                    callback.onSuccess(response.body());
                } else {
                    // Error de autenticación
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Error en la solicitud
                callback.onFailure();
            }
        });
    }


    // Interfaz de callback para manejar el resultado de la autenticación del usuario
    public interface LoginCallback {
        void onSuccess(User user);
        void onFailure();
    }
}