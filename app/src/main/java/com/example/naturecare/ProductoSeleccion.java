package com.example.naturecare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ProductoSeleccion extends Activity {

    TextView txtProducto, txtProducto2, txtUser, txtCantidad, txtDetalle, txtMonto;
    Button menos, mas, comprar;

    String producto, user, detalle;
    int count;
    double monto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_seleccion);

        inicializar();
        Bundle datos = getIntent().getExtras();

        producto = datos.getString("nombre");
        user = datos.getString("user");
        detalle = datos.getString("detalle");
        count = datos.getInt("cantidad");
        monto = datos.getDouble("monto");

        txtProducto.setText(producto);
        txtProducto2.setText(producto);
        txtUser.setText(user);
        txtDetalle.setText(detalle);
        txtMonto.setText(String.valueOf(monto));

    }

    public void inicializar(){
        txtProducto = findViewById(R.id.txtMPProducto);
        txtProducto2 = findViewById(R.id.txtMPProducto2);
        txtUser = findViewById(R.id.txtMPNombre);
        txtCantidad = findViewById(R.id.txtMPCantidad);
        txtDetalle = findViewById(R.id.txtMPDetalle);
        txtMonto = findViewById(R.id.txtMPMonto);

        menos = findViewById(R.id.MPmenos);
        mas = findViewById(R.id.MPmas);
        comprar = findViewById(R.id.MPcomprar);

        txtCantidad.setText(0);
    }

    public void cantidad(){
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num;
                num = Integer.parseInt(txtCantidad.getText().toString());

                if(num>=0){
                    num=num-1;
                    txtCantidad.setText("");
                }
                txtCantidad.setText(String.valueOf(num));
            }
        });

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num;
                num = Integer.parseInt(txtCantidad.getText().toString());

                if(num>=0 && num<count){
                    num=num+1;
                    txtCantidad.setText("");
                }
                txtCantidad.setText(String.valueOf(num));
            }
        });
    }
}
