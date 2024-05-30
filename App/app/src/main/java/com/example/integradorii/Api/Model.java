package com.example.integradorii.Api;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.integradorii.estructura.Animal;
import com.example.integradorii.estructura.Careful;
import com.example.integradorii.estructura.Post;
import com.example.integradorii.estructura.User;
import com.example.integradorii.estructura.Vacuna;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {
    private static final String BASE_URL = "http://192.168.51.50:3001";

    private ApiService apiService;

    public Model() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }



    public void loginUser(Context context, String email, String password, final UserCallback callback) {
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
                        String username = user.getUser_name();
                        String email = user.getMail();
                    }
                    Log.e("ola", getShared(context, "idUser"));
                    callback.onSuccess(user);
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
    public void verificarUser(String mail,String code, final UserCallback callback){
        User Usernew = new User(mail,code,"");

        Call<User> call= apiService.verificar(Usernew);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public void getMyUser(Context context, final UserCallback callback) {
        String iduser = Model.getShared(context, "userId");
        Call<User> call= apiService.getUserById(iduser);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public void registerUser(String username, String name, String birthdate, String email, String password, String phone, String gender, final UserCallback callback) {
        boolean active= true;
        User Usernew = new User(username,  name,  birthdate,  email,  password,  phone,  gender, true);
        Call<User> call = apiService.register(Usernew);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public void actulizarUser(int id,String nombre, String password,String img,String phone,String user_name, final UserCallback callback) {
        User Usernew = new User(user_name,  nombre,"  birthdate",  "email",password,  phone,  "gender",true);
        Call<User> call = apiService.updateUser(id,Usernew);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

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
    public void getAnimalsByUserId(int userId, final AnimalsCallback callback) {
        Call<List<Animal>> call = apiService.getAnimals(userId);
        call.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                callback.onFailure();
                Log.e("ola", t.toString());
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

    public void getCuidados(int idRace, final CarefulCallback callback) {
        Call<List<Careful>> call = apiService.getCuidados(idRace);
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
    public void getPosts(final PostsCallback callback) {
        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public void registerVaccine(Vacuna vacuna, final vaccineCallback vaccineCallback) {
        Call<Vacuna> call = apiService.registrarVacuna(vacuna);
        call.enqueue(new Callback<Vacuna>() {
            @Override
            public void onResponse(Call<Vacuna> call, Response<Vacuna> response) {
                if (response.isSuccessful()) {
                    vaccineCallback.onSuccess(response.body());
                } else {
                    vaccineCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Vacuna> call, Throwable t) {
                vaccineCallback.onFailure();
                Log.e("olaa", t.toString());
            }
        });
    }

    public void getPetsToChoose(int id_user, final getMyAnimals getMyAnimalsCallback) {
        Call<List<Animal>> call = apiService.getMyAnimals(id_user);
        call.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if (response.isSuccessful()) {
                    getMyAnimalsCallback.onSuccess(response.body());
                } else {
                    getMyAnimalsCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                getMyAnimalsCallback.onFailure();
                Log.e("ola", t.toString());
            }
        });
    }

    public void getvacunasByPet(int id_pet, final vaccinegetCallback vaccinegetCallback) {
        Call<List<Vacuna>> call = apiService.getMyvacunas(id_pet);
        call.enqueue(new Callback<List<Vacuna>>() {
            @Override
            public void onResponse(Call<List<Vacuna>> call, Response<List<Vacuna>> response) {
                if (response.isSuccessful()) {
                    vaccinegetCallback.onSuccess(response.body());
                } else {
                    vaccinegetCallback.onFailure();
                    Log.e("ola88", "paso por aca ww");
                }
            }

            @Override
            public void onFailure(Call<List<Vacuna>> call, Throwable t) {
                vaccinegetCallback.onFailure();
                Log.e("ola88", "paso por aca" + t.toString());
            }
        });
    }

    public interface PostsCallback {
        void onSuccess(List<Post> posts);
        void onFailure();
    }




    public interface UserCallback {
        void onSuccess(User user);
        void onFailure();
    }
    public interface AnimalsCallback {
        void onSuccess(List<Animal> animals);
        void onFailure();
    }
    public interface AnimalCallback {
        void onSuccess(Animal animal);
        void onFailure();
    }

    public interface PostCallback {
        void onSuccess(Post post);
        void onFailure();
    }

    public interface CarefulCallback {
        void onSuccess(List<Careful> carefulList);
        void onFailure();
    }

    public interface vaccineCallback {
        void onSuccess(Vacuna vacuna);
        void onFailure();
    }

    public interface vaccinegetCallback {
        void onSuccess(List<Vacuna> vacunasList);
        void onFailure();
    }

    public interface getMyAnimals {
        void onSuccess( List<Animal> myAnimals);
        void onFailure();
    }

    public static String getShared(Context activity, String key) {
        SharedPreferences preferences = activity.getSharedPreferences("animal", MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static void saveShared(Context activity, String key, String value) {
        SharedPreferences preferences = activity.getSharedPreferences("animal", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

}