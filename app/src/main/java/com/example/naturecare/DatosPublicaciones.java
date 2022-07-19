package com.example.naturecare;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturecare.entidades.Publicacion;

import java.util.List;

public class DatosPublicaciones extends RecyclerView.Adapter<DatosPublicaciones.PubliViewHolder>{

    private List<Publicacion> publiList;
    private LayoutInflater inflater;
    private Context context;
    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View v,int position);
    }

    public DatosPublicaciones(Context context,List<Publicacion> lista) {
        //inflater = LayoutInflater.from(context);
        publiList = lista;
        this.context = context;
    }


    @Override
    public PubliViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_publicacion,null);

        return new PubliViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PubliViewHolder holder, int position) {
        Publicacion publicacion = publiList.get(position);

        holder.txtNombre.setText(publicacion.getNombre());
        holder.txtDetalle.setText(publicacion.getDetalle());
        holder.txtLike.setText(String.valueOf(publicacion.getLike()));
        holder.txtComentario.setText(String.valueOf(publicacion.getComentarios()));
    }

    @Override
    public int getItemCount() {
        return publiList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClick){
        this.listener = onItemClick;
    }

    public static class PubliViewHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtDetalle, txtLike, txtComentario;
        public PubliViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtPUNombre);
            txtDetalle = itemView.findViewById(R.id.txtPUPublicacion);
            txtLike = itemView.findViewById(R.id.txtPULike);
            txtComentario = itemView.findViewById(R.id.txtPUComentar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v,getAdapterPosition());
                }
            });
        }
    }
}