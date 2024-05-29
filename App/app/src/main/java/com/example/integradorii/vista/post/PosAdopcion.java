package com.example.integradorii.vista.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradorii.Adaptadores.AdaptadorPost;
import com.example.integradorii.Api.ApiService;
import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Post;
import com.example.integradorii.vista.Home;
import com.example.integradorii.vista.UserProfile;
import com.example.integradorii.vista.mascota.PetProfile;
import com.example.integradorii.vista.usuario.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PosAdopcion extends AppCompatActivity {
    private ImageView backArrow;
    private Button crearPostButton;
    private List<Post> posts;
    AdaptadorPost adaptadorPost;
    RecyclerView recyclerView;
    ImageButton profileUser, profilePet;

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postadopcion);

        backArrow = findViewById(R.id.back_toolbar);
        crearPostButton = findViewById(R.id.btnCrearPublicacion);
        recyclerView = findViewById(R.id.recycler_view_post);
        profileUser = findViewById(R.id.profile_user);
        profilePet = findViewById(R.id.profile_mascota);

        model = new Model();
        fetchPosts();

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PosAdopcion.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        crearPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PosAdopcion.this, RegistrarPost.class);
                startActivity(intent);
                finish();
            }
        });

        profilePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PosAdopcion.this, PetProfile.class);
                startActivity(intent);
            }
        });

        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PosAdopcion.this, UserProfile.class);
                startActivity(intent);
            }
        });
    }

    private void fetchPosts() {
        model.getPosts(new Model.PostsCallback() {
            @Override
            public void onSuccess(List<Post> fetchedPosts) {
                posts = new ArrayList<>();
                posts.addAll(fetchedPosts);
                adaptadorPost = new AdaptadorPost(posts, position -> {
                    Post post = posts.get(position);
                    posts.remove(position);
                    posts.add(post);
                    adaptadorPost.notifyDataSetChanged();
                });
                recyclerView.setAdapter(adaptadorPost);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(), "Algo salió mal!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}