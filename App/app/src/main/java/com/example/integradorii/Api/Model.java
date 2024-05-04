package com.example.integradorii.Api;

import com.example.integradorii.estructura.Animal;
import com.example.integradorii.estructura.Careful;
import com.example.integradorii.estructura.Post;
import com.example.integradorii.estructura.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {
    private static final String BASE_URL = "http://192.168.1.11:3000";

    private ApiService apiService;

    public Model() {
        // Inicializar ApiService
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }



    public void loginUser(String email, String password, final UserCallback callback) {
        User Usernew = new User(email,"",password);

        Call<User> call = apiService.loginUser(Usernew);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user != null) {
                        String name = user.getName();
                        String phone = user.getPhone();
                        String username = user.getUsername();
                        String email = user.getEmail();
                    }
                    callback.onSuccess(user);
                } else {
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
    public void verificarUser(String email,String code, final UserCallback callback){
        User Usernew = new User(email,code,"");
        Call<User> call= apiService.verificar(Usernew);

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
                callback.onFailure();
            }
        });
    }
    public void registerUser(String username, String name, String birthdate, String email, String password, String img, String phone, String gender, final UserCallback callback) {
        // Crear un objeto de tipo User con los datos proporcionados
        boolean active= true;
        User Usernew = new User(1,username,  name,  birthdate,  email,  password,  img,  phone,  gender,active,"");
        // Realizar la llamada a la API para registrar el usuario
        Call<User> call = apiService.register(Usernew);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Usuario registrado correctamente
                    callback.onSuccess(response.body());
                } else {
                    // Error al registrar usuario
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

    public void actulizarUser(int id,String nombre, String password,String img,String phone,String user_name, final UserCallback callback) {
        User Usernew = new User(0,user_name,  nombre,"  birthdate",  "email",password,  img,  phone,  "gender",true,"");

        Call<User> call = apiService.updateUser(id,Usernew);
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

    // Métodos para operaciones relacionadas con User (login, register, update) aquí...
    public void registerAnimal(Animal animal, final AnimalCallback callback) {
        Call<Animal> call = apiService.registerAnimal(animal);
        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
    public void updateAnimal(int animalId, Animal animal, final AnimalCallback callback) {
        Call<Animal> call = apiService.updateAnimal(animalId, animal);
        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
    public void createPost(Post post, final PostCallback callback) {
        Call<Post> call = apiService.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
    public void updatePost(int postId, Post post, final PostCallback callback) {
        Call<Post> call = apiService.updatePost(postId, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
    public void getCuidados(final CarefulCallback callback) {
        Call<List<Careful>> call = apiService.getCuidados();
        call.enqueue(new Callback<List<Careful>>() {
            @Override
            public void onResponse(Call<List<Careful>> call, Response<List<Careful>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Careful>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }




    // Interfaz de callback para manejar el resultado de la autenticación del usuario
    public interface UserCallback {
        void onSuccess(User user);
        void onFailure();
    }

    // Interfaz de callback para manejar el resultado de las operaciones con Animal
    public interface AnimalCallback {
        void onSuccess(Animal animal);
        void onFailure();
    }

    // Interfaz de callback para manejar el resultado de las operaciones con Post
    public interface PostCallback {
        void onSuccess(Post post);
        void onFailure();
    }

    // Interfaz de callback para manejar el resultado de las operaciones con Careful
    public interface CarefulCallback {
        void onSuccess(List<Careful> carefulList);
        void onFailure();
    }

}