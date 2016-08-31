package com.edhuar.mercaton;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MenuCliente extends AppCompatActivity {

    String codigo;
    String rol;
    Cliente clienteC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        getSupportActionBar().hide();
        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");
        clienteC = new Cliente(codigo, getApplicationContext());

        File dir = new File(clienteC.getPath());
        if(!dir.exists()){
            dir.mkdirs();
        }
        File dirfotos = new File(clienteC.getPath());
        if(!dirfotos.exists()){
            dirfotos.mkdirs();
        }
        TextView cliente = (TextView)findViewById(R.id.textview_cliente);
        cliente.setText((new Cliente(codigo,getApplicationContext())).nombre);

        if(!Utils.fechaValida(this)){
            AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
            mensaje.setTitle("Atención")
                    .setMessage("Tu hora no está configurada correctamente y debes cambiarla, podría no dejarte tomar fotos")
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            mensaje.create().show();
        }
    }

    public void clickOpcion(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.button_perfil:
                intent = new Intent(this, Perfil.class);
                break;
            case R.id.button_historico_exhibicion:
                intent = new Intent(this, HistoricoExhibicion.class);
                break;
            case R.id.button_historico_compras:
                intent = new Intent(this, HistoricoCompras.class);
                break;
            case R.id.button_medicion_exhibicion_fotografia:
                intent = new Intent(this, Visita.class);
                break;
            case R.id.button_comentarios:
                intent = new Intent(this, Comentarios.class);
                break;
            case R.id.button_galeria:
                intent = new Intent(this, Galeria.class);
                break;
            case R.id.button_finalizar:
                validarVisita();

                break;
        }
        if(intent !=null){
            intent.putExtra("codigo",codigo);
            intent.putExtra("rol",rol);
            startActivity(intent);
        }
    }

    public void validarVisita(){
        if(!tienevisita() || !tienecompetencia() || !tienefotos()){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atención")
                    .setMessage("No has terminado de realizar todas las mediciones, si sales se eliminará toda información tomada")
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clienteC.getPath();
                            File file = new File(clienteC.getPath());
                            if(file.exists()){
                                deleteDirectory(file);
                            }
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alerta.create().show();
        }
        else{
            finish();
        }
    }

    public static boolean deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }

    public boolean tienevisita(){
        File archivo = new File(clienteC.getPath()+"visita.txt");
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
        File dirFotos = new File(clienteC.getPathFoto());
        int fotosvalidas = 0;
        ArrayList<Integer> sincom = new ArrayList<>();
        Log.d("fotoCheck",dirFotos.getAbsolutePath());
        for(int i=1; i<=10; i++){
            File foto = new File(clienteC.getPathFoto()+"foto"+i+".jpg");
            Log.d("fotoCheck",foto.getAbsolutePath());
            if(foto.exists()){
                File comentario = new File(clienteC.getPathFoto()+"comentarioFoto"+i+".txt");
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
                    .setMessage("Debes tomar 10 fotos y poner sus comentarios respectivos, solo tienes "+fotosvalidas+" fotos validas")
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
        File archivo = new File(clienteC.getPath()+"visitaCompetencia.txt");
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
