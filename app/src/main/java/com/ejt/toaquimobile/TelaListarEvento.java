package com.ejt.toaquimobile;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class TelaListarEvento extends AppCompatActivity implements Runnable{

    private List<Evento> eventos;
    private EventosAdapter adapter;
    private ListView listView;
    private Handler handler = new Handler();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Carregando Eventos...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {

        listView = (ListView) findViewById(R.id.listViewEventos);
        listView.setEmptyView(findViewById(android.R.id.empty));
        WebService ws = new WebService();

        final Intent it = new Intent(this, TelaResultadoEvento.class);
        int id;

        try{
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Evento evento = (Evento)adapterView.getItemAtPosition(position);
                    Toast.makeText(TelaListarEvento.this, evento.getNome(), Toast.LENGTH_SHORT).show();
                    id = evento.getId_evento();
                    Bundle b = new Bundle();
                    b.putInt("id_evento", (int) id);
                    it.putExtras(b);
                    startActivity(it);
                    finish();
                }});

            eventos = ws.eventoListar();

            adapter = new EventosAdapter(this, eventos);
            listView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dialog.dismiss();
            ws = null;
            handler = null;
            dialog = null;
            //APAGAR CASO DE ZEBRA
            eventos = null;
            adapter = null;
            listView = null;
        }

    }
}
