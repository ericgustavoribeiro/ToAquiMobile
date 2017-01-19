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

import java.util.ArrayList;
import java.util.List;

public class TelaMeusEstab extends AppCompatActivity implements Runnable {

    private List<Estabelecimento> estabelecimentos;
    private List<Estabelecimento> estabelecimentosPendentes;
    private EstabelecimentosAdapter adapter;
    private EstabelecimentosAdapter adapterPendete;
    private ListView listViewAprovados;
    private ListView listViewPendentes;
    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private static int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_meus_estab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewAprovados = (ListView) findViewById(R.id.listViewAprovados);
        listViewAprovados.setEmptyView(findViewById(android.R.id.empty));
        listViewPendentes = (ListView) findViewById(R.id.listViewPendentes);
        listViewPendentes.setEmptyView(findViewById(android.R.id.empty));
        // setContentView(listView);



        dialog = new ProgressDialog(this);
        dialog.setMessage("Carregando Meus Estabelecimentos...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        WebService ws = new WebService();
        final Intent it = new Intent(this, TelaResultadoEstab.class);
        int id;

        try{
            listViewAprovados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Estabelecimento estabelecimento = (Estabelecimento)adapterView.getItemAtPosition(position);
                    Toast.makeText(TelaMeusEstab.this, estabelecimento.getNome(), Toast.LENGTH_SHORT).show();
                    id = estabelecimento.getId_estabelecimento();
                    Bundle b = new Bundle();
                    b.putInt("id_estab", (int) id);
                    it.putExtras(b);
                    startActivity(it);
                }});

            listViewPendentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Estabelecimento estabelecimento = (Estabelecimento)adapterView.getItemAtPosition(position);
                    Toast.makeText(TelaMeusEstab.this, estabelecimento.getNome(), Toast.LENGTH_SHORT).show();
                    id = estabelecimento.getId_estabelecimento();
                    Bundle b = new Bundle();
                    b.putInt("id_estab", (int) id);
                    it.putExtras(b);
                    startActivity(it);
                }});

            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            id_user = sharedPreferences.getInt("id_user", 0);

            estabelecimentos = ws.estabelecimentoListarCadastrados(id_user);
            estabelecimentosPendentes = ws.estabelecimentoListarCadastradosPendentes(id_user);


            ArrayList<Estabelecimento> estabs = new ArrayList<Estabelecimento>();
            ArrayList<Estabelecimento> estabsPendentes = new ArrayList<Estabelecimento>();

            for (Estabelecimento est : estabelecimentos) {
                estabs.add(est);
            }





            for (Estabelecimento est : estabelecimentosPendentes) {
                estabsPendentes.add(est);
            }

            adapter = new EstabelecimentosAdapter(this, estabs);
            listViewAprovados.setAdapter(adapter);

            adapterPendete = new EstabelecimentosAdapter(this, estabsPendentes);
            listViewPendentes.setAdapter(adapterPendete);
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
            estabelecimentosPendentes=null;
            adapter=null;
            adapterPendete=null;
            listViewAprovados=null;
            listViewPendentes=null;
        }
    }
}
