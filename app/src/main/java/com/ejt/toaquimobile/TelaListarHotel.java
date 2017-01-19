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

import java.util.ArrayList;
import java.util.List;

public class TelaListarHotel extends AppCompatActivity implements Runnable{

    private List<Estabelecimento> estabelecimentos;
    private EstabelecimentosAdapter adapter;
    private ListView listView;
    private Handler handler = new Handler();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar_hotel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Carregando Hoteis...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        listView = (ListView) findViewById(R.id.listViewHoteis);
        listView.setEmptyView(findViewById(android.R.id.empty));
        // setContentView(listView);
        WebService ws = new WebService();
        final Intent it = new Intent(this, TelaResultadoEstab.class);
        int id;



        try{
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Estabelecimento estabelecimento = (Estabelecimento)adapterView.getItemAtPosition(position);
                    Toast.makeText(TelaListarHotel.this, estabelecimento.getNome(), Toast.LENGTH_SHORT).show();
                    id = estabelecimento.getId_estabelecimento();
                    Bundle b = new Bundle();
                    b.putInt("id_estab", (int) id);
                    it.putExtras(b);
                    startActivity(it);
                    finish();
                }});

            estabelecimentos = ws.estabelecimentoListar();

            ArrayList<Estabelecimento> hoteis = new ArrayList<Estabelecimento>();

            for (Estabelecimento est : estabelecimentos) {

                if (est.getCategoria().equals("Hotel")) {
                    hoteis.add(est);
                }

            }


            adapter = new EstabelecimentosAdapter(this, hoteis);
            listView.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dialog.dismiss();
            ws = null;
            handler = null;
            dialog = null;
            //APAGAR CASO DE ZEBRA
            estabelecimentos = null;
            adapter = null;
            listView = null;
        }
    }
}
