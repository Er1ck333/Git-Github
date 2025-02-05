package com.example.conversor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button miBoton;
    private Spinner listaValores;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        miBoton = findViewById(R.id.button);
        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String valorSeleccionado = listaValores.getSelectedItem().toString();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("valor_seleccionado", valorSeleccionado);
                startActivity(intent);
                responderPulsacion();
            }
        });
        listaValores = findViewById(R.id.spinner1);
        String[] Tipos1 = {"Peso", "Volumen", "Distancia", "Temperatura", "Capacidad"};
        ArrayAdapter<String>adaptador = new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item, Tipos1);
        listaValores.setAdapter(adaptador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    public void responderPulsacion() {
        Toast.makeText(MainActivity.this,
                "Boton pulsado", Toast.LENGTH_LONG).show();
    }
}