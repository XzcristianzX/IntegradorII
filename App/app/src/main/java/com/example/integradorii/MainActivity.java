package com.example.integradorii;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.vista.Login;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000; // Tiempo en milisegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Crear un Intent para iniciar la actividad HomeActivity
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                // Finaliza MainActivity para que el usuario no pueda regresar aquí presionando el botón de retroceso
                finish();
            }
        }, 3000);
    }
}