package com.example.integradorii.vista;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.R;

public class Home extends AppCompatActivity {

    private LinearLayout publicacionesAdopcion;
    private LinearLayout consejosCuidados;
    private LinearLayout carnetVacunas;
    private LinearLayout ubicaMascota;
    private ImageView backArrow,mascota,profile;
    String userName, id, name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        publicacionesAdopcion = findViewById(R.id.publicaciones_adopcion);
        consejosCuidados = findViewById(R.id.consejos_cuidados);
        carnetVacunas = findViewById(R.id.carnet_vacunas);
        ubicaMascota = findViewById(R.id.ubica_mascota);
        backArrow = findViewById(R.id.back_arrow);
        mascota = findViewById(R.id.mascota);
        profile = findViewById(R.id.profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("user_name")) {
                userName = extras.getString("user_name");
                id = extras.getString("phone");
                name = extras.getString("name");
                String mail = extras.getString("mail");

                Toast.makeText(this, "Hola: " + userName + " id: " + id + " name: " + name + " " + mail, Toast.LENGTH_SHORT).show();
            }
        }
        mascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Mascota.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Usuario.class);
                startActivity(intent);
            }
        });

        publicacionesAdopcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PosAdopcion.class);
                startActivity(intent);
            }
        });

        consejosCuidados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Consejos.class);
                startActivity(intent);
            }
        });

        carnetVacunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Vacunas.class);
                startActivity(intent);
            }
        });

        ubicaMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Ubicacion.class);
                startActivity(intent);
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutConfirmationDialog();
            }
        });
    }

    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Seguro quieres cerrar sesión?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Cerrar sesión y redirigir a la pantalla de inicio de sesión
                        Intent intent = new Intent(Home.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}