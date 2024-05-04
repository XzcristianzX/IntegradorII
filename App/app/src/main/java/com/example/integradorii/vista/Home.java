package com.example.integradorii.vista;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.User;

public class Home  extends AppCompatActivity {

    private Button btactulizar;
    String userName,id,name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        btactulizar= findViewById(R.id.btActulizar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Verificar si el extra "user_name" está presente
            if (extras.containsKey("user_name")) {
                // Obtener el valor de "user_name"
                 userName = extras.getString("user_name");
                 id = extras.getString("phone");
                 name = extras.getString("name");
                 String mail = extras.getString("mail");

                Toast.makeText(this, "hola: "+userName+" id: "+ id+" name: "+name+mail, Toast.LENGTH_SHORT).show();
            }
        }

        btactulizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Toast.makeText(Home.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Home.this, Actulizar.class);
                        intent.putExtra("user_name",userName);
                        startActivity(intent);
                        finish();
                    }
        });
    }
}