package com.example.integradorii.vista.cuidados;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Careful;
import com.example.integradorii.estructura.User;
import com.example.integradorii.vista.Login;
import com.example.integradorii.vista.UserProfile;
import com.example.integradorii.vista.Verificar;
import com.example.integradorii.vista.mascota.PetProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Consejos extends AppCompatActivity {
    private ImageView backArrow, profileUser, profilePet;
    private Spinner spinnerAnimalType, spinnerAnimalBreed;
    private HashMap<String, ArrayList<String>> animalBreedMap = new HashMap<>();

    private Button btconsulta;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejos);
        backArrow = findViewById(R.id.back_toolbar);
        profilePet = findViewById(R.id.profile_mascota);
        profileUser = findViewById(R.id.profile_user);
        btconsulta = findViewById(R.id.btn_consulta);
        spinnerAnimalType = findViewById(R.id.spinner_animal_type);
        spinnerAnimalBreed = findViewById(R.id.spinner_animal_breed);
        backArrow.setOnClickListener(v -> finish());
        profileUser.setOnClickListener(view -> {
            Intent intent = new Intent(Consejos.this, UserProfile.class);
            startActivity(intent);
        });
        profilePet.setOnClickListener(v -> {
            Intent intent = new Intent(Consejos.this, PetProfile.class);
            startActivity(intent);
        });
        setupAnimalTypeSpinner();
        btconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected animal type and breed
                String selectedAnimalType = spinnerAnimalType.getSelectedItem().toString();
                String selectedBreed = spinnerAnimalBreed.getSelectedItem().toString();

                // Get the ID of the selected breed
                int breedId = getIdRaceByName(selectedBreed);

                // Create an Intent to start the MostrarConsejos activity
                Intent intent = new Intent(Consejos.this, MostrarConsejos.class);

                // Put the selected animal type, breed, and breed ID as extras in the Intent
                intent.putExtra("animalType", selectedAnimalType);
                intent.putExtra("breed", selectedBreed);
                intent.putExtra("breedId", breedId);

                // Start the MostrarConsejos activity
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void setupAnimalTypeSpinner() {
        ArrayList<String> animalTypes = new ArrayList<>();
        animalTypes.add("Perro");
        animalTypes.add("Gato");



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, animalTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnimalType.setAdapter(adapter);
        spinnerAnimalType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedAnimal = parentView.getItemAtPosition(position).toString();
                loadBreedsForAnimal(selectedAnimal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    private void loadBreedsForAnimal(String animalType) {
        ArrayList<String> breeds = new ArrayList<>();
        if (animalType.equals("Perro")) {
            breeds.add("Labrador");
        } else if (animalType.equals("Gato")) {
            breeds.add("persa");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, breeds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnimalBreed.setAdapter(adapter);

        spinnerAnimalBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedBreed = parentView.getItemAtPosition(position).toString();
                int idRace = getIdRaceByName(selectedBreed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    private int getIdRaceByName(String breedName) {
        switch (breedName) {
            case "Labrador": return 1;
            case "Bulldog": return 2;
            case "Siames": return 3;
            case "Persa": return 4;
            case "Holandés": return 5;
            case "Cabeza de León": return 6;
            default: return 0;
        }
    }

}