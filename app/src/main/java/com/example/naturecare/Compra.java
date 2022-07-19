package com.example.naturecare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Compra extends Activity {

    EditText etRut, etEmail, etRegion, etCiudad, etDireccion;
    Button registrar;
    String user, producto, rut, email, region, ciudad, direccion;
    int cantidad, idProducto, idUser;
    double iva, monto;

    RequestQueue requestQueue;

    private final String URL = "https://naturecare-app.000webhostapp.com/crud/compra.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compra);

        inicializar();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if(id==R.id.CRegistrar){
                    rut = etRut.getText().toString().trim();
                    email = etEmail.getText().toString().trim();
                    region = etRegion.getText().toString().trim();
                    ciudad = etCiudad.getText().toString().trim();
                    direccion = etDireccion.getText().toString().trim();

                    generarCompra(rut, email, region, ciudad, direccion);
                }
            }
        });
    }

    public void inicializar(){
        etRut = findViewById(R.id.CRut);
        etEmail = findViewById(R.id.CEmail);
        etRegion = findViewById(R.id.CRegion);
        etCiudad = findViewById(R.id.CCiudad);
        etDireccion = findViewById(R.id.CDireccion);

        registrar = findViewById(R.id.CRegistrar);
    }

    public void generarCompra(final String txtrut, final String txtemail, final String txtregion, final String txtciudad, final String txtdireccion){
        ProgressDialog progressDialog=new ProgressDialog(getApplicationContext());
        if(txtrut.isEmpty()){
            etRut.setError("Complete los campos");
        }else if(txtemail.isEmpty()){
            etEmail.setError("Complete los campos");
        }else if(txtregion.isEmpty()){
            etRegion.setError("Complete los campos");
        }else if(txtciudad.isEmpty()){
            etCiudad.setError("Complete los campos");
        }else if(txtdireccion.isEmpty()){
            etDireccion.setError("Complete los campos");
        }else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equalsIgnoreCase("Compra realizada")){
                        Toast.makeText(getApplicationContext(),"Compra realizada!",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }else{
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String>params=new HashMap<>();
                    params.put("Rut_cliente",txtrut);
                    params.put("Email",txtemail);
                    params.put("Region",txtregion);
                    params.put("Ciudad",txtciudad);
                    params.put("Direccion",txtdireccion);
                    return params;
                }
            };
            requestQueue.add(request);
        }
    }
}
