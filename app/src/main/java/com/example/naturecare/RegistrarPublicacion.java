package com.example.naturecare;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class RegistrarPublicacion extends Fragment {

    EditText etPublicacion;
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

        requestQueue = Volley.newRequestQueue(requireActivity());
        //EditText
        etPublicacion= requireActivity().findViewById(R.id.RPUpublicacion);

        //Button
        btnEnviar = requireActivity().findViewById(R.id.RPUregistrar);

        btnEnviar.setOnClickListener(v -> {
            int id = v.getId();

            if(id == R.id.RPUregistrar){
                String detalle = etPublicacion.getText().toString().trim();
                Date hora = new Date();
                obtenerHora(hora);

                CreatePublicacion(detalle, hora);
            }
        });
    }

    public void obtenerHora(Date date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        simpleDate.format(date);
    }

    public void CreatePublicacion(final String detalle, final Date hora) {

        ProgressDialog progressDialog = new ProgressDialog(requireActivity());
        if (detalle.isEmpty()) {
            etPublicacion.setError("Complete los campos");
        }else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, URL, response -> {
                if (response.equalsIgnoreCase("datos insertados")) {
                    Toast.makeText(requireActivity(), "datos ingresados", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(requireActivity(), response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


            }, error -> {
                Toast.makeText(requireActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }) {
                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<>();
                    params.put("Detalle", detalle);
                    params.put("Hora_publicacion", String.valueOf(hora));
                    return params;
                }
            };
            requestQueue.add(request);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_publicacion, container, false);
    }
}