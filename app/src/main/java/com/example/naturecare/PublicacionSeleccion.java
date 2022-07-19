package com.example.naturecare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.naturecare.entidades.Publicacion;

public class PublicacionSeleccion extends Activity {

    TextView txtNombre, txtPublicacion, txtLike, txtComentar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicacion_seleccion);

        txtNombre = findViewById(R.id.txtPSENombre);
        txtPublicacion = findViewById(R.id.txtPSEPublicacion);
        txtLike = findViewById(R.id.txtPSELike);
        txtComentar = findViewById(R.id.txtPSEComentar);

        Bundle datos = getIntent().getExtras();

        String nombre = datos.getString("nombre");
        String detalle = datos.getString("detalle");
        String like = datos.getString("like");
        String comentario = datos.getString("comentario");

        txtNombre.setText(nombre);
        txtPublicacion.setText(detalle);
        txtLike.setText(like);
        txtComentar.setText(comentario);



    }

}
