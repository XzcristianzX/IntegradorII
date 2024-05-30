package com.example.integradorii.vista.mascota;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Animal;

import java.util.Calendar;

public class RegisterPet extends AppCompatActivity {

    private EditText etName, etLocation, etWeight, etSize, etBirthdate;
    private Spinner spinnerType, spinnerRace, spinnerGender;
    private Button btnRegisterPet;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        model = new Model();

        etName = findViewById(R.id.etName1);
        etWeight = findViewById(R.id.etWeight);
        etSize = findViewById(R.id.etSize1);
        etBirthdate = findViewById(R.id.datein);
        btnRegisterPet = findViewById(R.id.btnRegisterPet);
        spinnerType = findViewById(R.id.spinnerType1);
        spinnerRace = findViewById(R.id.spinnerRace1);
        spinnerGender = findViewById(R.id.spinnerGender1);

        // Lista de opciones de género
        String[] genderOptions = {"Masculino", "Femenino", "Indefinido"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        // Lista de opciones de tipo de animal
        String[] typeOptions = {"Perro", "Gato", "Conejo", "Hámster", "Pájaro", "Pez"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeOptions);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(typeAdapter);

        // Configuración del botón de registro
        btnRegisterPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPet();
            }
        });

        // Configuración de la selección de la fecha de nacimiento
        etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Configuración de las opciones de raza al seleccionar el tipo de animal
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el tipo de animal seleccionado
                String selectedType = parent.getItemAtPosition(position).toString();

                // Obtener la lista de razas según el tipo de animal seleccionado
                String[] raceOptions = getRaceOptions(selectedType);

                // Crear un ArrayAdapter para las opciones de raza
                ArrayAdapter<String> raceAdapter = new ArrayAdapter<>(RegisterPet.this, android.R.layout.simple_spinner_item, raceOptions);
                raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRace.setAdapter(raceAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se necesita implementar nada aquí
            }
        });
    }

    private void registerPet() {
        // Obtener los valores seleccionados de los Spinners
        String type = spinnerType.getSelectedItem().toString();
        String race = spinnerRace.getSelectedItem().toString();
        String gender = spinnerGender.getSelectedItem().toString();

        // Obtener los valores de los EditText
        String name = etName.getText().toString();
//        String location = etLocation.getText().toString();
        String owner = Model.getShared(getApplicationContext(), "userId"); // Método para obtener el ID del dueño actual
        String weight = etWeight.getText().toString();
        String size = etSize.getText().toString();
        String birthdate = etBirthdate.getText().toString(); // Cambiar esto según la implementación de la fecha

        // Crear un objeto Animal con los datos ingresados
        Animal animal = new Animal(0, name, type, race, "", owner, weight, size, gender, null, null, true);

        // Llamar al método de registro en el modelo
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

    private void showDatePickerDialog() {
        // Obtener la fecha actual
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // Crear un DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RegisterPet.this,
                (DatePicker view, int yearSelected, int monthOfYear, int dayOfMonth) -> {
                    // Formatear la fecha seleccionada
                    String formattedDate = String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, yearSelected);
                    etBirthdate.setText(formattedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }

    // Método para obtener las opciones de raza según el tipo de animal seleccionado
    private String[] getRaceOptions(String selectedType) {
        switch (selectedType) {

            case "Perro":
                return getResources().getStringArray(R.array.dog_races);
            case "Gato":
                return getResources().getStringArray(R.array.cat_races);
            case "Conejo":
                return getResources().getStringArray(R.array.rabbit_races);
            case "Hámster":
                return getResources().getStringArray(R.array.hamster_races);
            case "Pájaro":
                return getResources().getStringArray(R.array.bird_races);
            case "Pez":
                return getResources().getStringArray(R.array.fish_races);
            default:
                return new String[0]; // Retornar una lista vacía por defecto
        }
    }
}
