package com.example.integradorii.vista;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Actulizar extends AppCompatActivity {

    private EditText editTextUserName, editTextPassword, editTextPhone;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actulizar);

        // Asociar los EditTexts y el Button con sus respectivos IDs en el XML
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Verificar si el extra "user_name" está presente
            if (extras.containsKey("user_name")) {
                // Obtener el valor de "user_name"
                userName = extras.getString("user_name");
                Toast.makeText(this, "hola: "+userName, Toast.LENGTH_SHORT).show();
            }
        }



        Button buttonUpdateUser = findViewById(R.id.buttonUpdateUser);

        buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario

                String nombre = editTextUserName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();

                // Aquí puedes enviar los datos del usuario, incluida la imagen, para su actualización
                if (selectedImageUri != null) {
                    // Aquí puedes incluir el código para manejar la imagen seleccionada
                    Toast.makeText(Actulizar.this, "Imagen seleccionada: " + selectedImageUri.toString(), Toast.LENGTH_SHORT).show();
                }
                final Model model = new Model();


                // Aquí puedes escribir el código para actualizar los datos del usuario, por ejemplo, enviar los datos a una base de datos o a un servicio web
                // En este ejemplo, simplemente mostraremos un mensaje de confirmación
                Toast.makeText(Actulizar.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();


                model.actulizarUser(nombre, password,"img",phone,userName, new Model.LoginCallback() {
                    @Override
                    public void onSuccess(User user) {
                        // Usuario autenticado correctamente
                        Toast.makeText(Actulizar.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Actulizar.this, Home.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure() {
                        // Error de autenticación
                        Toast.makeText(Actulizar.this, "Error: Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                });
                // Resto del código para actualizar el usuario
            }
        });
    }


    public void selectImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
        }
    }
}

