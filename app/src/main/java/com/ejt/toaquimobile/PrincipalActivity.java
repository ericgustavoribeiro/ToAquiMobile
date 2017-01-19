package com.ejt.toaquimobile;

import android.content.ClipData;
import android.content.Context;
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
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GridView.OnClickListener {

    private ImageButton buttonBar;
    private ImageButton buttonResta;
    private ImageButton buttonHotel;
    private ImageButton buttonEvent;
    private MenuItem item;
    private TextView navigator_nome;
    private TextView navigator_email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
        String email = sharedPreferences.getString("email_user", null);
        String nome = sharedPreferences.getString("nome_user", null);


        //    if(email != null) {
            setContentView(R.layout.principal);
      //  }else{
      //      setContentView(R.layout.activity_nao__logado_);

       // }
        navigator_nome = (TextView) findViewById(R.id.Nome);
        navigator_email = (TextView) findViewById(R.id.email);


        //navigator_nome = (TextView) findViewById(R.id.Nome);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // setContentView(R.layout.principal);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        buttonBar = (ImageButton)findViewById(R.id.buttonBar);
        buttonBar.setOnClickListener(this);
        buttonResta = (ImageButton)findViewById(R.id.buttonResta);
        buttonResta.setOnClickListener(this);
        buttonHotel = (ImageButton)findViewById(R.id.buttonHotel);
        buttonHotel.setOnClickListener(this);
        buttonEvent = (ImageButton) findViewById(R.id.buttonEvent);
        buttonEvent.setOnClickListener(this);



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

/*    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        item = (MenuItem) findViewById(R.id.Teste);
        item.setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
        String email = sharedPreferences.getString("email_user", null);


        if (id == R.id.MenuCadastreEstabele) {

            if (email != null) {
                Intent it = new Intent(this, TelaCadastroEstabelecimento.class);
                startActivity(it);
            } else{
                Intent it1 = new Intent(this, TelaLogin.class);
                startActivity(it1);
        }

        } else if (id == R.id.MenuCadastreEvento){

            if (email != null) {
                Intent it1 = new Intent(this, TelaCadastrarEvento.class);
                startActivity(it1);
            } else {
                Intent it1 = new Intent(this, TelaLogin.class);
                startActivity(it1);
            }

        } else if (id == R.id.minhaConta){


            if(email != null) {
                Intent it1 = new Intent(this, TelaAlterarUsuario.class);
                startActivity(it1);
            }else{
                Intent it1 = new Intent(this, TelaLogin.class);
                startActivity(it1);
            }
    }
         else if (id == R.id.meusEstabelecimentos){

            if (email != null) {
                Intent it1 = new Intent(this, TelaMeusEstab.class);
                startActivity(it1);
            } else {
                Intent it1 = new Intent(this, TelaLogin.class);
                startActivity(it1);
            }

    }
    else if (id == R.id.meusEventos){

            if (email != null) {
                Intent it1 = new Intent(this, TelaMeusEventos.class);
                startActivity(it1);
            } else {
                Intent it1 = new Intent(this, TelaLogin.class);
                startActivity(it1);
            }

    }  else if (id == R.id.MenuBares){

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

        } else if (id == R.id.MenuSair){

            if (email != null) {
                Intent it1 = new Intent(this, TelaSair.class);
                startActivity(it1);
                finish();
            } else {
                Intent it1 = new Intent(this, TelaLogin.class);
                startActivity(it1);
            }




        }else  if (id == R.id.LoginCadastro){
            //  Intent it1 = new Intent(this, TelaCasdastroUsuario.class);
            Intent it1 = new Intent(this, TelaLogin.class);
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

                Intent it2 = new Intent(this,  TelaListarRestaurante.class);
                startActivity(it2);

                break;


            case R.id.buttonHotel:

                Intent its = new Intent(this,  TelaListarHotel.class);
                startActivity(its);

                break;


            case R.id.buttonEvent:

                Intent x = new Intent(this,  TelaListarEvento.class);
                startActivity(x);

                break;



        }








    }


}
