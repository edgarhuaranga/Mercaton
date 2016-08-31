package com.edhuar.mercaton;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FullScreenViewActivity extends AppCompatActivity {

    private Utils utils;
    private FullScreenImageAdapter adapter;
    private ViewPager viewPager;
    String codigo;
    String rol;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_view);

        viewPager = (ViewPager) findViewById(R.id.pager);

        utils = new Utils(getApplicationContext());

        Intent i = getIntent();
        int position = i.getIntExtra("position", 0);
        int numsemana= i.getIntExtra("semana", -1);
        codigo = i.getStringExtra("codigo");
        rol = i.getStringExtra("rol");
        cliente = new Cliente(codigo, getApplicationContext());


        adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,cliente.getPathPhotos(numsemana));

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);
    }
}
