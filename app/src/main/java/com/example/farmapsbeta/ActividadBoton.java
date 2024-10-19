package com.tu.paquete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.farmapsbeta.R;

public class ActividadBoton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton);  // El layout activity_boton.xml
    }

    // Método que se ejecuta al presionar el botón "Vamos al mapa"
    public void onMapClick(View view) {
        Intent intent;  // Referencia a la actividad del mapa
        intent = new Intent(ActividadBoton.this,
                com.example.farmapsbeta.MapActivity.class);
        startActivity(intent);
    }
}
