package com.example.integradorii.vista.mascota;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Animal;

import java.util.Date;

public class RegisterPet extends AppCompatActivity {

    private EditText etName, etType, etRace, etLocation, etWeight, etSize, etGender, etBirthdate;
    private Button btnRegisterPet;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_pet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        model = new Model();

        etName = findViewById(R.id.etName);
        etType = findViewById(R.id.etType);
        etRace = findViewById(R.id.etRace);
        etWeight = findViewById(R.id.etWeight);
        etSize = findViewById(R.id.etSize);
        etGender = findViewById(R.id.etGender);
        etBirthdate = findViewById(R.id.etBirthdate);
        btnRegisterPet = findViewById(R.id.btnRegisterPet);

        btnRegisterPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPet();
            }
        });
    }

    private void registerPet() {
        String name = etName.getText().toString();
        String type = etType.getText().toString();
        String race = etRace.getText().toString();
        String location = etLocation.getText().toString();
        String owner = getOwnerId(); // Método para obtener el ID del dueño actual
        String weight = etWeight.getText().toString();
        String size = etSize.getText().toString();
        String gender = etGender.getText().toString();
        Date birthdate = getDateFromString(etBirthdate.getText().toString());

        Animal animal = new Animal(0, name, type, race, location, owner, weight, size, gender, null, birthdate, true);

        model.registerAnimal(animal, new Model.AnimalCallback() {
            @Override
            public void onSuccess(Animal registeredAnimal) {
                Toast.makeText(RegisterPet.this, "Mascota registrada exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure() {
                Toast.makeText(RegisterPet.this, "Error al registrar la mascota", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getOwnerId() {
        return Model.getShared(this, "userId");
    }

    private Date getDateFromString(String dateString) {
        // Implementar la conversión de String a Date según tu formato
        return new Date(); // Ejemplo
    }
}
