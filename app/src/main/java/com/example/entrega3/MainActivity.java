package com.example.entrega3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.entrega3.Fragments.DetalleMovie;
import com.example.entrega3.Fragments.MainFragment;
import com.example.entrega3.Fragments.SeriesFragment;
import com.example.entrega3.Fragments.TrendingFragment;
import com.example.entrega3.model.ItemList;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    //variable para cargar el fragmetn principal
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    DetalleMovie detalleMovieFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        //establecer evento onclick al navigationview
        navigationView.setNavigationItemSelectedListener(this);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //cargar fragment principal
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,new MainFragment());
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.Movies){
           fragmentManager = getSupportFragmentManager();
           fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.container,new MainFragment());
           //setContentView(R.layout.main_fragment);
           fragmentTransaction.commit();

       }
        if(menuItem.getItemId() == R.id.Series){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new SeriesFragment());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.Trending){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new TrendingFragment());
            fragmentTransaction.commit();

        }else{
            return false;
        }
        return true;


    }

    @Override
    public void enviarItemList(ItemList items) {
        detalleMovieFragment = new DetalleMovie();
        //objeto bundle para transportar informacion
        Bundle bundleEnvio = new Bundle();
        //enviar objeto con serializable
        bundleEnvio.putSerializable("objeto",  items);
        detalleMovieFragment.setArguments(bundleEnvio);
        //abrir fragment
        fragmentManager  = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,detalleMovieFragment);
        fragmentTransaction.commit();
    }
}