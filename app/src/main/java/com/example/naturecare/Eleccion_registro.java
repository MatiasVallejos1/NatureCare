package com.example.naturecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Eleccion_registro extends AppCompatActivity {

    Button empresarial, personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_registro);

        personal = findViewById(R.id.BtnPersonal);
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button = new Intent(getApplicationContext(), Registro_usuario.class);
                startActivity(button);
            }
        });

        empresarial = findViewById(R.id.BtnEmpresarial);
        empresarial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent empresa = new Intent(getApplicationContext(), Registro_empresarial.class);
                empresa.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(empresa);
            }
        });
    }
}