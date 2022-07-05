package com.example.naturecare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturecare.entidades.Producto;

import java.util.List;

public class DatosProductos extends RecyclerView.Adapter<DatosProductos.ProdViewHolder>{

    private List<Producto> prodList;
    private Context context;

    public DatosProductos(Context contexto, List<Producto> lista) {
        context = contexto;
        prodList = lista;
    }


    @Override
    public DatosProductos.ProdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_producto,null);

        return new DatosProductos.ProdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatosProductos.ProdViewHolder holder, int position) {
        Producto producto = prodList.get(position);

        holder.txtUser.setText(producto.getUsuario());
        holder.txtNombre.setText(producto.getDetalle());
    }

    @Override
    public int getItemCount() {
        return prodList.size();
    }

    class ProdViewHolder extends RecyclerView.ViewHolder{

        TextView txtUser, txtNombre;
        public ProdViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUser = itemView.findViewById(R.id.txtPRNombre);
            txtNombre = itemView.findViewById(R.id.txtPRProducto);
        }
    }

}
