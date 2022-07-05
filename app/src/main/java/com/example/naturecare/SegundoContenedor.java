package com.example.naturecare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.naturecare.entidades.Producto;
import com.example.naturecare.entidades.Publicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SegundoContenedor extends Fragment{

    RecyclerView listaProducto;
    RequestQueue requestQueue;
    List<Producto> lista;
    Producto producto;
    private static final String URL = "http://192.168.1.6/naturecare/readProducto.php";

    public SegundoContenedor() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaProducto = (RecyclerView) view.findViewById(R.id.ListaProducto);
        listaProducto.setHasFixedSize(true);
        listaProducto.setLayoutManager(new LinearLayoutManager(view.getContext()));

        lista = new ArrayList<>();

        listarProductos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_segundo_contenedor, container, false);
    }

    public void listarProductos() {
        StringRequest request = new StringRequest(Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject ProductoOb = jsonArray.getJSONObject(i);

                                lista.add(new Producto(
                                        ProductoOb.getInt("id"),
                                        ProductoOb.getString("name"),
                                        ProductoOb.getInt("cantidad"),
                                        ProductoOb.getString("detalle"),
                                        ProductoOb.getString("ficha"),
                                        ProductoOb.getString("estado"),
                                        ProductoOb.getDouble("monto"),
                                        ProductoOb.getString("user")
                                ));


                            }

                            DatosProductos datos = new DatosProductos(getActivity(),lista);
                            listaProducto.setAdapter(datos);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(getActivity()).add(request);
    }
}