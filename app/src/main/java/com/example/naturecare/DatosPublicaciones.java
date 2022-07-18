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
    final DatosPublicaciones.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public DatosPublicaciones(Context context, List<Publicacion> lista, DatosPublicaciones.OnItemClickListener listener) {
        //inflater = LayoutInflater.from(context);
        this.context = context;
        publiList = lista;
        this.listener = listener;
    }


    @Override
    public PubliViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_publicacion,null);

        return new PubliViewHolder(view,listener);
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



    class PubliViewHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtDetalle, txtLike, txtComentario;
        public PubliViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtPUNombre);
            txtDetalle = itemView.findViewById(R.id.txtPUPublicacion);
            txtLike = itemView.findViewById(R.id.txtPULike);
            txtComentario = itemView.findViewById(R.id.txtPUComentar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(pos);
                    }
                }
            });
        }
    }
}