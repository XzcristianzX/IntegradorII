package com.example.integradorii.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.User;
import com.example.integradorii.vista.mascota.PetProfile;

public class UserProfile extends AppCompatActivity {

    private ImageView backArrow, profileUser, profilePet;
    private Model model;
    private EditText editTextUsuario, editTextCorreo, editTelefono, editGenero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTelefono = findViewById(R.id.editTelefono);
        editGenero = findViewById(R.id.editGenero);

        model = new Model();
        model.getMyUser(getApplicationContext(), new Model.UserCallback() {
            @Override
            public void onSuccess(User user) {
                editTextUsuario.setText(user.getUser_name());
                editTextCorreo.setText(user.getMail());
                editTelefono.setText(user.getPhone());
                editGenero.setText(user.getGender());
            }

            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(), "Error de servidor", Toast.LENGTH_SHORT).show();
            }
        });


        backArrow = findViewById(R.id.back_arrow_userprofile);
        profilePet = findViewById(R.id.pet_profile_userprofile);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        profilePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, PetProfile.class);
                startActivity(intent);
            }
        });
    }
}