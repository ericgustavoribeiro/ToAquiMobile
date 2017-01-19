package com.ejt.toaquimobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class TelaRemoverUsuarioActivity extends AppCompatActivity implements Runnable{

    private int id_user;
    private Handler handler = new Handler();
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_remover_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Removendo Usuario...");
        dialog.setTitle("#ToAqui");
        dialog.show();
        Thread t = new Thread(TelaRemoverUsuarioActivity.this);
        t.start();

    }

    @Override
    public void run() {

        WebService ws = new WebService();

        try{
            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            id_user = sharedPreferences.getInt("id_user", 0);

            ws.usuarioRemoverId(id_user);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            // limpar tudo
            editor.clear();
            editor.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dialog.dismiss();
            ws = null;
            handler = null;
            finish();
        }
    }
}
