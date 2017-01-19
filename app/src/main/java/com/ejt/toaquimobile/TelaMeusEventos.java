package com.ejt.toaquimobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TelaMeusEventos extends AppCompatActivity implements Runnable {

    private List<Evento> aprovados;
    private List<Evento> pendentes;
    private EventosAdapter adapterAprovados;
    private EventosAdapter adapterPendentes;
    private ListView listViewAprovados;
    private ListView listViewPendentes;
    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private static int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_meus_eventos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listViewAprovados = (ListView) findViewById(R.id.listViewEventosAprovados);
        listViewAprovados.setEmptyView(findViewById(android.R.id.empty));
        listViewPendentes = (ListView) findViewById(R.id.listViewEventosPendentes);
        listViewPendentes.setEmptyView(findViewById(android.R.id.empty));

        dialog = new ProgressDialog(this);
        dialog.setMessage("Carregando Meus Eventos...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();


    }

    @Override
    public void run() {
        WebService ws = new WebService();
        try{


            final Intent it = new Intent(this, TelaResultadoEvento.class);
            int id;

            listViewAprovados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Evento evento = (Evento)adapterView.getItemAtPosition(position);
                    Toast.makeText(TelaMeusEventos.this, evento.getNome(), Toast.LENGTH_SHORT).show();
                    id = evento.getId_evento();
                    Bundle b = new Bundle();
                    b.putInt("id_evento", (int) id);
                    it.putExtras(b);
                    startActivity(it);
                    finish();
                }});

            listViewPendentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Evento evento = (Evento)adapterView.getItemAtPosition(position);
                    Toast.makeText(TelaMeusEventos.this, evento.getNome(), Toast.LENGTH_SHORT).show();
                    id = evento.getId_evento();
                    Bundle b = new Bundle();
                    b.putInt("id_evento", (int) id);
                    it.putExtras(b);
                    startActivity(it);
                    finish();
                }});




            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            id_user = sharedPreferences.getInt("id_user", 0);

            aprovados = ws.eventoListarCadastrados(id_user);
            pendentes = ws.eventoListarCadastradosPendentes(id_user);

            adapterAprovados = new EventosAdapter(this, aprovados);
            listViewAprovados.setAdapter(adapterAprovados);


            adapterPendentes = new EventosAdapter(this, pendentes);
            listViewPendentes.setAdapter(adapterPendentes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dialog.dismiss();
            ws = null;
            handler = null;
            dialog = null;
            //APAGAR CASO DE ZEBRA
            aprovados= null;
            pendentes= null;
            adapterAprovados= null;
            adapterPendentes= null;
            listViewAprovados= null;
            listViewPendentes = null;

            listViewAprovados=null;
            listViewPendentes=null;
        }
    }
}
