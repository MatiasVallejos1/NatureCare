package com.example.naturecare;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.naturecare.entidades.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    EditText etUsuario, etPass;
    Button button;
    TextView registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = (EditText) findViewById(R.id.usuario);
        etPass = (EditText) findViewById(R.id.password);
        registrar = (TextView)findViewById(R.id.registrarse);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(MainActivity2.this, Eleccion_registro.class);
                startActivity(registrar);
            }
        });

        button = (Button)findViewById(R.id.iniciarSesion);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button = new Intent(getApplicationContext(), Foro.class);
                button.putExtra("Nombre", etUsuario.getText().toString().trim());
                button.putExtra("Pass", etPass.getText().toString().trim());
                startActivity(button);
            }
        });
    }
}