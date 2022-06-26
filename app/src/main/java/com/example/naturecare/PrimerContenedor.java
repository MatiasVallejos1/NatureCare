package com.example.naturecare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.naturecare.entidades.Publicacion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;


public class PrimerContenedor extends Fragment {

    TextView tvNombre, tvPublicacion, tvLike, tvComentar;
    ArrayList<Publicacion> lista;
    RecyclerView publicacion;
    RequestQueue requestQueue;
    public static final String URL = "http://192.168.1.9/naturecare/readPublicacion.php";

    public PrimerContenedor() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvNombre = (TextView) getActivity().findViewById(R.id.txtNombre);
        tvPublicacion = (TextView) getActivity().findViewById(R.id.txtPublicacion);
        tvLike = (TextView) getActivity().findViewById(R.id.txtLike);
        tvComentar = (TextView) getActivity().findViewById(R.id.txtComentar);

        /*publicacion = (RecyclerView) getActivity().findViewById(R.id.ListaPublicacion);
        publicacion.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        lista = new ArrayList<Publicacion>();

        DatosPublicaciones datosPublicaciones = new DatosPublicaciones(lista);
        publicacion.setAdapter(datosPublicaciones);*/

        requestQueue = Volley.newRequestQueue(getContext());
        readPublicacion();
    }

    private void readPublicacion(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String detalle, hora, idUsuario;
                        int like, comentario;
                        try {
                            detalle = response.getString("Detalle");
                            like = response.getInt("Cantidad_like");
                            comentario = response.getInt("Cantidad_comentarios");
                            hora = response.getString("Hora_publicacion");
                            idUsuario = response.getString("Usuario_Personal_idUsuarioPersonal");

                            tvPublicacion.setText(detalle);
                            tvLike.setText(like);
                            tvComentar.setText(comentario);
                            tvNombre.setText(idUsuario);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_primer_contenedor, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }
}