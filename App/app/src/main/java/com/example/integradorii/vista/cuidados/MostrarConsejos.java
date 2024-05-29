package com.example.integradorii.vista.cuidados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.Adaptadores.ConsejoAdapter;
import com.example.integradorii.estructura.Careful;
import com.example.integradorii.vista.Home;
import com.example.integradorii.vista.UserProfile;
import com.example.integradorii.vista.mascota.PetProfile;

import java.util.ArrayList;
import java.util.List;

public class MostrarConsejos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ConsejoAdapter adapter;
    private List<Careful> consejos;
    private int breedId;
    private Model model;
    private ImageView backArrow, profileUser, profilePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_consejos);

        backArrow = findViewById(R.id.back_toolbar);
        profilePet = findViewById(R.id.profile_mascota);
        profileUser = findViewById(R.id.profile_user);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list and adapter
        consejos = new ArrayList<>();
        adapter = new ConsejoAdapter(consejos);
        recyclerView.setAdapter(adapter);

        // Initialize ApiService
        model = new Model();

        // Get the breedId from the Intent extras
        Intent intent = getIntent();
        breedId = intent.getIntExtra("breedId", -1);

        if (breedId == -1) {
            Toast.makeText(this, "Invalid breed ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Fetch the data
        fetchConsejos(breedId);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profilePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarConsejos.this, PetProfile.class);
                startActivity(intent);
            }
        });

        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarConsejos.this, UserProfile.class);
                startActivity(intent);
            }
        });
    }

    private void fetchConsejos(int breedId) {
        model.getCuidados(breedId, new Model.CarefulCallback() {
            @Override
            public void onSuccess(List<Careful> carefulList) {
                consejos.addAll(carefulList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MostrarConsejos.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
