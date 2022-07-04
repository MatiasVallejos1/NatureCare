package com.example.naturecare;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class RegistrarPublicacion extends Fragment {

    TextView etPublicacion;
    Button btnEnviar;
    RequestQueue requestQueue;

    private final String URL = "https://naturecare-app.000webhostapp.com/crud/nuevaPublicacion.php";

    public RegistrarPublicacion() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requestQueue = Volley.newRequestQueue(getActivity());
        //EditText
        etPublicacion = requireActivity().findViewById(R.id.RPUpublicacion);

        //Button
        btnEnviar = requireActivity().findViewById(R.id.RPUregistrar);

        btnEnviar.setOnClickListener(v -> {
            int id = v.getId();

            if(id == R.id.RPUregistrar){

                String detalle = etPublicacion.getText().toString().trim();
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String hora = simpleDate.format(new Date());

                CreatePublicacion(detalle, hora);
            }
        });
    }

    public void CreatePublicacion(final String detalle, final String hora) {

        StringRequest request = new StringRequest(Request.Method.POST, URL, response -> {

                Toast.makeText(requireActivity(), "datos ingresados"+hora, Toast.LENGTH_SHORT).show();
                etPublicacion.setText("");

        }, error -> {
            Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("Detalle", detalle);
                params.put("Hora_publicacion", hora);
                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_publicacion, container, false);
    }
}