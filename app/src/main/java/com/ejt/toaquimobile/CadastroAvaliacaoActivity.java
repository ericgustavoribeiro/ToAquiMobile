package com.ejt.toaquimobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class CadastroAvaliacaoActivity extends AppCompatActivity implements Runnable{

    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private static int id_user;
    private static int id_estab;
    private static int nota;
    private static String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it = getIntent();
        Bundle b = it.getExtras();
        id_estab = b.getInt("id_estab");
        nota = b.getInt("nota");
        desc = b.getString("desc");

        SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
        id_user = sharedPreferences.getInt("id_user", 0);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //chamo um método para melhor organização.
                updateUI(msg);
            }
        };

        dialog = new ProgressDialog(this);
        dialog.setMessage("Cadastrando Avaliacao...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();
    }

    private void updateUI(Message msg) {
        if (msg.what == 1) {
            //Converto o object para string (pois foi o que eu passei)
            String texto = (String) msg.obj;
            //defino no meu TextView o texto.
            Toast.makeText(this, "Bem Vindo " + texto, Toast.LENGTH_SHORT).show();
        } else if (msg.what == 2) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("#ToAqui");
            alertDialog.setMessage("Dados Nulos ou Invalidos !");
            alertDialog.setIcon(R.drawable.error);

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();
        }
    }


    @Override
    public void run() {
        WebService ws = new WebService();
        try{

            if(nota!=0 && !(desc.equals(""))) {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setDescricao(desc);
                avaliacao.setNota(nota);
                avaliacao.setId_estabelecimento(id_estab);
                avaliacao.setId_user(id_user);

                ws.avaliacaoCadastra(avaliacao);

                Intent it = new Intent(this, TelaAvaliacaoActivity.class);
                Bundle b = new Bundle();
                b.putInt("id_estab", id_estab);
                it.putExtras(b);
                startActivity(it);
                finish();
            }else if(nota==0 && desc.equals("")){
                Message message = new Message();
                //defino um codigo para controle.
                message.what = 2;

                message.obj = "Email/Senha Nulos ou Invalidos !";
                //Envio da mensagem.
                handler.sendMessage(message);
                finish();
            }
        }catch (Exception e){
            Message message = new Message();
            //defino um codigo para controle.
            message.what = 2;

            message.obj = "Email/Senha Nulos ou Invalidos !";
            //Envio da mensagem.
            handler.sendMessage(message);
        }finally {
            dialog.dismiss();
            //CASO DE BOZO APAGAR
          //  ws=null;
          //  handler = null;
          //  dialog = null;
          //  id_user = 0;
          //  id_estab = 0;
          //  nota = 0;
          //  desc = null;


        }
    }
}
