package com.example.integradorii.vista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.MainActivity;
import com.example.integradorii.R;
import com.example.integradorii.vista.mascota.PetProfile;

public class Consejos extends AppCompatActivity {
    private ImageView backArrow, profileUser, profilePet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejos);
        backArrow = findViewById(R.id.back_arrow_consejos);
        profileUser = findViewById(R.id.user_profile_consejos);
        profilePet = findViewById(R.id.pet_profile_consejos);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Consejos.this, UserProfile.class);
                startActivity(intent);
            }
        });
        profilePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consejos.this, PetProfile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
