package com.example.integradorii.vista.vacunas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradorii.Adaptadores.AdaptadorElegirMascota;
import com.example.integradorii.Adaptadores.AdaptadorVacuna;
import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Animal;
import com.example.integradorii.estructura.Vacuna;
import com.example.integradorii.vista.Home;
import com.example.integradorii.vista.UserProfile;
import com.example.integradorii.vista.mascota.PetProfile;
import com.example.integradorii.vista.mascota.RegisterPet;

import java.util.ArrayList;
import java.util.List;

public class Vacunas extends AppCompatActivity {

    private ImageView backArrow, goToVaccine, profilePet, profileUser, addVaccine;
    int idPet;
    private List<Vacuna> vacunaList = new ArrayList<>();
    private AdaptadorVacuna adaptadorVacuna;
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacunas);

        backArrow = findViewById(R.id.back_toolbar);
        profilePet = findViewById(R.id.profile_mascota);
        profileUser = findViewById(R.id.profile_user);
        goToVaccine = findViewById(R.id.addVaccine);
        recyclerView = findViewById(R.id.recycler_view);
        addVaccine = findViewById(R.id.addVaccine);


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vacunas.this, UserProfile.class);
                startActivity(intent);
            }
        });

        profilePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vacunas.this, PetProfile.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("idPet")) {
            idPet = bundle.getInt("idPet");
        }
        adaptadorVacuna = new AdaptadorVacuna(vacunaList, new AdaptadorVacuna.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        Model model = new Model();
        model.getvacunasByPet(idPet, new Model.vaccinegetCallback() {
            @Override
            public void onSuccess(List<Vacuna> vacunasList) {
                vacunaList = new ArrayList<>();
                vacunaList.addAll(vacunasList);
                recyclerView.setAdapter(adaptadorVacuna);
                adaptadorVacuna.updateData(vacunasList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            }
            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(), "Error de servidor", Toast.LENGTH_SHORT).show();
            }
        });
        goToVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistroVacuna.class));
            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                if(bundle != null && bundle.containsKey("idPet")) {
                    idPet = bundle.getInt("idPet");
                }
                Intent intent = new Intent(Vacunas.this, RegistroVacuna.class);
                intent.putExtra("idPet", idPet);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
