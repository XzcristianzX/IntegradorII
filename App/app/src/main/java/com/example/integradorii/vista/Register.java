package com.example.integradorii.vista;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.User;

public class Register extends AppCompatActivity {

    private Button btRegister;
    private ImageButton btCerrar;
    private EditText etUsuario, etNombre, etFechaNacimiento, etTelefono, etCorreo, etPassword, etPasswordConfi;
    private Spinner spinnerGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register);


        btRegister= findViewById(R.id.btRegister);
        btCerrar = findViewById(R.id.cerrar);


        etUsuario = findViewById(R.id.userRegister);
        etNombre = findViewById(R.id.nameRegister);
        etFechaNacimiento = findViewById(R.id.dateRegister);
        etTelefono = findViewById(R.id.phoneRegister);
        etCorreo = findViewById(R.id.mailRegister);
        etPassword = findViewById(R.id.paswordRegister);
        etPasswordConfi = findViewById(R.id.paswordValidarRegister);


        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        spinnerGender = findViewById(R.id.gender);

        // Definir un array de strings para los géneros
        String[] generos = {"M", "H", "I"};

        // Crear un ArrayAdapter utilizando el array de géneros y el diseño predeterminado
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar el ArrayAdapter al Spinner
        spinnerGender.setAdapter(adapter);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                register();
            }
        });

        btCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                atras(v);
            }
        });
    }


    public void register(){
        try {
            final Model model = new Model();
            String userRegister = etUsuario.getText().toString();
            String nombre=etNombre.getText().toString();
            String fecha = etFechaNacimiento.getText().toString();
            String correo = etCorreo.getText().toString();
            String pin=etPassword.getText().toString();
            String pin2=etPasswordConfi.getText().toString();
            String tel = etTelefono.getText().toString();
            String genero = spinnerGender.getSelectedItem().toString();


            if(genero.isEmpty() && tel.isEmpty() && correo.isEmpty() && nombre.isEmpty() && pin.isEmpty() && pin2.isEmpty() && pin.equals(pin2)){
                Toast.makeText(this, "ingrese todos los datos", Toast.LENGTH_SHORT).show();

            }else if( tel.length() != 10 || tel.matches("[a-zA-Z\\s]+")) {
                Toast.makeText(this, "debes ingresar un telefono valido", Toast.LENGTH_SHORT).show();

            }else if (!correo.contains("@") || !correo.contains(".") || correo.indexOf("@") == 0 || correo.lastIndexOf(".") == correo.length() - 1 || correo.lastIndexOf(".") - correo.indexOf("@") <= 1) {
                Toast.makeText(this, "debes ingresar el correo valido", Toast.LENGTH_SHORT).show();

            }else if(!nombre.matches(".*[a-zA-Z].*")){
                Toast.makeText(this, "debes ingresar el nombre valido", Toast.LENGTH_SHORT).show();

            }else if(pin.length() != 4|| pin.matches("[a-zA-Z\\s]+")) {
                Toast.makeText(this, "debes ingresar los 4 digitos del pin", Toast.LENGTH_SHORT).show();

            } else if (!pin.equals(pin2)){
                Toast.makeText(this, "no coinciden los 2 pines", Toast.LENGTH_SHORT).show();

            }else if (pin.matches("(\\d)\\1{3}")) {
                Toast.makeText(this, "El PIN no puede tener 4 números iguales", Toast.LENGTH_SHORT).show();

            } else if (hasConsecutiveNumbers(pin)) {
                Toast.makeText(this, "El PIN no puede contener ser consecutivos", Toast.LENGTH_SHORT).show();
            }else {




                model.registerUser(userRegister, nombre,fecha,correo,pin,null,tel,genero, new Model.UserCallback() {
                    @Override
                    public void onSuccess(User user) {
                        // Usuario autenticado correctamente
                        Toast.makeText(Register.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Register.this, Home.class);
                        intent.putExtra("correo",correo);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure() {
                        // Error de autenticación
                        Toast.makeText(Register.this, "Error: Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar los datos del usuario",
                    Toast.LENGTH_SHORT).show();
            Log.e("Error",e.getMessage());
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

    public void atras(View view){
        onBackPressed();
    }

}

