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
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.naturecare.entidades.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class Foro extends AppCompatActivity {

    PrimerContenedor primerContenedor = new PrimerContenedor();
    SegundoContenedor segundoContenedor = new SegundoContenedor();
    PublicacionGuardada publicacionGuardada = new PublicacionGuardada();
    Carro_compra carro_compra = new Carro_compra();
    ProductoGuardado productoGuardado = new ProductoGuardado();
    RegistrarProducto registrarProducto = new RegistrarProducto();

    String name, pass;
    int menuOpcion = 1;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        BottomNavigationView navigation = findViewById(R.id.bottom_navegation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationSelectedListener);

        loadFragment(primerContenedor);

        if(menuOpcion == 1){
            setToolbarForo(menuOpcion);
        }if(menuOpcion ==2){
            setToolbarTienda(menuOpcion);
        }

        requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            name = extras.getString("Nombre");
            pass = extras.getString("Pass");
        }

        readPublicacion();
    }


    public void setToolbarForo(int menu){
        Toolbar toolbarForo;
        toolbarForo = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbarForo);
        getSupportActionBar().show();
    }

    public void setToolbarTienda(int menu){
        Toolbar toolbarTienda;
        toolbarTienda = findViewById(R.id.tool_bar_tienda);
        setSupportActionBar(toolbarTienda);
        getSupportActionBar().show();
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

    private void readPublicacion(){
        String URL = "http://192.168.1.9/naturecare/login.php?Nombre="+name+"&Pass="+pass;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String nombre, email, pass, phone;
                        int idUser, tipo;
                        try {
                            idUser = response.getInt("idUsuarioPersonal");
                            nombre = response.getString("Nombre");
                            phone = response.getString("Phone");
                            email = response.getString("Email");
                            pass = response.getString("Pass");
                            tipo = response.getInt("tipo_usuario_id_tipo_usuario");

                            Usuario user = new Usuario(idUser, nombre, phone, email, pass, tipo);
                            System.out.println(user.getId()+", "+user.getNombre()+", "+user.getEmail());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}