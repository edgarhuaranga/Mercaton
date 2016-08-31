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
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HistoricoExhibicion extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    String codigo;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_exhibicion);

        codigo = getIntent().getStringExtra("codigo");
        cliente = new Cliente(codigo,this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ExhibicionMesFragment.newInstance(codigo,"Setiembre"), "Setiembre");
        adapter.addFragment(ExhibicionMesFragment.newInstance(codigo,"Octubre"), "Octubre");
        adapter.addFragment(ExhibicionMesFragment.newInstance(codigo,"Noviembre"), "Noviembre");
        adapter.addFragment(ExhibicionMesFragment.newInstance(codigo,"Diciembre"), "Diciembre");
        adapter.addFragment(ExhibicionMesFragment.newInstance(codigo,"Enero"), "Enero");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
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


}
