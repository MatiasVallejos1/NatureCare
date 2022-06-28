package com.example.naturecare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro_usuario extends AppCompatActivity {

    EditText etEmail, etPhone, etUser, etPass;
    Button button;

    RequestQueue requestQueue;

    private static final String URL = "https://naturecare-app.000webhostapp.com/crud/save.php";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        requestQueue = Volley.newRequestQueue(this);
        inicializar();

        button.setOnClickListener(this::onClick);
    }

    private void inicializar(){
        //EditText
        etEmail = findViewById(R.id.RPemail);
        etPhone = findViewById(R.id.RPphone);
        etUser = findViewById(R.id.RPusuario);
        etPass = findViewById(R.id.RPpassword);
        //Button
        button = findViewById(R.id.registrar);
    }

    public void onClick(View v){
        int id = v.getId();

        if(id == R.id.registrar){
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String usuario = etUser.getText().toString().trim();
            String password = etPass.getText().toString().trim();
            int tipo = 1;

            CreateUser(usuario, phone, email, password, tipo);
        }
    }

    private void CreateUser(final String usuario, final String phone, final String email, final String password, final int tipo){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Usuario registrado",Toast.LENGTH_SHORT).show();
                        etUser.setText("");
                        etPhone.setText("");
                        etEmail.setText("");
                        etPass.setText("");
                        Intent button = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(button);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Nombre", usuario);
                params.put("Phone", phone);
                params.put("Email", email);
                params.put("Pass", password);
                params.put("tipo_usuario_id_tipo_usuario", String.valueOf(tipo));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
