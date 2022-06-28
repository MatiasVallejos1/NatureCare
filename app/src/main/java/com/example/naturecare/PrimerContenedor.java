package com.example.naturecare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.naturecare.entidades.Publicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.zip.Inflater;


public class PrimerContenedor extends Fragment {

    Button registrar;
    ListView listView;
    DatosPublicaciones datosPublicaciones;
    public static ArrayList<Publicacion>publicacionArrayList=new ArrayList<>();
    Publicacion publicacion;
    static String URL = "https://naturecare-app.000webhostapp.com/crud/readPublicacion.php";

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

        listView = view.findViewById(R.id.listPublicacion);
        datosPublicaciones = new DatosPublicaciones(requireContext(),publicacionArrayList);
        listView.setAdapter(datosPublicaciones);
        //listarPublicaciones();
    }

    private void listarPublicaciones(){
        StringRequest request = new StringRequest(Request.Method.POST, URL, response -> {
            publicacionArrayList.clear();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String exito = jsonObject.getString("exito");
                JSONArray jsonArray = jsonObject.getJSONArray("datos");

                if(exito.equals("1")){
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        int idPublicacion = object.getInt("idpublicacion");
                        String nombre = object.getString("Nombre");
                        String detalle = object.getString("Detalle");
                        int like = object.getInt("Cantidad_like");
                        int comentario = object.getInt("Cantidad_comentarios");
                        Date hora = Date.valueOf(object.getString("Hora_publicacion"));

                        publicacion = new Publicacion(idPublicacion,nombre,detalle,like,comentario,hora);
                        publicacionArrayList.add(publicacion);
                        datosPublicaciones.notifyDataSetChanged();
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue= Volley.newRequestQueue(requireContext());
        requestQueue.add(request);
    }
}