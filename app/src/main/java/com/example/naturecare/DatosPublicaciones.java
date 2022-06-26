package com.example.naturecare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturecare.entidades.Publicacion;

import java.util.ArrayList;

public class DatosPublicaciones extends RecyclerView.Adapter<DatosPublicaciones.ViewPublicacion>{

    ArrayList<Publicacion> lista;

    public DatosPublicaciones(ArrayList<Publicacion> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewPublicacion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_publicacion,null,false);
        return new ViewPublicacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPublicacion holder, int position) {
        holder.nombre.setText(lista.get(position).getIdPublicacion());
        holder.publicacion.setText(lista.get(position).getDetalle());
        holder.like.setText(lista.get(position).getLike());
        holder.comentar.setText(lista.get(position).getComentarios());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewPublicacion extends RecyclerView.ViewHolder{
        TextView nombre, publicacion, like, comentar, guardar;
        public ViewPublicacion(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            publicacion = (TextView) itemView.findViewById(R.id.txtPublicacion);
            like = (TextView) itemView.findViewById(R.id.txtLike);
            comentar = (TextView) itemView.findViewById(R.id.txtComentar);
        }
    }
}