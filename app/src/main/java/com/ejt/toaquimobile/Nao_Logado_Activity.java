package com.ejt.toaquimobile;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageButton;

public class Nao_Logado_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GridView.OnClickListener {

    private ImageButton buttonBar;
    private ImageButton buttonResta;
    private ImageButton buttonHotel;
    private ImageButton buttonEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nao__logado_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

            if (id == R.id.LoginCadastro){
            //  Intent it1 = new Intent(this, TelaCasdastroUsuario.class);
            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            String email = sharedPreferences.getString("email_user", null);

                Intent it1 = new Intent(this, TelaLogin.class);
                startActivity(it1);
                finish();

        } else if (id == R.id.MenuBares){

            Intent it1 = new Intent(this, TelaListarBar.class);
            startActivity(it1);

        } else if (id == R.id.MenuHotel){

            Intent it1 = new Intent(this, TelaListarHotel.class);
            startActivity(it1);

        } else if (id == R.id.MenuRestaurantes){

            Intent it1 = new Intent(this, TelaListarRestaurante.class);
            startActivity(it1);

        } else if (id == R.id.MenuEventos){

            Intent it1 = new Intent(this, TelaListarEvento.class);
            startActivity(it1);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBar:

                Intent it1 = new Intent(this, TelaListarBar.class);
                startActivity(it1);

                break;


            case R.id.buttonResta:

                Intent it2 = new Intent(this, TelaListarRestaurante.class);
                startActivity(it2);

                break;


            case R.id.buttonHotel:

                Intent its = new Intent(this, TelaListarHotel.class);
                startActivity(its);

                break;


            case R.id.buttonEvent:

                Intent x = new Intent(this, TelaListarEvento.class);
                startActivity(x);

                break;
        }
    }
}
