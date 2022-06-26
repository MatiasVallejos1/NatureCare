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
import com.example.naturecare.entidades.Publicacion;
import com.example.naturecare.entidades.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class RegistrarPublicacion extends Fragment {

    EditText etPublicacion;
    Button btnEnviar;
    RequestQueue requestQueue;
    Usuario usuario = new Usuario();

    private static final String URL = "http://192.168.1.9/naturecare/nuevaPublicacion.php";

    public RegistrarPublicacion() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_publicacion, container, false);
    }

    private void inicializar(){
        //EditText
        etPublicacion = getView().findViewById(R.id.Rpublicacion);
        //Button
        btnEnviar = getView().findViewById(R.id.button);
    }

    public void onClick(View v){
        int id = v.getId();

        if(id == R.id.button){

            String publicacion = etPublicacion.getText().toString().trim();
            int like = 0;
            int comentario = 0;
            Date hora = null;
            obtenerHora(hora);
            int idUsuario = idUsuario= usuario.getId();

            CreateUser(publicacion, like, comentario, hora, idUsuario);


        }
    }
    public void obtenerHora(Date date){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        simpleDate.format(date);
    }

    private void CreateUser(final String publicacion, final int like, final int comentario, final Date hora, final int idUsuario){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(),"Usuario registrado",Toast.LENGTH_SHORT).show();
                        etPublicacion.setText("");
                        Intent button = new Intent(getActivity(), Foro.class);
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
                params.put("Detalle", publicacion);
                params.put("Cantidad_like", String.valueOf(like));
                params.put("Cantidad_comentarios", String.valueOf(comentario));
                params.put("Hora_publicacion", String.valueOf(hora));
                params.put("Usuario_Personal_idUsuarioPersonal", String.valueOf(idUsuario));
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}