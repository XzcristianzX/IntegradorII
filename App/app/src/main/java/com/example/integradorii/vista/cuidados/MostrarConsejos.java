package com.example.integradorii.vista.cuidados;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.integradorii.Api.Model;
import com.example.integradorii.R;
import com.example.integradorii.adaptadores.ConsejosAdapter;
import com.example.integradorii.estructura.Careful;

import java.util.ArrayList;
import java.util.List;

public class MostrarConsejos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ConsejosAdapter adapter;
    private List<Careful> consejos;
    private int breedId;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_consejos);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list and adapter
        consejos = new ArrayList<>();
        adapter = new ConsejosAdapter(consejos);
        recyclerView.setAdapter(adapter);

        // Initialize ApiService
        model = new Model();

        // Get the breedId from the Intent extras
        Intent intent = getIntent();
        breedId = intent.getIntExtra("breedId", -1);

        if (breedId == -1) {
            Toast.makeText(this, "Invalid breed ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Fetch the data
        fetchConsejos(breedId);
    }

    private void fetchConsejos(int breedId) {
        model.getCuidados(breedId, new Model.CarefulCallback() {
            @Override
            public void onSuccess(List<Careful> carefulList) {
                consejos.addAll(carefulList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MostrarConsejos.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
