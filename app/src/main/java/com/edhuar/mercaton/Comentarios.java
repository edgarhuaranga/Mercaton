package com.edhuar.mercaton;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class Comentarios extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1272;
    String codigo;
    String rol;
    EditText comentariosDistribuidor;
    EditText comentariosCompetencia;
    EditText actualizarDatos;
    EditText comentariosLibre;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");
        cliente = new Cliente(codigo, getApplicationContext());

        comentariosDistribuidor = (EditText) findViewById(R.id.edittext_comentarios_distribuidor);
        comentariosCompetencia = (EditText) findViewById(R.id.edittext_comentarios_competencia);
        actualizarDatos = (EditText) findViewById(R.id.edittext_actualizar_datos);
        comentariosLibre = (EditText) findViewById(R.id.edittext_comentario_libre);

    }

    public void finalizarComentarios(View view){
        String comentarioDistribuidor = comentariosDistribuidor.getText().toString();
        String comentarioCompe = comentariosCompetencia.getText().toString();
        String actualizaDatos = actualizarDatos.getText().toString();
        String comentarioLibre = comentariosLibre.getText().toString();
        if(comentarioDistribuidor.length()!=0){
            comentarioDistribuidor+="\n";
            try{
                File storageDir = new File(cliente.getPath());
                storageDir.mkdirs();
                File file = new File(storageDir,"comentariosDistribuidor.txt");
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write(comentarioDistribuidor.getBytes());
                fos.close();
                MediaScannerConnection.scanFile(this,new String[]{file.toString()},null,null);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        if(comentarioCompe.length()!=0){
            comentarioCompe+="\n";
            try{
                File storageDir = new File(cliente.getPath());
                storageDir.mkdirs();
                File file = new File(storageDir,"comentariosCompetencia.txt");
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write(comentarioCompe.getBytes());
                fos.close();
                MediaScannerConnection.scanFile(this,new String[]{file.toString()},null,null);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        if(actualizaDatos.length()!=0){
            actualizaDatos+="\n";
            try{
                File storageDir = new File(cliente.getPath());
                storageDir.mkdirs();
                File file = new File(storageDir,"actualizarDatos.txt");
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write(actualizaDatos.getBytes());
                fos.close();
                MediaScannerConnection.scanFile(this,new String[]{file.toString()},null,null);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        if(comentarioLibre.length()!=0){
            comentarioLibre+="\n";
            try{
                File storageDir = new File(cliente.getPath());
                storageDir.mkdirs();
                File file = new File(storageDir,"comentariosLibre.txt");
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write(comentarioLibre.getBytes());
                fos.close();
                MediaScannerConnection.scanFile(this,new String[]{file.toString()},null,null);
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        finish();
    }

    public File createImageFile(String nombreFoto) throws IOException {
        File storageDir = new File(cliente.getPathCompetencia());
        storageDir.mkdirs();
        File image = new File(storageDir, nombreFoto);
        return image;
    }

    public String createImageName(String xxx){
        String res="";
        //Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if(hour<10) res = res+"0"+hour;
        else res += hour;
        res+="_";
        int minute = c.get(Calendar.MINUTE);
        if(minute<10) res = res+"0"+minute;
        else res += minute;
        res+="_";
        int second = c.get(Calendar.SECOND);
        if(second<10) res = res+"0"+second;
        else res += second;
        res+="_";


        res += rol+xxx+codigo;
        /*try{
            File carpetaFoto = new File(clienteVisitado.getPath()+"Semana"+semana+"/"+dia+"/"+clienteVisitado.codigo+"/Fotos/");
            res += clienteVisitado.codigo + (carpetaFoto.listFiles().length+1);
        } catch (Exception e){
            res += clienteVisitado.codigo+"1";
            e.printStackTrace();
            Log.i("Foto",clienteVisitado.getPath());
        }*/
        res += ".jpg";
        return res;
    }

    public void fotoCompetencia(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try{
                photoFile = createImageFile(createImageName("competencia"));
            } catch (Exception e){
                e.printStackTrace();
            }

            if(photoFile != null){
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                MediaScannerConnection.scanFile(this,new String[]{photoFile.toString()},null,null);
            }
        }
    }
}
