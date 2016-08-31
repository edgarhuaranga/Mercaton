package com.edhuar.mercaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Galeria extends AppCompatActivity {

    String codigo;
    String rol;
    Cliente cliente;
    Button[] buttonsGaleriaSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");
        cliente = new Cliente(codigo, getApplicationContext());
        buttonsGaleriaSemana = new Button[8];
        setupviews();
    }

    private void setupviews(){
        buttonsGaleriaSemana[0] = (Button) findViewById(R.id.button_galeria_semana01);
        buttonsGaleriaSemana[1] = (Button) findViewById(R.id.button_galeria_semana02);
        buttonsGaleriaSemana[2] = (Button) findViewById(R.id.button_galeria_semana03);
        buttonsGaleriaSemana[3] = (Button) findViewById(R.id.button_galeria_semana04);
        buttonsGaleriaSemana[4] = (Button) findViewById(R.id.button_galeria_semana05);
        buttonsGaleriaSemana[5] = (Button) findViewById(R.id.button_galeria_semana06);
        buttonsGaleriaSemana[6] = (Button) findViewById(R.id.button_galeria_semana07);
        buttonsGaleriaSemana[7] = (Button) findViewById(R.id.button_galeria_semana08);
    }

    public int getIndexButton(int id){
        for(int i=0; i<buttonsGaleriaSemana.length; i++){
            if(buttonsGaleriaSemana[i].getId() == id) return i;
        }
        return -1;
    }

    public void goGaleriaSemanal(View view){
        int index= getIndexButton(view.getId());
        index++;
        ArrayList<String> rutas = cliente.getPathPhotos(index);
        Intent intent = new Intent(this, GaleriaSemanal.class);
        intent.putExtra("codigo",codigo);
        intent.putExtra("rol",rol);
        intent.putExtra("index",index);
        Log.d("rutas", rutas.toString());
        startActivity(intent);
    }

    public void regresarMenu(View view){
        finish();
    }
}
