package com.example.naturecare;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturecare.entidades.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class DatosPublicaciones extends RecyclerView.Adapter<DatosPublicaciones.ViewHolder> {

    private List<Publicacion> localDataSet;
    private Publicacion publicacion;
    Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtNombre;
        private final TextView txtPublicacion;
        private final TextView txtLike;
        private final TextView txtcomentario;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            txtNombre = (TextView) view.findViewById(R.id.txtPUNombre);
            txtPublicacion = (TextView) view.findViewById(R.id.txtPUPublicacion);
            txtLike = (TextView) view.findViewById(R.id.txtPULike);
            txtcomentario = (TextView) view.findViewById(R.id.txtPUComentar);

        }

        public TextView getTxtNombre() {
            return txtNombre;
        }
        public TextView getTxtPublicacion() {
            return txtPublicacion;
        }
        public TextView getTxtLike() {
            return txtLike;
        }
        public TextView getTxtcomentario() {
            return txtcomentario;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public DatosPublicaciones(Context contexto, List<Publicacion> dataSet) {
        context = contexto;
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_publicacion, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTxtNombre().setText((CharSequence) localDataSet.set(position, publicacion));
        viewHolder.getTxtPublicacion().setText((CharSequence) localDataSet.set(position, publicacion));
        viewHolder.getTxtLike().setText((CharSequence) localDataSet.set(position, publicacion));
        viewHolder.getTxtcomentario().setText((CharSequence) localDataSet.set(position, publicacion));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}