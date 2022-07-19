package com.example.naturecare;

import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.naturecare.entidades.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    EditText etUsuario, etPass;
    String user, pass;
    Button button;
    TextView registrar;


    private static final String URL = "https://naturecare-app.000webhostapp.com/crud/login.php";

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
                login();

                Intent button = new Intent(MainActivity2.this, Foro.class);
                button.putExtra("nombre", user);
                button.putExtra("pass", pass);
                startActivity(button);
            }
        });
    }
    private void login(){
        if(etUsuario.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese usuario",Toast.LENGTH_SHORT).show();
        }else if(etPass.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese su contraseña",Toast.LENGTH_SHORT).show();
        }else{
            user=etUsuario.toString().trim();
            pass=etPass.toString().trim();
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equalsIgnoreCase("Igreso correctamente")){
                        etUsuario.setText("");
                        etPass.setText("");
                        Intent intent = new Intent(getApplicationContext(),Foro.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity2.this,response,Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity2.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros=new HashMap<>();
                    parametros.put("Nombre",user);
                    parametros.put("Pass",pass);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
            requestQueue.add(request);
        }
    }
}