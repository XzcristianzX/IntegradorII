package com.example.integradorii.vista.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.R;
import com.example.integradorii.vista.Home;
import com.example.integradorii.vista.usuario.Usuario;

public class RegistrarPost extends AppCompatActivity {
    private ImageView backArrow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacunas);
        backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarPost.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

}