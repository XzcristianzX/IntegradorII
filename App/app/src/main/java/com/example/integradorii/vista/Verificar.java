package com.example.integradorii.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.User;

public class Verificar extends AppCompatActivity {
    Button btaceptar,btcancelar;

    EditText editCode;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verificar);
        btaceptar = findViewById(R.id.btaceptar);
        btcancelar = findViewById(R.id.btcancelar);
        editCode = findViewById(R.id.code);
        final Model model = new Model();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("mail")) {
                email = extras.getString("mail");
            }
        }

        btaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editCode.getText().toString().trim();
                model.verificarUser(email,code, new Model.UserCallback() {
                    @Override
                    public void onSuccess(User user) {
                        // Usuario autenticado correctamente
                        Toast.makeText(Verificar.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Verificar.this, Home.class);
                        intent.putExtra("name",user.getName());
                        intent.putExtra("phone",user.getPhone());
                        intent.putExtra("user_name",user.getUsername());
                        intent.putExtra("mail",user.getEmail());

                        //aqui pasar los demas datos
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure() {
                        // Error de autenticación
                        Toast.makeText(Verificar.this, "Error: Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Verificar.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
