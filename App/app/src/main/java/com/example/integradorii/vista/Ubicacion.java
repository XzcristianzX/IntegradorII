package com.example.integradorii.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ubicacion extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion);
        btnSalir = findViewById(R.id.back_arrow);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAHome();
            }
        });

        // Obtener el SupportMapFragment y registrar el callback cuando el mapa está listo para ser utilizado
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onClickSalir(View view) {
        volverAHome();
    }

    private void volverAHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Ubicación a mostrar en el mapa (coordenadas de ejemplo)
        LatLng ubicacion = new LatLng(-33.867, 151.206); // Ejemplo: Sidney, Australia

        // Agregar marcador en la ubicación
        MarkerOptions markerOptions = new MarkerOptions().position(ubicacion).title("Ubicación de ejemplo");
        Marker marker = mMap.addMarker(markerOptions);

        // Mover la cámara al marcador y ajustar el zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 12));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
