package com.edhuar.mercaton;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class VerificarFotos extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1000;
    ImageView[] fotosTomadas;
    TextView[] comentarios;
    Button[] tomarOtra;
    Button[] modificarComentario;
    Cliente cliente;
    String codigo;
    String rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_fotos);

        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");
        cliente = new Cliente(codigo, getApplicationContext());
        fotosTomadas = new ImageView[10];
        comentarios = new TextView[10];
        tomarOtra = new Button[10];
        modificarComentario = new Button[10];
        setupViews();
        loadPhotos();
        loadComents();
    }

    public void setupViews(){
        fotosTomadas[0] = (ImageView) findViewById(R.id.imageview_foto1);
        fotosTomadas[1] = (ImageView) findViewById(R.id.imageview_foto2);
        fotosTomadas[2] = (ImageView) findViewById(R.id.imageview_foto3);
        fotosTomadas[3] = (ImageView) findViewById(R.id.imageview_foto4);
        fotosTomadas[4] = (ImageView) findViewById(R.id.imageview_foto5);
        fotosTomadas[5] = (ImageView) findViewById(R.id.imageview_foto6);
        fotosTomadas[6] = (ImageView) findViewById(R.id.imageview_foto7);
        fotosTomadas[7] = (ImageView) findViewById(R.id.imageview_foto8);
        fotosTomadas[8] = (ImageView) findViewById(R.id.imageview_foto9);
        fotosTomadas[9] = (ImageView) findViewById(R.id.imageview_foto10);

        comentarios[0] = (TextView) findViewById(R.id.textview_comentario1);
        comentarios[1] = (TextView) findViewById(R.id.textview_comentario2);
        comentarios[2] = (TextView) findViewById(R.id.textview_comentario3);
        comentarios[3] = (TextView) findViewById(R.id.textview_comentario4);
        comentarios[4] = (TextView) findViewById(R.id.textview_comentario5);
        comentarios[5] = (TextView) findViewById(R.id.textview_comentario6);
        comentarios[6] = (TextView) findViewById(R.id.textview_comentario7);
        comentarios[7] = (TextView) findViewById(R.id.textview_comentario8);
        comentarios[8] = (TextView) findViewById(R.id.textview_comentario9);
        comentarios[9] = (TextView) findViewById(R.id.textview_comentario10);

        tomarOtra[0] = (Button) findViewById(R.id.button_tomaotra1);
        tomarOtra[1] = (Button) findViewById(R.id.button_tomaotra2);
        tomarOtra[2] = (Button) findViewById(R.id.button_tomaotra3);
        tomarOtra[3] = (Button) findViewById(R.id.button_tomaotra4);
        tomarOtra[4] = (Button) findViewById(R.id.button_tomaotra5);
        tomarOtra[5] = (Button) findViewById(R.id.button_tomaotra6);
        tomarOtra[6] = (Button) findViewById(R.id.button_tomaotra7);
        tomarOtra[7] = (Button) findViewById(R.id.button_tomaotra8);
        tomarOtra[8] = (Button) findViewById(R.id.button_tomaotra9);
        tomarOtra[9] = (Button) findViewById(R.id.button_tomaotra10);

        modificarComentario[0] = (Button) findViewById(R.id.button_modificar1);
        modificarComentario[1] = (Button) findViewById(R.id.button_modificar2);
        modificarComentario[2] = (Button) findViewById(R.id.button_modificar3);
        modificarComentario[3] = (Button) findViewById(R.id.button_modificar4);
        modificarComentario[4] = (Button) findViewById(R.id.button_modificar5);
        modificarComentario[5] = (Button) findViewById(R.id.button_modificar6);
        modificarComentario[6] = (Button) findViewById(R.id.button_modificar7);
        modificarComentario[7] = (Button) findViewById(R.id.button_modificar8);
        modificarComentario[8] = (Button) findViewById(R.id.button_modificar9);
        modificarComentario[9] = (Button) findViewById(R.id.button_modificar10);
    }

    public void loadPhotos(){
        for(int i=1; i<=10; i++){
            Log.d("buscafoto",cliente.getPathFoto()+"foto"+i);
            if((new File(cliente.getPathFoto()+"foto"+i+".jpg")).exists()){
                Bitmap bitmap = MedicionFotos.getSampleBitmapFromFile(cliente.getPathFoto()+"foto"+i+".jpg", 175, 175);
                fotosTomadas[i-1].setImageBitmap(bitmap);
            }
            else{
                fotosTomadas[i-1].setImageResource(R.drawable.ic_add_a_photo_black_24dp);
            }
        }
    }

    public void loadComents(){
        for(int i=1; i<=10; i++){
            String comentario = cliente.getComentarioFoto(i);
            comentarios[i-1].setText(comentario);
        }
    }

    public void tomaOtra(View view){
        int indexb = getIndexButton(view.getId());
        indexb++;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try{
                photoFile = createImageFile(createImageName(indexb),indexb);
            } catch (Exception e){
                e.printStackTrace();
            }

            if(photoFile != null){
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE+indexb);
                MediaScannerConnection.scanFile(this,new String[]{photoFile.toString()},null,null);
            }
        }
    }

    public void modificarComentario(View view){
        final int indexButton = getIndexButtonComent(view.getId());

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);

        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setTitle("Modifica comentario");
        builder.setMessage("Ingresa el comentario de la foto aqui");
        builder.setView(editText);
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("nuevocomentario", editText.getText().toString() +(indexButton+1));
                String comentario = editText.getText().toString()+"\n";
                try{
                    File storageDir = new File(cliente.getPathFoto());
                    storageDir.mkdirs();
                    File file = new File(storageDir,"comentarioFoto"+(indexButton+1)+".txt");
                    FileOutputStream fos = new FileOutputStream(file, true);
                    fos.write(comentario.getBytes());
                    loadComents();
                    fos.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    public int getIndexButtonComent(int id){
        for(int i=0; i<10; i++){
            if(modificarComentario[i].getId() == id) return i;
        }
        return -1;
    }

    public File createImageFile(String nombreFoto, int index) throws IOException {
        File storageDir = new File(cliente.getPathFoto()+index+"/");
        storageDir.mkdirs();
        File image = new File(storageDir, nombreFoto);
        return image;
    }

    public File createImageFile(String nombreFoto) throws IOException {
        File storageDir = new File(cliente.getPathFoto());
        storageDir.mkdirs();
        File image = new File(storageDir, nombreFoto);
        return image;
    }

    public String createImageName(int indexfoto){
        String res="";
        //Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if(hour<10) res = res+"0"+hour;
        else res += hour;
        res+=":";
        int minute = c.get(Calendar.MINUTE);
        if(minute<10) res = res+"0"+minute;
        else res += minute;
        res+=":";
        int second = c.get(Calendar.SECOND);
        if(second<10) res = res+"0"+second;
        else res += second;
        res+="_";


        res += rol+indexfoto+codigo;
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

    public int getIndexButton(int id){
        for(int i=0; i<10; i++){
            if(tomarOtra[i].getId()==id){
                return i;
            }
        }
        return -1;
    }

    public void verFotoFullSize(View view){
        int index=0;
        switch (view.getId()){
            case R.id.imageview_foto1:
                index=1;
                break;
            case R.id.imageview_foto2:
                index=2;
                break;
            case R.id.imageview_foto3:
                index=3;
                break;
            case R.id.imageview_foto4:
                index=4;
                break;
            case R.id.imageview_foto5:
                index=5;
                break;
            case R.id.imageview_foto6:
                index=6;
                break;
            case R.id.imageview_foto7:
                index=7;
                break;
            case R.id.imageview_foto8:
                index=8;
                break;
            case R.id.imageview_foto9:
                index=9;
                break;
            case R.id.imageview_foto10:
                index=10;
                break;
        }
        Intent intent = new Intent(this, FullSizeFoto.class);
        intent.putExtra("index",index);
        intent.putExtra("codigo",codigo);
        intent.putExtra("rol",rol);
        startActivity(intent);
    }

    public File getLastPhotoTaken(int index){
        File file = new File(cliente.getPathFoto());
        File[] carpetasInternas = file.listFiles();
        File last = null;
        for(int i=0;i<carpetasInternas.length;i++){
            if(carpetasInternas[i].isDirectory() && carpetasInternas[i].getName().equalsIgnoreCase(""+index)){
                File[] fotos = carpetasInternas[i].listFiles();
                int minim = 0;
                for(int j=0; j<fotos.length; j++){
                    Log.d("hora", fotos[j].getAbsolutePath());
                    int hour = Integer.parseInt(fotos[j].getName().substring(0,2));
                    int min = Integer.parseInt(fotos[j].getName().substring(3,5));
                    int sec = Integer.parseInt(fotos[j].getName().substring(6,8));
                    if(hour*60*60+min*60+sec > minim){
                        last = fotos[j];
                        minim = hour*60*60+min*60+sec;
                    }
                    Log.d("hora", sec+"_"+min+"_"+hour);
                }
            }
        }
        Log.d("last",last.getName());
        return last;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode >= CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && requestCode<=CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE+10) {
            if (resultCode == RESULT_OK) {
                int indx = requestCode-CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE;
                try{
                    File ultima = getLastPhotoTaken(indx);
                    File copia = new File(cliente.getPathFoto()+"foto"+indx+".jpg");
                    MedicionFotos.copiar(ultima,copia);
                    Bitmap photo = MedicionFotos.getSampleBitmapFromFile(ultima.getPath(),175,175);

                    fotosTomadas[indx-1].setImageBitmap(photo);
                } catch (Exception e){
                    e.printStackTrace();
                }

                //imageView.setImageBitmap(photo);
                Log.d("foto","foto"+(requestCode-CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE));


            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }

}
