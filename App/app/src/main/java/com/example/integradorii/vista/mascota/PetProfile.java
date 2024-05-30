package com.example.integradorii.vista.mascota;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradorii.Adaptadores.AdaptadorElegirMascota;
import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.Adaptadores.AdaptadorMascota;
import com.example.integradorii.estructura.Animal;
import com.example.integradorii.vista.UserProfile;
import com.example.integradorii.vista.usuario.Usuario;
import com.example.integradorii.vista.vacunas.RegistroVacuna;
import com.example.integradorii.vista.vacunas.SelectAnimalForVaccine;
import com.example.integradorii.vista.vacunas.Vacunas;

import java.util.ArrayList;
import java.util.List;

public class PetProfile extends AppCompatActivity {
    private List<Animal> myAnimalsList;
    private AdaptadorElegirMascota adaptadorElegirMascota;
    private RecyclerView recyclerView;
    private ImageButton profile_user_own, back_toolbar_own, addPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.reciclerPets);
        profile_user_own = findViewById(R.id.profile_user_own);
        back_toolbar_own = findViewById(R.id.back_toolbar_own);
        addPet = findViewById(R.id.addPet);
        Model model = new Model();

        int iduser = Integer.parseInt(Model.getShared(getApplicationContext(), "userId"));
        model.getPetsToChoose(iduser, new Model.getMyAnimals() {
            @Override
            public void onSuccess(List<Animal> myAnimals) {

                myAnimalsList = new ArrayList<>();
                myAnimalsList.addAll(myAnimals);
                adaptadorElegirMascota = new AdaptadorElegirMascota(myAnimalsList, position -> {
//                Intent intent = new Intent(SelectAnimalForVaccine.this, Vacunas.class);
//                intent.putExtra("idPet", position);
//                startActivity(intent);
                });
                recyclerView.setAdapter(adaptadorElegirMascota);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(), "Error de servidor", Toast.LENGTH_SHORT).show();
            }
        });

        back_toolbar_own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profile_user_own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
            }
        });

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterPet.class));
            }
        });
    }
}

