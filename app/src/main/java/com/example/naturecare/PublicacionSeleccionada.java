package com.example.naturecare;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.naturecare.entidades.Publicacion;

import java.io.Serializable;
import java.util.zip.Inflater;


public class PublicacionSeleccionada extends Fragment implements Serializable {

    private TextView txtUser, txtDetalle, txtLike, txtComentario;
    private Publicacion publicacion;

    public PublicacionSeleccionada() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtUser = (TextView) view.findViewById(R.id.txtPUSNombre);
        txtDetalle = (TextView) view.findViewById(R.id.txtPUSPublicacion);
        txtLike = (TextView) view.findViewById(R.id.txtPUSLike);
        txtComentario = (TextView) view.findViewById(R.id.txtPUSComentar);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publicacion_seleccionada, container, false);
    }
}