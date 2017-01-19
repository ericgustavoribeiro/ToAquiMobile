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

public class TelaSair extends AppCompatActivity implements Runnable{

    private Handler handler = new Handler();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sair);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Saindo...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        try{

            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id_user", Integer.parseInt(null));
            editor.putString("nome_user", null);
            editor.putString("email_user", null);

            // limpar tudo
            editor.clear();
            editor.commit();


            //  editor.commit();

        }catch (Exception e){

        }finally {
            dialog.dismiss();
            handler = null;
            dialog=null;
            finish();
        }
    }
}
