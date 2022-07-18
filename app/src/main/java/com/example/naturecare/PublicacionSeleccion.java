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

        Publicacion datos = (Publicacion) getIntent().getSerializableExtra("Publicacion");
        txtNombre = findViewById(R.id.txtPSENombre);
        txtPublicacion = findViewById(R.id.txtPSEPublicacion);
        txtLike = findViewById(R.id.txtPSELike);
        txtComentar = findViewById(R.id.txtPSEComentar);

        txtNombre.setText(datos.getNombre());
        txtPublicacion.setText(datos.getDetalle());
        txtLike.setText(datos.getLike());
        txtComentar.setText(datos.getComentarios());



    }

}
