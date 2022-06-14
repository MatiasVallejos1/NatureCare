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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registro_empresarial extends AppCompatActivity {
    EditText etEmail, etPhone, etUser, etPass;
    Button button;

    RequestQueue requestQueue;

    private static final String URL = "http://192.168.1.6/naturecare/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empresarial);

        requestQueue = Volley.newRequestQueue(this);
        inicializar();

        button.setOnClickListener(this::onClick);

    }

    private void inicializar(){
        //EditText
        etEmail = findViewById(R.id.REemail);
        etUser = findViewById(R.id.REusuario);
        etPhone = findViewById(R.id.REphone);
        etPass = findViewById(R.id.REpassword);
        //Button
        button = findViewById(R.id.REregistrar);
    }

    public void onClick(View v){
        int id = v.getId();

        if(id == R.id.REregistrar){

            String nombre = etUser.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPass.getText().toString().trim();
            String tipo = "2";

            CreateUser(nombre, phone, email, password, tipo);


        }
    }

    private void CreateUser(final String nombre, final  String phone, final  String email, final String password, final String tipo){

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
                params.put("Nombre", nombre);
                params.put("Phone", phone);
                params.put("Email", email);
                params.put("Pass", password);
                params.put("tipo_usuario_id_tipo_usuario", tipo);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}
