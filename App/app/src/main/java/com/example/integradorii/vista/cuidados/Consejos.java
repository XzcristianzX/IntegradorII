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
import java.util.Arrays;
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
            breeds.addAll(Arrays.asList(getResources().getStringArray(R.array.dog_races)));
            } else if (animalType.equals("Gato")) {
            breeds.addAll(Arrays.asList(getResources().getStringArray(R.array.cat_races)));
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
            case "Labrador Retriever":
                return 195;
            case "Pastor Alemán":
                return 196;
            case "Golden Retriever":
                return 197;
            case "Bulldog Francés":
                return 198;
            case "Beagle":
                return 199;
            case "Poodle":
                return 200;
            case "Rottweiler":
                return 201;
            case "Dachshund":
                return 202;
            case "Husky Siberiano":
                return 203;
            case "Boxer":
                return 204;
            case "Chihuahua":
                return 205;
            case "Pug":
                return 206;
            case "Cocker Spaniel":
                return 207;
            case "Schnauzer":
                return 208;
            case "Border Collie":
                return 209;
            case "Criollo (Mestizo)":
                return 210;
            case "Persa":
                return 211;
            case "Siamés":
                return 212;
            case "Maine Coon":
                return 213;
            case "Ragdoll":
                return 214;
            case "Sphynx":
                return 215;
            case "Bengalí":
                return 216;
            case "British Shorthair":
                return 217;
            case "Scottish Fold":
                return 218;
            case "Siberiano":
                return 219;
            case "Birmano":
                return 220;
            case "Abisinio":
                return 221;
            case "Chartreux":
                return 222;
            case "Exotic Shorthair":
                return 223;
            case "Oriental":
                return 224;
            case "American Shorthair":
                return 225;
            case "Holland Lop":
                return 227;
            case "Netherland Dwarf":
                return 228;
            case "Lionhead":
                return 229;
            case "Mini Rex":
                return 230;
            case "Flemish Giant":
                return 231;
            case "English Angora":
                return 232;
            case "American Fuzzy Lop":
                return 233;
            case "Mini Lop":
                return 234;
            case "Harlequin":
                return 235;
            case "Jersey Wooly":
                return 236;
            case "Havana":
                return 237;
            case "Rex":
                return 238;
            case "Polish":
                return 239;
            case "Californian":
                return 240;
            case "Dutch":
                return 241;
            case "Sirio (Dorados)":
                return 243;
            case "Roborovski":
                return 244;
            case "Ruso":
                return 245;
            case "Campbell":
                return 246;
            case "Chino":
                return 247;
            case "Winter White":
                return 248;
            case "Panda":
                return 249;
            case "Albino":
                return 250;
            case "Polaco":
                return 251;
            case "Angora":
                return 252;
            case "Robusto":
                return 253;
            case "Enano":
                return 254;
            case "Satinado":
                return 255;
            case "Teddybear":
                return 256;
            case "Atigrado":
                return 257;
            case "Canario":
                return 259;
            case "Periquito":
                return 260;
            case "Agapornis":
                return 261;
            case "Jilguero":
                return 262;
            case "Diamante Mandarín":
                return 263;
            case "Cacatúa":
                return 264;
            case "Loro Amazónico":
                return 265;
            case "Ninfa (Cockatiel)":
                return 266;
            case "Papagayo":
                return 268;
            case "Guacamayo":
                return 269;
            case "Pinzón":
                return 270;
            case "Cardelina":
                return 271;
            case "Diamante de Gould":
                return 272;
            case "Pardillo":
                return 273;
            case "Carolina":
                return 274;
            case "Goldfish":
                return 275;
            case "Betta":
                return 276;
            case "Guppy":
                return 277;
            case "Tetra Neón":
                return 278;
            case "Cíclido":
                return 279;
            case "Molly":
                return 280;
            case "Platy":
                return 281;
            case "Gourami":
                return 282;
            case "Oscar":
                return 283;
            case "Plecostomus":
                return 284;
            case "Pez Ángel":
                return 285;
            case "Pez Payaso":
                return 286;
            default:return 0;
        }
    }

}