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
import android.view.View;

public class RemoverAvaliacaoActivity extends AppCompatActivity implements Runnable{

    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private static int id_user;
    private static int id_estab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it2 = getIntent();
        Bundle b2 = it2.getExtras();
        id_estab = b2.getInt("id_estab");

        SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
        id_user = sharedPreferences.getInt("id_user", 0);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Removendo Avaliacao...");
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
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dialog.dismiss();
            Intent it = new Intent(this, TelaAvaliacaoActivity.class);
            Bundle b = new Bundle();
            b.putInt("id_estab", id_estab);
            it.putExtras(b);
            startActivity(it);

            //CASO DE ZEBRA APAGAR
            ws=null;
            handler = null;
            dialog = null;
            id_user = 0;
            id_estab = 0;

            finish();
        }
    }
}
