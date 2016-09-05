package com.edhuar.mercaton;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes extends AppCompatActivity implements SearchView.OnQueryTextListener {

    String rol;
    String usuario;
    DatabaseHandler db;
    RecyclerClientesAdapter adapter;
    List<Cliente> dataFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        getSupportActionBar().setTitle("Mercaton");
        getSupportActionBar().setSubtitle("Lista de clientes");

        usuario = getIntent().getStringExtra(Login.USUARIO_LABEL);
        rol = getIntent().getStringExtra(Login.ROL_LABEL);

        db=new DatabaseHandler(ListaClientes.this, null, null, 2);

        RecyclerView recyclerViewVisitados = (RecyclerView) findViewById(R.id.recycler_lista_visitados);
        recyclerViewVisitados.setLayoutManager(new LinearLayoutManager(this));
        dataFromDB = db.getDataFromDB(rol, usuario);
        adapter = new RecyclerClientesAdapter(dataFromDB,rol, getApplicationContext());
        recyclerViewVisitados.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        adapter.setFilter(dataFromDB);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Cliente> filteredModelList = filter(dataFromDB, newText);
        adapter.setFilter(filteredModelList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<Cliente> filter(List<Cliente> models, String query) {
        query = query.toLowerCase();

        final List<Cliente> filteredModelList = new ArrayList<>();
        for (Cliente model : models) {
            final String text = model.nombre.toLowerCase();
            final String mercado = model.mercado.toLowerCase();
            final String direccion = model.direccion.toLowerCase();
            final String giro = model.giro.toLowerCase();
            final String codigo = model.codigo.toLowerCase();

            if (text.contains(query) || mercado.contains(query) || direccion.contains(query) || giro.contains(query) || codigo.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


}
