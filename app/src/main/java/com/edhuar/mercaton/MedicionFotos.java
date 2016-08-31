package com.edhuar.mercaton;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MedicionFotos extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100 ;
    ImageView[] tomaFotosButtons;
    EditText[] obtenerComentarios;
    Button[] buttonGuardarComentario;
    String codigo;
    String rol;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion_fotos);

        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");
        cliente = new Cliente(codigo, getApplicationContext());
        tomaFotosButtons = new ImageView[10];
        obtenerComentarios = new EditText[10];
        buttonGuardarComentario= new Button[10];
        setupViews();
        fillPhotos();
    }

    public void setupViews(){
        tomaFotosButtons[0] = (ImageView) findViewById(R.id.button_tomafoto1);
        tomaFotosButtons[1] = (ImageView) findViewById(R.id.button_tomafoto2);
        tomaFotosButtons[2] = (ImageView) findViewById(R.id.button_tomafoto3);
        tomaFotosButtons[3] = (ImageView) findViewById(R.id.button_tomafoto4);
        tomaFotosButtons[4] = (ImageView) findViewById(R.id.button_tomafoto5);
        tomaFotosButtons[5] = (ImageView) findViewById(R.id.button_tomafoto6);
        tomaFotosButtons[6] = (ImageView) findViewById(R.id.button_tomafoto7);
        tomaFotosButtons[7] = (ImageView) findViewById(R.id.button_tomafoto8);
        tomaFotosButtons[8] = (ImageView) findViewById(R.id.button_tomafoto9);
        tomaFotosButtons[9] = (ImageView) findViewById(R.id.button_tomafoto10);

        obtenerComentarios[0] = (EditText) findViewById(R.id.editview_comentario1);
        obtenerComentarios[1] = (EditText) findViewById(R.id.editview_comentario2);
        obtenerComentarios[2] = (EditText) findViewById(R.id.editview_comentario3);
        obtenerComentarios[3] = (EditText) findViewById(R.id.editview_comentario4);
        obtenerComentarios[4] = (EditText) findViewById(R.id.editview_comentario5);
        obtenerComentarios[5] = (EditText) findViewById(R.id.editview_comentario6);
        obtenerComentarios[6] = (EditText) findViewById(R.id.editview_comentario7);
        obtenerComentarios[7] = (EditText) findViewById(R.id.editview_comentario8);
        obtenerComentarios[8] = (EditText) findViewById(R.id.editview_comentario9);
        obtenerComentarios[9] = (EditText) findViewById(R.id.editview_comentario10);

        buttonGuardarComentario[0] = (Button) findViewById(R.id.button_grabarcomentario1);
        buttonGuardarComentario[1] = (Button) findViewById(R.id.button_grabarcomentario2);
        buttonGuardarComentario[2] = (Button) findViewById(R.id.button_grabarcomentario3);
        buttonGuardarComentario[3] = (Button) findViewById(R.id.button_grabarcomentario4);
        buttonGuardarComentario[4] = (Button) findViewById(R.id.button_grabarcomentario5);
        buttonGuardarComentario[5] = (Button) findViewById(R.id.button_grabarcomentario6);
        buttonGuardarComentario[6] = (Button) findViewById(R.id.button_grabarcomentario7);
        buttonGuardarComentario[7] = (Button) findViewById(R.id.button_grabarcomentario8);
        buttonGuardarComentario[8] = (Button) findViewById(R.id.button_grabarcomentario9);
        buttonGuardarComentario[9] = (Button) findViewById(R.id.button_grabarcomentario10);
    }

    public void fillPhotos(){
        for(int i=1; i<=10; i++){
            File foto = new File(cliente.getPathFoto()+"foto"+i+".jpg");
            if(foto.exists()){
                //Bitmap photo = getSampleBitmapFromFile(foto.getAbsolutePath(),150, 150);
                Glide.clear(tomaFotosButtons[i-1]);
                Glide.with(this).load(foto.getAbsolutePath()).centerCrop().fitCenter().into(tomaFotosButtons[i-1]);
            }
        }
    }

    public int getIndexFoto(int id){
        for(int i=0;i<10; i++){
            if(tomaFotosButtons[i].getId()==id) return i;
        }
        return -1;
    }

    private int getIndexComentario(int id) {
        for(int i=0; i<10; i++){
            if(buttonGuardarComentario[i].getId()==id){
                return i;
            }
        }
        return -1;
    }

    public void guardarComentario(View view){
        int indexComentario = getIndexComentario(view.getId());
        Log.d("indexc",indexComentario+"/");
        if(obtenerComentarios[indexComentario].getText().length()==0){
            AlertDialog.Builder message = new AlertDialog.Builder(this);
            message.setTitle("Atento")
                    .setMessage("No hay comentario a guardar")
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            message.create().show();
        }
        else{
            indexComentario++;
            try{
                File storageDir = new File(cliente.getPathFoto());
                storageDir.mkdirs();
                File file = new File(storageDir,"comentarioFoto"+indexComentario+".txt");
                FileOutputStream fos = new FileOutputStream(file, true);
                String comentario= obtenerComentarios[indexComentario-1].getText().toString()+"\n";
                fos.write(comentario.getBytes());
                fos.close();
                AlertDialog.Builder confirmacion = new AlertDialog.Builder(this);
                confirmacion.setTitle("Mensaje")
                        .setMessage("Tu comentario ha sido guardado")
                        .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
                obtenerComentarios[indexComentario-1].setText("");
                MediaScannerConnection.scanFile(this,new String[]{file.toString()},null,null);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void tomaFoto(View view){

        if(Utils.fechaValida(this)){
            int indexFoto = getIndexFoto(view.getId());
            indexFoto++;
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(getPackageManager()) != null){
                File photoFile = null;
                try{
                    photoFile = createImageFile(createImageName(indexFoto),indexFoto);
                } catch (Exception e){
                    e.printStackTrace();
                }

                if(photoFile != null){
                    Log.d("rutasd", photoFile.getAbsolutePath());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    startActivityForResult(takePictureIntent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE+indexFoto);
                    MediaScannerConnection.scanFile(this,new String[]{photoFile.toString()},null,null);
                }
            }
        }
        else{
            AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
            mensaje.setTitle("Atención")
                    .setMessage("Tu hora no está configurada correctamente. Cambiala para poder continuar")
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            mensaje.create().show();
        }


    }

    public File getLastPhotoTaken(int index){
        File file = new File(cliente.getPathFoto());
        Log.d("rutasd", file.getAbsolutePath());
        File[] carpetasInternas = file.listFiles();
        if(carpetasInternas==null){
            Log.d("rutasd","Carpetas internas null");
        }
        else{
            Log.d("rutasd","Carpetas internas not null "+carpetasInternas.length);
        }
        File last = null;

        for(int i=0;i<carpetasInternas.length;i++){
            if(carpetasInternas[i].isDirectory() && carpetasInternas[i].getName().equalsIgnoreCase(""+index)){
                Log.d("rutasd", "buscando fotitos :3");
                Log.d("rutasd", carpetasInternas[i].getAbsolutePath());

                File[] fotos = carpetasInternas[i].listFiles();
                if(fotos==null){
                    Log.d("rutasd", "Fotos null :'( ");
                }
                else{
                    Log.d("rutasd", "Fotos not null :3"+fotos.length);
                }
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
        res+="#";
        int minute = c.get(Calendar.MINUTE);
        if(minute<10) res = res+"0"+minute;
        else res += minute;
        res+="#";
        int second = c.get(Calendar.SECOND);
        if(second<10) res = res+"0"+second;
        else res += second;
        res+="#";


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

    public static Bitmap getSampleBitmapFromFile(String path, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try{
            BitmapFactory.decodeStream(new FileInputStream(new File(path)), null, options);

            int scale = calculateInSampleSize(options, reqWidth, reqHeight);

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;

            return BitmapFactory.decodeStream(new FileInputStream(new File(path)), null, o2);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float)height / (float)reqHeight);
            final int widthRatio = Math.round((float)width / (float)reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static void copiar(File src, File dst){
        try{
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode >= CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && requestCode<=CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE+10) {
            if (resultCode == RESULT_OK) {
                int indx = requestCode-CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE;

                /*Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                tomaFotosButtons[indx-1].setImageBitmap(bitmap);
                String nombre = createImageName(indx);

                try {
                    File file = createImageFile(nombre, indx);

                    ByteArrayOutputStream bats = new ByteArrayOutputStream();
                    //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bats);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
                    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bats);

                    //byte[] bitmapdata = bats.toByteArray();
                    byte[] bitmapdata = scaledBitmap.copyPixelsToBuffer(bats);

                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                */

                try{
                    File ultima = getLastPhotoTaken(indx);
                    File copia = new File(cliente.getPathFoto()+"foto"+indx+".jpg");
                    copiar(ultima,copia);
                    Bitmap photo = getSampleBitmapFromFile(ultima.getPath(),100,80);
                    tomaFotosButtons[indx-1].setImageBitmap(photo);

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
