package com.example.integradorii.vista;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

import io.socket.client.Socket;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    TextView btRegister;
    Button btLogin;
    EditText etEmail, etpass;
    private Socket socket;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btRegister = findViewById(R.id.go_to_register);
        btLogin = findViewById(R.id.btn_login);
        etEmail = findViewById(R.id.email);
        etpass = findViewById(R.id.pass);

        final Model model = new Model();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etpass.getText().toString().trim();

                model.loginUser(email, password, new Model.UserCallback() {
                    @Override
                    public void onSuccess(User user) {

                        Toast.makeText(Login.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Verificar.class);
                        intent.putExtra("name",user.getName());
                        intent.putExtra("phone",user.getPhone());
                        intent.putExtra("user_name",user.getUsername());
                        intent.putExtra("mail",email);
                        //aqui pasar los demas datos
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure() {
                        // Error de autenticación
                        Toast.makeText(Login.this, "Error: Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}

