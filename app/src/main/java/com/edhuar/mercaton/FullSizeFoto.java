package com.edhuar.mercaton;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

public class FullSizeFoto extends AppCompatActivity{

    private static final String TAG = "Touch";
    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 1f,MAX_ZOOM = 1f;

    // These matrices will be used to scale points of the image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // The 3 states (events) which the user is trying to perform
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // these PointF objects are used to record the point(s) the user is touching
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;

    ImageView fotoFull;
    String codigo;
    String rol;
    Cliente cliente;
    int indx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_size_foto);

        getSupportActionBar().hide();
        fotoFull = (ImageView) findViewById(R.id.foto_full);
        //fotoFull.setOnTouchListener(this);
        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");
        indx = getIntent().getIntExtra("index",-1);
        cliente = new Cliente(codigo,getApplicationContext());
        Bitmap bitmap = MedicionFotos.getSampleBitmapFromFile(cliente.getPathFoto()+"foto"+indx+".jpg",200,200);
        Glide.clear(fotoFull);
        Glide.with(this)
                .load(new File(cliente.getPathFoto()+"foto"+indx+".jpg"))
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(fotoFull);
    }
}
