package com.edhuar.mercaton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes extends AppCompatActivity {

    String rol;
    String usuario;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        getSupportActionBar().hide();

        usuario = getIntent().getStringExtra(Login.USUARIO_LABEL);
        rol = getIntent().getStringExtra(Login.ROL_LABEL);

        db=new DatabaseHandler(ListaClientes.this, null, null, 2);

        RecyclerView recyclerViewVisitados = (RecyclerView) findViewById(R.id.recycler_lista_visitados);
        recyclerViewVisitados.setLayoutManager(new LinearLayoutManager(this));
        RecyclerClientesAdapter adapter = new RecyclerClientesAdapter(db.getDataFromDB(rol, usuario),rol, getApplicationContext());
        recyclerViewVisitados.setAdapter(adapter);
    }

}
