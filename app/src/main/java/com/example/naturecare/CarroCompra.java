package com.example.naturecare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CarroCompra extends Activity {

    TextView txtUser, txtNombre, txtCantidad;
    int id, count;
    double precio;
    String producto, user;
    Button comprar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carro_compra);

        txtUser = findViewById(R.id.CCUsuario);
        txtNombre = findViewById(R.id.CCNombre);
        txtCantidad = findViewById(R.id.CCCantidad);

        comprar = findViewById(R.id.CCComprar);

        Bundle datos = getIntent().getExtras();

        id = datos.getInt("id");
        count = datos.getInt("cantidad");
        producto = datos.getString("nombre");
        user = datos.getString("user");
        precio = datos.getDouble("precio");

        txtUser.setText(user);
        txtNombre.setText(producto);
        txtCantidad.setText(String.valueOf(count));

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarroCompra.this,Compra.class);
                intent.putExtra("id",id);
                intent.putExtra("cantidad",count);
                intent.putExtra("nombre",producto);
                intent.putExtra("user",user);
                intent.putExtra("precio",precio);
                startActivity(intent);
            }
        });

    }
}
