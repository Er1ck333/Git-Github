package com.example.conversor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private Button miBoton, convertButton;
    private Spinner spinner2, spinner3;
    private EditText inputValue, outputValue;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        miBoton = findViewById(R.id.button2);
        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                responderPulsacion();
            }
        });
        String valorSeleccionado = getIntent().getStringExtra("valor_seleccionado");
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        inputValue = findViewById(R.id.inputValue);
        outputValue = findViewById(R.id.outputValue);
        convertButton = findViewById(R.id.convertButton);

        String[] opcionesSpinner2 = new String[0];
        String[] opcionesSpinner3 = new String[0];

        if (valorSeleccionado != null) {
            switch (valorSeleccionado) {
                case "Peso":
                    opcionesSpinner2 = new String[]{"Microgramo", "Miligramo", "Gramo", "Kg", "Tonelada"};
                    opcionesSpinner3 = new String[]{"Microgramo", "Miligramo", "Gramo", "Kg", "Tonelada"};
                    break;
                case "Volumen":
                    opcionesSpinner2 = new String[]{"Mililitro", "Litro", "Metro cubico"};
                    opcionesSpinner3 = new String[]{"Mililitro", "Litro", "Metro cubico"};
                    break;
                case "Distancia":
                    opcionesSpinner2 = new String[]{"Milimetro", "Centimetro", "Metro", "Kilometro"};
                    opcionesSpinner3 = new String[]{"Milimetro", "Centimetro", "Metro", "Kilometro"};
                    break;
                case "Temperatura":
                    opcionesSpinner2 = new String[]{"Celsius", "Fahrenheit", "Kelvin"};
                    opcionesSpinner3 = new String[]{"Celsius", "Fahrenheit", "Kelvin"};
                    break;
                case "Capacidad":
                    opcionesSpinner2 = new String[]{"Bit", "Bytes", "Kilobytes", "Gigabytes", "Terabytes"};
                    opcionesSpinner3 = new String[]{"Bit", "Bytes", "Kilobytes", "Gigabytes", "Terabytes"};
                    break;
            }
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesSpinner2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesSpinner3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarConversion(valorSeleccionado);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void responderPulsacion() {
        Toast.makeText(MainActivity2.this,
                "Boton pulsado", Toast.LENGTH_LONG).show();
    }
    private void realizarConversion(String tipo) {
        String unidadOrigen = spinner2.getSelectedItem().toString();
        String unidadDestino = spinner3.getSelectedItem().toString();

        if (inputValue.getText().toString().isEmpty()) {
            Toast.makeText(this, "Introduce un valor para convertir", Toast.LENGTH_SHORT).show();
            return;
        }
        // Obtener el valor ingresado
        double valor = Double.parseDouble(inputValue.getText().toString());
        double resultado = 0;

        // Lógica de conversión según el tipo seleccionado
        switch (tipo) {
            case "Peso":
                resultado = convertirPeso(valor, unidadOrigen, unidadDestino);
                break;
            case "Volumen":
                resultado = convertirVolumen(valor, unidadOrigen, unidadDestino);
                break;
            case "Distancia":
                resultado = convertirDistancia(valor, unidadOrigen, unidadDestino);
                break;
            case "Temperatura":
                resultado = convertirTemperatura(valor, unidadOrigen, unidadDestino);
                break;
            case "Capacidad":
                resultado = convertirCapacidad(valor, unidadOrigen, unidadDestino);
                break;
        }
        outputValue.setText(String.valueOf(resultado));
    }

    private double convertirPeso(double valor, String origen, String destino) {
        double[] array1 = new double[0];
        double resultado = 0;
        switch (destino) {
            case "Microgramo":
                array1 = new double[] {1, 1000, 1000000, 1000000000, 1000000000};
                break;
            case "Miligramo":
                array1 = new double[] {0.001, 1, 1000, 1000000, 1000000};
                break;
            case "Gramo":
                array1 = new double[] {0.000001, 0.001, 1, 1000, 1000};
                break;
            case "Kg":
                array1 = new double[] {0.000000001, 0.000001, 0.001, 1, 1};
                break;
            case "Tonelada":
                array1 = new double[] {0.000000000001, 0.000000001, 0.000001, 0.001, 0.001};
                break;
        }
        switch (origen) {
            case "Microgramo":
                resultado = valor * array1[0];
                break;
            case "Miligramo":
                resultado = valor * array1[1];
                break;
            case "Gramo":
                resultado = valor * array1[2];
                break;
            case "Kg":
                resultado = valor * array1[3];
                break;
            case "Tonelada":
                resultado = valor * array1[4] * 1000;
                break;
        }
        return resultado;
    }
    private double convertirVolumen(double valor, String origen, String destino) {
        double[] array1 = new double[0];
        double resultado = 0;
        switch (destino) {
            case "Mililitro":
                array1 = new double[] {1, 1000, 1000000};
                break;
            case "Litro":
                array1 = new double[] {0.001, 1, 1000};
                break;
            case "Metro cubico":
                array1 = new double[] {0.000001, 0.001, 1};
                break;
        }
        switch (origen) {
            case "Mililitro":
                resultado = valor * array1[0];
                break;
            case "Litro":
                resultado = valor * array1[1];
                break;
            case "Metro cubico":
                resultado = valor * array1[2];
                break;
        }
        return resultado;
    }
    private double convertirDistancia(double valor, String origen, String destino){
        double[] array1 = new double[0];
        double resultado = 0;
        switch (destino) {
            case "Milimetro":
                array1 = new double[] {1, 1000, 1000000, 1000000000};
                break;
            case "Centimetro":
                array1 = new double[] {0.001, 1, 1000, 1000000};
                break;
            case "Metro":
                array1 = new double[] {0.000001, 0.001, 1, 1000};
                break;
            case "Kilometro":
                array1 = new double[] {0.000001, 0.001, 0.001, 1};
                break;
        }
        switch (origen) {
            case "Milimetro":
                resultado = valor * array1[0];
                break;
            case "Centimetro":
                resultado = valor * array1[1];
                break;
            case "Metro":
                resultado = valor * array1[2];
                break;
            case "Kilometro":
                resultado = valor * array1[3];
                break;
        }
        return resultado;
    }
    private double DEcAf(double valor){
        return (valor * 9 / 5) + 32;
    }
    private double DEcAk(double valor){
        return valor + 273.15;
    }
    private double DEfAc(double valor){
        return (valor - 32) * 5 / 9;
    }
    private double DEfAk(double valor){
        return (DEfAc(valor)) + 273.15;
    }
    private double DEkAc(double valor){
        return valor - 273.15;
    }
    private double DEkAf(double valor){
        return (DEkAc(valor) * 9 / 5) + 32;
    }


    private double convertirTemperatura(double valor, String origen, String destino) {
        double resultado = 0;
        switch (origen) {
            case "Celsius":
                if (destino.equals("Celsius")) {
                    resultado = valor;
                } else if (destino.equals("Fahrenheit")) {
                    resultado = DEcAf(valor);
                } else if (destino.equals("Kelvin")) {
                    resultado = DEcAk(valor);
                }
                break;
            case "Fahrenheit":
                if (destino.equals("Celsius")) {
                    resultado = DEfAc(valor);
                } else if (destino.equals("Fahrenheit")) {
                    resultado = valor;
                } else if (destino.equals("Kelvin")) {
                    resultado = DEfAk(valor);
                }
                break;
            case "Kelvin":
                if (destino.equals("Celsius")) {
                    resultado = DEkAc(valor);
                } else if (destino.equals("Fahrenheit")) {
                    resultado = DEkAf(valor);
                } else if (destino.equals("Kelvin")) {
                    resultado = valor;
                }
                break;
        }
        return resultado;
    }
    private double convertirCapacidad(double valor, String origen, String destino){
        double resultado = 0;
        return resultado;
    }
}