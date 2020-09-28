package com.example.pruebanativa;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.pruebanativa.ui.CitasFragment;
import com.example.pruebanativa.ui.CuposFragment;
import com.example.pruebanativa.ui.PrestadoresFragment;
import com.example.pruebanativa.ui.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener   {


    DrawerLayout drawer;
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

         drawer = findViewById(R.id.drawer_layout);
         fragmentManager = getSupportFragmentManager();



        NavigationView navigationView = findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, new HomeFragment())
                .addToBackStack(null)
                .commit();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean state = false;

        if (id == R.id.nav_home) {
            Fragment homeFragment = new HomeFragment();
            show(homeFragment);

            state = true;
        } else if (id == R.id.nav_prestadores_servicios) {
            Fragment Prestadores = new PrestadoresFragment();
            show(Prestadores);
            drawer.closeDrawer(GravityCompat.START);
            state = true;
        } else if (id == R.id.nav_consultar_citas) {
            Fragment Citas = new CitasFragment();
            show(Citas);
            drawer.closeDrawer(GravityCompat.START);
            state = true;
        } else if (id == R.id.nav_cupos) {
            Fragment Cupos = new CuposFragment();
            show(Cupos);
            drawer.closeDrawer(GravityCompat.START);
            state = true;
        }
        else if (id == R.id.nav_salir) {

            state = false;
        }
        return  state;
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);

    }

    private void show(Fragment fragment) {

       // DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);


        fragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit();

        drawer.closeDrawer(GravityCompat.START);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_home:
               // newGame();

                return true;
            case R.id.nav_prestadores_servicios:
                //showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
