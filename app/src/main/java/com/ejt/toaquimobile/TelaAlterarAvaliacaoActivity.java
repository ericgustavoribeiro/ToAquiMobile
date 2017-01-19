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

public class TelaAlterarAvaliacaoActivity extends AppCompatActivity implements Runnable{

    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private static int id_user;
    private static int id_estab;
    private static int nota;
    private static String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alterar_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it3 = getIntent();
        Bundle b3 = it3.getExtras();
        id_estab = b3.getInt("id_estab");
        nota = b3.getInt("nota");
        desc = b3.getString("desc");

        SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
        id_user = sharedPreferences.getInt("id_user", 0);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Alterando Avaliacao...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();


    }

    @Override
    public void run() {
        WebService ws = new WebService();
        try{

            ws.avaliacaoRemover(id_user, id_estab);

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setDescricao(desc);
            avaliacao.setNota(nota);
            avaliacao.setId_estabelecimento(id_estab);
            avaliacao.setId_user(id_user);

            ws.avaliacaoCadastra(avaliacao);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dialog.dismiss();
            Intent it = new Intent(this, TelaAvaliacaoActivity.class);
            Bundle b = new Bundle();
            b.putInt("id_estab", id_estab);
            it.putExtras(b);
            //CASO DE BOZO APAGAR
            ws=null;
            handler = null;
            dialog = null;
            id_user = 0;
            id_estab = 0;
            nota = 0;
            desc = null;

            startActivity(it);
            finish();
        }
    }
}
