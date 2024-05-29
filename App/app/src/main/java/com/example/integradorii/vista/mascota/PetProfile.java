package com.example.integradorii.vista.mascota;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    private void obtenerMascotasDelUsuario() {
        int userId = obtenerIdUsuario(); // Método para obtener el ID del usuario actual
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

    private int obtenerIdUsuario() {
        // Aquí debes implementar cómo obtener el ID del usuario actual desde SharedPreferences
        String userIdStr = Model.getShared(this, "userId");
        return Integer.parseInt(userIdStr); // Ejemplo, asegúrate de manejar posibles errores
    }
}
