package com.example.naturecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class RegistrarProducto extends Fragment {

    EditText etNombre, etCantidad, etDetalle, etFicha, etEstado, etMonto;
    Button registrar;

    RequestQueue requestQueue;


    private final String URL = "https://naturecare-app.000webhostapp.com/crud/nuevoProducto.php";

    public RegistrarProducto() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requestQueue = Volley.newRequestQueue(requireActivity());
        //EditText
        etNombre= requireActivity().findViewById(R.id.RPnombre);
        etCantidad= requireActivity().findViewById(R.id.RPcantidad);
        etDetalle= requireActivity().findViewById(R.id.RPdetalle);
        etFicha= requireActivity().findViewById(R.id.RPficha);
        etEstado= requireActivity().findViewById(R.id.RPestado);
        etMonto= requireActivity().findViewById(R.id.RPmonto);

        //Button
        registrar = requireActivity().findViewById(R.id.RPregistrar);

        registrar.setOnClickListener(v -> {
            int id = v.getId();

            if(id == R.id.RPregistrar){
                String nombre = etNombre.getText().toString().trim();
                int cantidad = Integer.parseInt(etCantidad.getText().toString());
                String detalle = etDetalle.getText().toString().trim();
                String ficha = etFicha.getText().toString().trim();
                String estado = etEstado.getText().toString().trim();
                Double monto = Double.parseDouble(etMonto.getText().toString());
                //int idUser = usuario.obtenerUsuario(0);
                int idUser = 1;


                CreateProducto(nombre, cantidad, detalle, ficha, estado, monto, idUser);
        }
    });
    }

    public void CreateProducto(final String nombre, final int cantidad, final String detalle, final String ficha, final String estado, final Double monto, final int idUser){

        ProgressDialog progressDialog=new ProgressDialog(requireActivity());
        if(nombre.isEmpty()){
            etNombre.setError("Complete los campos");
        }else if(cantidad==0){
            etCantidad.setError("Complete los campos");
        }else if(detalle.isEmpty()){
            etDetalle.setError("Complete los campos");
        }else if(ficha.isEmpty()){
            etFicha.setError("Complete los campos");
        }else if(estado.isEmpty()){
            etEstado.setError("Complete los campos");
        }else if(monto==null){
            etMonto.setError("Complete los campos");
        }else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST, URL, response -> {
                if (response.equalsIgnoreCase("datos insertados")) {
                    Toast.makeText(requireActivity(), "datos ingresados", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(requireActivity(), response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }, error -> {
                Toast.makeText(requireActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }){
                @Override
                protected Map<String, String> getParams(){

                    Map<String, String>params=new HashMap<>();
                    params.put("Nombre", nombre);
                    params.put("Cantidad", String.valueOf(cantidad));
                    params.put("Detalle", detalle);
                    params.put("Ficha_tecnica", ficha);
                    params.put("Estado_producto", estado);
                    params.put("Monto", String.valueOf(monto));
                    return params;
                }
            };
            requestQueue.add(request);
        }
/*
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                    Toast.makeText(getContext(),"Producto registrado",Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    etCantidad.setText("");
                    etDetalle.setText("");
                    etFicha.setText("");
                    etEstado.setText("");
                    etMonto.setText("");
                },
                error -> {

                }
        ){
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Nombre", nombre);
                params.put("Cantidad", String.valueOf(cantidad));
                params.put("Detalle", detalle);
                params.put("Ficha_tecnica", ficha);
                params.put("Estado_producto", estado);
                params.put("Monto", String.valueOf(monto));
                return params;
            }
        };
        requestQueue.add(stringRequest);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_producto, container, false);
    }
}