package com.example.naturecare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.naturecare.entidades.Publicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;


public class PrimerContenedor extends Fragment {

    TextView txtNombre, txtPublicacion, txtLike, txtComentar;

    RecyclerView listaPublicacion;
    RequestQueue requestQueue;
    List<Publicacion> lista;
    Publicacion publicacion;
    private static final String URL = "http://192.168.1.6/naturecare/readPublicacion.php";

    public PrimerContenedor() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_primer_contenedor, container, false);
        // Inflate the layout for this fragment


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        requestQueue = Volley.newRequestQueue(requireContext());

        txtNombre = (TextView) view.findViewById(R.id.txtPUNombre);
        txtPublicacion = (TextView) view.findViewById(R.id.txtPublicacion);
        txtLike = (TextView) view.findViewById(R.id.txtPULike);
        txtComentar = (TextView) view.findViewById(R.id.txtPUComentar);

        lista = new ArrayList<>();

        listaPublicacion = (RecyclerView) view.findViewById(R.id.listPublicacion);
        listaPublicacion.setHasFixedSize(true);
        listaPublicacion.setLayoutManager(new LinearLayoutManager(view.getContext()));

        listarPublicaciones();
        /*
        datosPublicaciones = new DatosPublicaciones(requireContext(),publicacionArrayList);
        listView.setAdapter(datosPublicaciones);
        listarPublicaciones();*/
    }

    public void listarPublicaciones() {
        StringRequest request = new StringRequest(Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject publicacionOb = jsonArray.getJSONObject(i);

                                lista.add(new Publicacion(
                                        publicacionOb.getInt("id"),
                                        publicacionOb.getString("nombre"),
                                        publicacionOb.getString("detalle"),
                                        publicacionOb.getInt("like"),
                                        publicacionOb.getInt("comentario")
                                ));


                            }

                            DatosPublicaciones datos = new DatosPublicaciones(getActivity(),lista);
                            listaPublicacion.setAdapter(datos);

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
/*
    }, error -> Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show());
        Volley.newRequestQueue(requireContext()).add(request);
        //requestQueue.add(request);*/
    }
}