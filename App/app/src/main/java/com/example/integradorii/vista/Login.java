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

import io.socket.client.Socket;

public class Login extends AppCompatActivity {
    Button btRegistrarce,btLogin;
    EditText editemial,editpassword;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        btRegistrarce = findViewById(R.id.btRegister);
        btLogin = findViewById(R.id.btLogin);
        editemial = findViewById(R.id.userLogin);
        editpassword = findViewById(R.id.paswordLogin);

        final Model model = new Model();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editemial.getText().toString().trim();
                String password = editpassword.getText().toString().trim();

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

        btRegistrarce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}