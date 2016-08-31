package com.edhuar.mercaton;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class HistoricoCompras extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    String codigo;
    String rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_compras);

        codigo = getIntent().getStringExtra("codigo");
        rol = getIntent().getStringExtra("rol");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerComprasAdapter adapter = new ViewPagerComprasAdapter(getSupportFragmentManager());
        //adapter.addFragment(ComprasMesFragment.newInstance(codigo,"Agosto"), "Agosto");
        adapter.addFragment(ComprasMesFragment.newInstance(codigo,"Setiembre"), "Setiembre");
        adapter.addFragment(ComprasMesFragment.newInstance(codigo,"Octubre"), "Octubre");
        adapter.addFragment(ComprasMesFragment.newInstance(codigo,"Noviembre"), "Noviembre");
        adapter.addFragment(ComprasMesFragment.newInstance(codigo,"Diciembre"), "Diciembre");
        adapter.addFragment(ComprasMesFragment.newInstance(codigo,"Enero"), "Enero");
        viewPager.setAdapter(adapter);
        //TODO obtener valor de cada compra
    }

    class ViewPagerComprasAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerComprasAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_historico_compras, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.show_premios:
                AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();

                mensaje.setView(inflater.inflate(R.layout.info_premios, null))
                        .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                mensaje.create().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
