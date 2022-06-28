package com.example.naturecare;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.naturecare.entidades.Publicacion;

import java.util.List;

public class DatosPublicaciones extends ArrayAdapter<Publicacion> {

    Context context;
    List<Publicacion> ArrayPublicacion;

    public DatosPublicaciones(@NonNull Context context, List<Publicacion>ArraPublicacion) {
        super(context, R.layout.lista_publicacion, ArraPublicacion);
        this.context = context;
        this.ArrayPublicacion = ArraPublicacion;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_publicacion,null,true);

        TextView txtNombre=view.findViewById(R.id.txtPUNombre);
        TextView txtDetalle=view.findViewById(R.id.txtPUPublicacion);
        TextView txtLike=view.findViewById(R.id.txtPULike);
        TextView txtComentario=view.findViewById(R.id.txtPUComentar);

        txtNombre.setText(ArrayPublicacion.get(position).getNombre());
        txtDetalle.setText(ArrayPublicacion.get(position).getDetalle());
        txtLike.setText(ArrayPublicacion.get(position).getLike());
        txtComentario.setText(ArrayPublicacion.get(position).getComentarios());
        return view;
    }
}