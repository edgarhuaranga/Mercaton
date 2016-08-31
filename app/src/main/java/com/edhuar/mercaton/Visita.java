package com.edhuar.mercaton;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

public class Visita extends AppCompatActivity {

    String codigo;
    String rol;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visita);

        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");
        cliente = new Cliente(codigo, getApplicationContext());
    }

    public void goMedicion(View view){
        Intent intent = new Intent(this,MedicionExhibicion.class);
        intent.putExtra("codigo",codigo);
        intent.putExtra("rol",rol);
        startActivity(intent);
    }

    public void goTomaFotos(View view){
        Intent intent = new Intent(this, MedicionFotos.class);
        intent.putExtra("codigo",codigo);
        intent.putExtra("rol",rol);
        startActivity(intent);
    }

    public void goVerificarFotos(View view){
        Intent intent = new Intent(this, VerificarFotos.class);
        intent.putExtra("codigo",codigo);
        intent.putExtra("rol",rol);
        startActivity(intent);
    }

    public void goCompetencia(View view){
        Intent intent = new Intent(this, Competencia.class);
        intent.putExtra("codigo",codigo);
        intent.putExtra("rol",rol);
        startActivity(intent);
    }

    public void terminarVisita(View view) {
        if (!tienevisita()) {
            AlertDialog.Builder message = new AlertDialog.Builder(this);
            message.setTitle("Alerta")
                    .setMessage("No has realizado la medida de exhibición")
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            message.create().show();
        }
        if (!tienecompetencia()) {
            AlertDialog.Builder message = new AlertDialog.Builder(this);
            message.setTitle("Alerta")
                    .setMessage("No has realizado la medida de exhibición de la competencia")
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            message.create().show();
        }
        if (!tienefotos()) {

        }
        if (tienevisita() && tienecompetencia() && tienefotos()) {
            finish();
        }
    }

    public boolean tienevisita(){
        File archivo = new File(cliente.getPath()+"visita.txt");
        Log.d("file", archivo.getAbsolutePath());
        if(archivo.exists()){
            Log.d("file","existe archivo");
            return true;
        }
        else{
            Log.d("file","no existe archivo");
            return false;
        }
    }

    public boolean tienefotos(){
        File dirFotos = new File(cliente.getPathFoto());
        int fotosvalidas = 0;
        ArrayList<Integer> sincom = new ArrayList<>();
        Log.d("fotoCheck",dirFotos.getAbsolutePath());
        for(int i=1; i<=10; i++){
            File foto = new File(cliente.getPathFoto()+"foto"+i+".jpg");
            Log.d("fotoCheck",foto.getAbsolutePath());
            if(foto.exists()){
                File comentario = new File(cliente.getPathFoto()+"comentarioFoto"+i+".txt");
                Log.d("fotoCheck",comentario.getAbsolutePath());
                if(comentario.exists()){
                    Log.d("fotoCheck", "foto valida");
                    fotosvalidas++;
                }
                else{
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
                    mensaje.setTitle("Mensaje")
                            .setMessage("Tu foto numero "+i+" no tiene comentarios!")
                            .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    mensaje.create().show();
                    return false;
                }
            }
        }
        if(fotosvalidas<10){
            AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
            mensaje.setTitle("Mensaje")
                    .setMessage("Debes tomar al menos 10 fotos y poner sus comentarios respectivos, solo tienes "+fotosvalidas+" fotos validas")
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            mensaje.create().show();
            return false;
        }

        return true;
    }

    public boolean tienecompetencia(){
        File archivo = new File(cliente.getPath()+"visitaCompetencia.txt");
        Log.d("file", archivo.getAbsolutePath());
        if(archivo.exists()){
            Log.d("file","existe archivo");
            return true;
        }
        else{
            Log.d("file","no existe archivo");
            return false;
        }
    }
}
