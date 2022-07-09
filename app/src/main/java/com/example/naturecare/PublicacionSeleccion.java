package com.example.naturecare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PublicacionSeleccion extends Activity {

    TextView txtNombre, txtPublicacion, txtLike, txtComentar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicacion_seleccion);

        PrimerContenedor datos = (PrimerContenedor) getIntent().getSerializableExtra("DatosPublicacion");
        txtNombre = findViewById(R.id.txtPSENombre);
        txtPublicacion = findViewById(R.id.txtPSEPublicacion);
        txtLike = findViewById(R.id.txtPSELike);
        txtComentar = findViewById(R.id.txtPSEComentar);

        txtNombre.setText(datos.txtNombre.getText());
        txtPublicacion.setText(datos.txtPublicacion.getText());
        txtLike.setText(datos.txtLike.getText());
        txtComentar.setText(datos.txtComentar.getText());



    }

}
