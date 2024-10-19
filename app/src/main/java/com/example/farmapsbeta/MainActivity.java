package com.tu.paquete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.farmapsbeta.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Este es el archivo del login
    }

    // Método que se ejecuta cuando el usuario presiona el botón de login
    public void onLoginClick(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (user.equals("juan alcachofa") && pass.equals("1234")) {
            // Redirigimos a la clase ActividadBoton
            Intent intent = new Intent(MainActivity.this, com.tu.paquete.ActividadBoton.class);
            startActivity(intent);
        } else {
            // Mostramos un mensaje de error
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}
