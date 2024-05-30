package com.example.integradorii.vista.vacunas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradorii.Adaptadores.AdaptadorElegirMascota;
import com.example.integradorii.Adaptadores.AdaptadorPost;
import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.estructura.Animal;
import com.example.integradorii.estructura.Post;
import com.example.integradorii.vista.Login;
import com.example.integradorii.vista.Verificar;

import java.util.ArrayList;
import java.util.List;

public class SelectAnimalForVaccine extends AppCompatActivity {

    private List<Animal> myAnimalsList;
    private AdaptadorElegirMascota adaptadorElegirMascota;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_animal_for_vaccine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView =  findViewById(R.id.reciclerchoosepets);
        Model model = new Model();

        int iduser = Integer.parseInt(Model.getShared(getApplicationContext(), "userId"));
        model.getPetsToChoose(iduser, new Model.getMyAnimals() {
            @Override
            public void onSuccess(List<Animal> myAnimals) {

                myAnimalsList = new ArrayList<>();
                myAnimalsList.addAll(myAnimals);
                adaptadorElegirMascota = new AdaptadorElegirMascota(myAnimalsList, position -> {
                    Intent intent = new Intent(SelectAnimalForVaccine.this, Vacunas.class);
                    intent.putExtra("idPet", position);
                    startActivity(intent);
                });
                recyclerView.setAdapter(adaptadorElegirMascota);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(), "Error de servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}