package com.example.integradorii.vista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.MainActivity;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Careful;
import com.example.integradorii.vista.mascota.PetProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Consejos extends AppCompatActivity {
    private ImageView backArrow, profileUser, profilePet;
    private Spinner spinnerAnimalType, spinnerAnimalBreed;
    private HashMap<String, ArrayList<String>> animalBreedMap = new HashMap<>();

    private TextView carefulDetails;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejos);
        backArrow = findViewById(R.id.back_arrow_consejos);
        profileUser = findViewById(R.id.user_profile_consejos);
        profilePet = findViewById(R.id.pet_profile_consejos);
        carefulDetails = findViewById(R.id.careful_details);
        spinnerAnimalType = findViewById(R.id.spinner_animal_type);
        spinnerAnimalBreed = findViewById(R.id.spinner_animal_breed);
        final Model model = new Model();

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
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Consejos.this, UserProfile.class);
                startActivity(intent);
            }
        });
        profilePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consejos.this, PetProfile.class);
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
        animalTypes.add("Conejo");

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
            breeds.add("Bulldog");
        } else if (animalType.equals("Gato")) {
            breeds.add("Siames");
            breeds.add("Persa");
        } else if (animalType.equals("Conejo")) {
            breeds.add("Holandés");
            breeds.add("Cabeza de León");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, breeds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnimalBreed.setAdapter(adapter);

        spinnerAnimalBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedBreed = parentView.getItemAtPosition(position).toString();
                int idRace = getIdRaceByName(selectedBreed);
                fetchCareDetailsForBreed(idRace);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    private int getIdRaceByName(String breedName) {
        // Suponiendo que tienes una manera de mapear los nombres de las razas a sus IDs
        // Esto es solo un ejemplo, puedes adaptar esto según tu lógica
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

    private void fetchCareDetailsForBreed(int idRace) {
        Model model = new Model();

        model.getCuidados(idRace, new Model.CarefulCallback() {
            @Override
            public void onSuccess(List<Careful> carefulList) {
                if (!carefulList.isEmpty()) {
                    Careful careful = carefulList.get(0); // Suponiendo que obtienes uno por raza
                    carefulDetails.setText("Alimentación: " + careful.getFeeding() +
                            "\nBaño: " + careful.getBathroom() +
                            "\nRaza: " + careful.getRace().getName());
                } else {
                    carefulDetails.setText("No se encontraron detalles de cuidados para esta raza.");
                }
            }

            @Override
            public void onFailure() {
                //Toast.makeText(Consejos.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

}