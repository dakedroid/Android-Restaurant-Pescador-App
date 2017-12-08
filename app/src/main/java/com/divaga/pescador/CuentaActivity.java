package com.divaga.pescador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CuentaActivity extends AppCompatActivity {

    Button button;

    public static TextView precio;
    int precioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        button = findViewById(R.id.btnFinalizar);

        precio = findViewById(R.id.total);

        for(int i = 1; i < Pescador.lista.size(); i++){

            precioTotal += Integer.valueOf(Pescador.lista.get(i).getMonto()) * Integer.valueOf(Pescador.lista.get(i).getCantidad());

        }

        precio.setText(String.valueOf(precioTotal));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Pescador.lista.size() > 1){

                    startActivity(new Intent(CuentaActivity.this, FinalizarActivity.class));

                }else {
                    Toast.makeText(CuentaActivity.this, "Regrese y agrege algun producto", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
