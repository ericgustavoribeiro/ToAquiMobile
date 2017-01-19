package com.ejt.toaquimobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RemoverEstabActivity extends AppCompatActivity implements Runnable{

    private int id_estab;
    private Handler handler = new Handler();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_estab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it = getIntent();
        Bundle b = it.getExtras();
        id_estab = b.getInt("id_estab");

        dialog = new ProgressDialog(this);
        dialog.setMessage("Removendo Estabelecimento...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        WebService ws = new WebService();
        try{

            ws.estabelecimentoRemoverId(id_estab);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dialog.dismiss();



            ws = null;
            //REMOVER SE DER ZEBRA
            handler = null;
            dialog = null;
            id_estab = 0;

//            Thread t = new Thread(this);
//            t.destroy();
            finish();
        }
    }
}
