package com.edhuar.mercaton;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.GridView;

import java.util.ArrayList;

public class GaleriaSemanal extends AppCompatActivity {

    String codigo;
    String rol;
    int index;
    Cliente cliente;
    private Utils utils;
    private ArrayList<String> imagePaths = new ArrayList<String>();
    private GridViewImageAdapter adapter;
    private GridView gridView;
    private int columnWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_semanal);
        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");
        index = getIntent().getIntExtra("index",-1);

        cliente = new Cliente(codigo, getApplicationContext());

        gridView = (GridView) findViewById(R.id.grid_view);

        utils = new Utils(this);

        // Initilizing Grid View
        InitilizeGridLayout();

        // loading all image paths from SD card
        //imagePaths = utils.getFilePaths();
        imagePaths = cliente.getPathPhotos(index);

        Log.d("pathimage",imagePaths.toString());
        // Gridview adapter
        adapter = new GridViewImageAdapter(this, imagePaths,
                columnWidth,codigo, rol, index);

        // setting grid view adapter
        gridView.setAdapter(adapter);
    }

    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                AppConstant.GRID_PADDING, r.getDisplayMetrics());

        columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1) * padding)) / AppConstant.NUM_OF_COLUMNS);

        gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }

}
