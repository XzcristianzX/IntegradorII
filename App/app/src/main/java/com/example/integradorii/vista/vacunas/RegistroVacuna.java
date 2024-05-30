package com.example.integradorii.vista.vacunas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Vacuna;
import com.example.integradorii.vista.Home;
import com.example.integradorii.vista.UserProfile;
import com.example.integradorii.vista.mascota.PetProfile;
import com.example.integradorii.vista.usuario.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class RegistroVacuna extends AppCompatActivity {
    private ImageView backArrow, profileUser, profilePet;
    private Button btnSaveCarnet;
    private TextInputEditText namevaccinein, descripcionVaccinein, nextVaccinein;
    private String idPet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vacuna);

        backArrow = findViewById(R.id.back_toolbar);
        profilePet = findViewById(R.id.profile_mascota);
        profileUser = findViewById(R.id.profile_user);

        btnSaveCarnet = findViewById(R.id.btnSaveCarnet);
        namevaccinein = findViewById(R.id.namevaccinein);
        descripcionVaccinein = findViewById(R.id.descripcionVaccinein);
        nextVaccinein = findViewById(R.id.nextVaccinein);


        btnSaveCarnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model model = new Model();
                String getNombre = Objects.requireNonNull(namevaccinein.getText()).toString();
                String getDes = Objects.requireNonNull(descripcionVaccinein.getText()).toString();
                String getNextVaccine = Objects.requireNonNull(nextVaccinein.getText()).toString();
                Bundle bundle = getIntent().getExtras();
                if(bundle != null && bundle.containsKey("idPet")) {
                    idPet = String.valueOf(bundle.getInt("idPet"));
                }
                LocalDate currentDate = LocalDate.now();
                String dateAsString = currentDate.toString();
                Vacuna vacuna = new Vacuna(getNombre, getDes, getNextVaccine, "true", dateAsString, idPet);
                model.registerVaccine(vacuna, new Model.vaccineCallback() {
                    @Override
                    public void onSuccess(Vacuna vacuna) {
                        Toast.makeText(getApplicationContext(), "Vacuna agregada", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(getApplicationContext(), "Error al agregar el registro", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroVacuna.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroVacuna.this, UserProfile.class);
                startActivity(intent);
            }
        });

        profilePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroVacuna.this, PetProfile.class);
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