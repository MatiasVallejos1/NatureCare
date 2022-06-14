package com.example.naturecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Foro extends AppCompatActivity {

    PrimerContenedor primerContenedor = new PrimerContenedor();
    SegundoContenedor segundoContenedor = new SegundoContenedor();
    PublicacionGuardada publicacionGuardada = new PublicacionGuardada();
    Carro_compra carro_compra = new Carro_compra();
    ProductoGuardado productoGuardado = new ProductoGuardado();
    RegistrarProducto registrarProducto = new RegistrarProducto();

    int menuOpcion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        BottomNavigationView navigation = findViewById(R.id.bottom_navegation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationSelectedListener);

        loadFragment(primerContenedor);
        setToolbarForo(menuOpcion);

    }


    public void setToolbarForo(int menu){
        Toolbar toolbar;
        if(menu == 1){
            toolbar = findViewById(R.id.tool_bar);
            setSupportActionBar(toolbar);
            getSupportActionBar().show();

        }else if(menu == 2){
            toolbar = findViewById(R.id.tool_bar_tienda);
            setSupportActionBar(toolbar);
            getSupportActionBar().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(menuOpcion == 1){
            getMenuInflater().inflate(R.menu.tool_bar, menu);
            return true;
        }else if(menuOpcion == 2){
            getMenuInflater().inflate(R.menu.tool_bar_tienda, menu);
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(menuOpcion == 1){
            switch(item.getItemId()){

                case R.id.publicacionGuardada:
                    loadFragment(publicacionGuardada);
                    break;

                case R.id.FcerrarSesion:
                    Intent cerrar = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(cerrar);
                    break;
            }
        }else if(menuOpcion == 2){
            switch(item.getItemId()){

                case R.id.carroCompras:
                    loadFragment(carro_compra);
                    break;
                case R.id.productoGuardado:
                    loadFragment(productoGuardado);
                    break;
                case R.id.agregarProducto:
                    loadFragment(registrarProducto);
                    break;
                case R.id.TcerrarSesion:
                    Intent cerrar = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(cerrar);
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.primerContenedor:
                    loadFragment(primerContenedor);
                    menuOpcion=1;
                    return true;
                case R.id.segundoContenedor:
                    loadFragment(segundoContenedor);
                    menuOpcion=2;
                    return true;
            }
            return false;
        }
    };

    public void loadFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, fragment);
        transaction.commit();
    }
}