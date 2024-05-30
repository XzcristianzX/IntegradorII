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

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.Adaptadores.AdaptadorMascota;
import com.example.integradorii.estructura.Animal;
import com.example.integradorii.vista.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class PetProfile extends AppCompatActivity {

    private ImageView backArrow, profileUser;
    private ImageButton btnCrearMascotas;
    private RecyclerView recyclerMascota;
    private AdaptadorMascota adaptadorMascota;
    private List<Animal> listaMascotas;
    private Model model;

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

        model = new Model();

        profileUser = findViewById(R.id.profile_user_own);
        backArrow = findViewById(R.id.back_toolbar_own);
        btnCrearMascotas = findViewById(R.id.addPet);
        recyclerMascota = findViewById(R.id.reciclerPets);

        listaMascotas = new ArrayList<>();
        adaptadorMascota = new AdaptadorMascota(listaMascotas, position -> {
            // Manejar clics en los ítems si es necesario
        });

        recyclerMascota.setLayoutManager(new LinearLayoutManager(this));
        recyclerMascota.setAdapter(adaptadorMascota);

        btnCrearMascotas.setOnClickListener(v -> {
            Intent intent = new Intent(PetProfile.this, RegisterPet.class);
            finish();
            startActivity(intent);
        });

        profileUser.setOnClickListener(view -> {
            Intent intent = new Intent(PetProfile.this, Usuario.class);
            finish();
            startActivity(intent);
        });

        backArrow.setOnClickListener(view -> finish());

        // Obtener mascotas del usuario
        obtenerMascotasDelUsuario();
    }


    private int obtenerIdUsuario() {
        String userIdStr = Model.getShared(this, "userId");
        if (userIdStr == null || userIdStr.isEmpty()) {
            Log.e("PetProfile", "ID de usuario inválido");
            return -1; // Valor por defecto
        }
        try {
            return Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            Log.e("PetProfile", "Error al convertir el ID de usuario a número", e);
            return -1;
        }
    }
    private void obtenerMascotasDelUsuario() {
        int userId = obtenerIdUsuario();
        if (userId == -1) {
            Toast.makeText(this, "ID de usuario inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        model.getAnimalsByUserId(userId, new Model.AnimalsCallback() {
            @Override
            public void onSuccess(List<Animal> animals) {
                listaMascotas.clear();
                listaMascotas.addAll(animals);
                adaptadorMascota.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {
                Toast.makeText(PetProfile.this, "Error al cargar los animales", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
