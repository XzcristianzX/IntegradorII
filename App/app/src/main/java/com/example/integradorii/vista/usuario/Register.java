package com.example.integradorii.vista.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.User;
import com.example.integradorii.vista.Home;
import com.example.integradorii.vista.Login;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {

    ImageButton back;
    Button btnRegister;
    TextView backLogin;
    TextInputEditText namein, userin, emailin, passin, phonein, datein;
    Spinner spinnerGender;
    Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        back = findViewById(R.id.btn_back);
        backLogin = findViewById(R.id.go_to_login);
        namein = findViewById(R.id.namein);
        userin = findViewById(R.id.userin);
        emailin = findViewById(R.id.emailin);
        passin = findViewById(R.id.passin);
        phonein = findViewById(R.id.phonein);
        datein = findViewById(R.id.datein);
        btnRegister = findViewById(R.id.btn_register);
        spinnerGender = findViewById(R.id.genero);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentLogin();
            }
        });
    }

    public void register() {
        try {
            String userRegister = userin.getText().toString();
            String nombre = namein.getText().toString();
            String fecha = datein.getText().toString();
            String correo = emailin.getText().toString();
            String pin = passin.getText().toString();
            String pin2 = passin.getText().toString();
            String tel = phonein.getText().toString();
            String genero = validarGenero();

            if (genero.isEmpty() && tel.isEmpty() && correo.isEmpty() && nombre.isEmpty() && pin.isEmpty() && pin2.isEmpty() && pin.equals(pin2)) {
                Toast.makeText(this, "ingrese todos los datos", Toast.LENGTH_SHORT).show();

            } else if (tel.length() != 10 || tel.matches("[a-zA-Z\\s]+")) {
                Toast.makeText(this, "debes ingresar un telefono valido", Toast.LENGTH_SHORT).show();

            } else if (!correo.contains("@") || !correo.contains(".") || correo.indexOf("@") == 0 || correo.lastIndexOf(".") == correo.length() - 1 || correo.lastIndexOf(".") - correo.indexOf("@") <= 1) {
                Toast.makeText(this, "debes ingresar el correo valido", Toast.LENGTH_SHORT).show();

            } else if (!nombre.matches(".*[a-zA-Z].*")) {
                Toast.makeText(this, "debes ingresar el nombre valido", Toast.LENGTH_SHORT).show();

            } else if (pin.length() != 4 || pin.matches("[a-zA-Z\\s]+")) {
                Toast.makeText(this, "debes ingresar los 4 digitos del pin", Toast.LENGTH_SHORT).show();

            } else if (!pin.equals(pin2)) {
                Toast.makeText(this, "no coinciden los 2 pines", Toast.LENGTH_SHORT).show();

            } else if (pin.matches("(\\d)\\1{3}")) {
                Toast.makeText(this, "El PIN no puede tener 4 números iguales", Toast.LENGTH_SHORT).show();

            } else if (hasConsecutiveNumbers(pin)) {
                Toast.makeText(this, "El PIN no puede contener ser consecutivos", Toast.LENGTH_SHORT).show();
            } else {
                model.registerUser(userRegister, nombre, fecha, correo, pin, tel, genero, new Model.UserCallback() {
                    @Override
                    public void onSuccess(User user) {
                        Toast.makeText(Register.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, Home.class);
                        intent.putExtra("correo", correo);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(Register.this, "Error: Algo ha salido mal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar los datos del usuario",
                    Toast.LENGTH_SHORT).show();
            Log.e("Error", e.getMessage());
        }
    }
    private boolean hasConsecutiveNumbers(String text) {
        char prevChar = '\0';
        int consecutiveCount = 1;
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isDigit(currentChar)) {
                if (prevChar != '\0' && prevChar + 1 == currentChar) {
                    consecutiveCount++;
                    if (consecutiveCount == 4) {
                        return true;
                    }
                } else {
                    consecutiveCount = 1;
                }
                prevChar = currentChar;
            }
        }
        return false;
    }

    public String validarGenero() {
        final String[] selectedItem = {""};
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedItem[0] = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedItem[0] = "";
            }
        });
        return selectedItem[0];
    }
    public void intentLogin() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }
}