package com.example.naturecare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.naturecare.entidades.Usuario;

import java.util.HashMap;
import java.util.Map;


public class RegistrarProducto extends Fragment {

    EditText etNombre, etCantidad, etDetalle, etFicha, etEstado, etMonto;
    Button registrar;

    RequestQueue requestQueue;

    Usuario usuario = new Usuario();

    private static final String URL = "http://192.168.1.9/naturecare/nuevoProducto.php";

    public RegistrarProducto() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getContext());
        inicializar();

        registrar.setOnClickListener(this::onClick);
    }

    private void inicializar(){
        //EditText
        etNombre = getActivity().findViewById(R.id.RPnombre);
        etCantidad = getActivity().findViewById(R.id.RPcantidad);
        etDetalle = getActivity().findViewById(R.id.RPdetalle);
        etFicha = getActivity().findViewById(R.id.RPficha);
        etEstado = getActivity().findViewById(R.id.RPestado);
        etMonto = getActivity().findViewById(R.id.RPmonto);

        //Button
        registrar = getActivity().findViewById(R.id.RPregistrar);
    }
    public void onClick(View v){
        int id = v.getId();

        if(id == R.id.registrar){
            String nombre = etNombre.getText().toString().trim();
            int cantidad = Integer.parseInt(etCantidad.getText().toString());
            String detalle = etDetalle.getText().toString().trim();
            String ficha = etFicha.getText().toString().trim();
            String estado = etEstado.getText().toString().trim();
            Double monto = Double.parseDouble(etMonto.getText().toString());
            int idUser;
            idUser = usuario.getId();


            CreateProducto(nombre, cantidad, detalle, ficha, estado, monto, idUser);
        }
    }

    private void CreateProducto(final String nombre, final int cantidad, final String detalle, final String ficha, final String estado, final Double monto, final int idUser){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(),"Usuario registrado",Toast.LENGTH_SHORT).show();
                        etNombre.setText("");
                        etCantidad.setText("");
                        etDetalle.setText("");
                        etFicha.setText("");
                        etEstado.setText("");
                        etMonto.setText("");
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
                params.put("Cantidad", String.valueOf(cantidad));
                params.put("Detalle", detalle);
                params.put("Ficha_tecnica", ficha);
                params.put("Estado_producto", estado);
                params.put("Monto", String.valueOf(monto));
                params.put("usuario_idUsuarioPersonal",String.valueOf(idUser));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_producto, container, false);
    }
}