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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TelaAvaliacaoActivity extends AppCompatActivity implements Runnable, GridView.OnClickListener {

    private List<Avaliacao> avaliacoes;
    private AvaliacaoAdapter adapter;
    private ListView listView;
    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private static int id_user;
    private static int id_estab;
    private EditText edtNota;
    private EditText edtDescricao;
    private Button buttonAvaliar;
    private Button buttonRemover;
    private Button buttonAlterar;
    private Avaliacao avaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNota = (EditText) findViewById(R.id.editTextNota);
        edtDescricao = (EditText) findViewById(R.id.editTextDesc);

        buttonAvaliar = (Button) findViewById(R.id.buttonCadastrarAvaliacao);
        buttonAvaliar.setOnClickListener(this);
        buttonRemover = (Button) findViewById(R.id.buttonRemoverAvaliacao);
        buttonRemover.setOnClickListener(this);
        buttonAlterar = (Button) findViewById(R.id.buttonAlterarAvaliacao);
        buttonAlterar.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listViewAvaliacao);
        SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
        id_user = sharedPreferences.getInt("id_user", 0);

        Intent it = getIntent();
        Bundle b = it.getExtras();
        id_estab = b.getInt("id_estab");

        Log.i("Teste", "Id : "+id_user);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //chamo um método para melhor organização.
                updateUI(msg);
            }
        };


        dialog = new ProgressDialog(this);
        dialog.setMessage("Carregando Avaliações...");
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCadastrarAvaliacao :

                try{
                    String desc = edtDescricao.getText().toString();
                    int nota = Integer.parseInt(edtNota.getText().toString());

                    if(nota!=0 && !(desc.equals(""))) {
                        Intent it = new Intent(this, CadastroAvaliacaoActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("id_estab", id_estab);
                        b.putString("desc", desc);
                        b.putInt("nota", nota);
                        it.putExtras(b);
                        startActivity(it);
                        finish();
                    }else{
                        Message message = new Message();
                        //defino um codigo para controle.
                        message.what = 2;

                        message.obj = "Email/Senha Nulos ou Invalidos !";
                        //Envio da mensagem.
                        handler.sendMessage(message);
                     //   finish();
                    }
                }catch (Exception e ){
                    Message message = new Message();
                    //defino um codigo para controle.
                    message.what = 2;

                    message.obj = "Email/Senha Nulos ou Invalidos !";
                    //Envio da mensagem.
                    handler.sendMessage(message);
                }

                break;
            case R.id.buttonRemoverAvaliacao :

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent it2 = new Intent(TelaAvaliacaoActivity.this, RemoverAvaliacaoActivity.class);
                                Bundle b2 = new Bundle();
                                b2.putInt("id_estab", id_estab);
                                it2.putExtras(b2);
                                startActivity(it2);
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                finish();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Deseja Excluir a Avaliação ?").setPositiveButton("SIM", dialogClickListener)
                        .setNegativeButton("NÃO", dialogClickListener).show();


                break;
            case R.id.buttonAlterarAvaliacao :

                DialogInterface.OnClickListener dialogClickListener2 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                try {
                                    Intent it3 = new Intent(TelaAvaliacaoActivity.this, TelaAlterarAvaliacaoActivity.class);
                                    Bundle b3 = new Bundle();
                                    String desc3 = edtDescricao.getText().toString();
                                    int nota3 = Integer.parseInt(edtNota.getText().toString());

                                    if(nota3!=0 && !(desc3.equals(""))) {
                                        b3.putInt("id_estab", id_estab);
                                        b3.putString("desc", desc3);
                                        b3.putInt("nota", nota3);
                                        it3.putExtras(b3);
                                        startActivity(it3);
                                        finish();
                                    }else{
                                        Message message = new Message();
                                        //defino um codigo para controle.
                                        message.what = 2;

                                        message.obj = "Email/Senha Nulos ou Invalidos !";
                                        //Envio da mensagem.
                                        handler.sendMessage(message);
                                        //   finish();
                                    }
                                }catch (Exception e){
                                    Message message = new Message();
                                    //defino um codigo para controle.
                                    message.what = 2;

                                    message.obj = "Email/Senha Nulos ou Invalidos !";
                                    //Envio da mensagem.
                                    handler.sendMessage(message);
                                }
                                    break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                finish();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage("Deseja Alterar a Avaliação ?").setPositiveButton("SIM", dialogClickListener2)
                        .setNegativeButton("NÃO", dialogClickListener2).show();


                break;
        }
    }

    @Override
    public void run() {
        WebService ws = new WebService();
        try{

            avaliacoes = ws.avaliacaoListar(id_estab);



            if(id_user == 0){
                buttonAvaliar.setVisibility(View.INVISIBLE);
                buttonAlterar.setVisibility(View.INVISIBLE);
                buttonRemover.setVisibility(View.INVISIBLE);
                edtNota.setVisibility(View.INVISIBLE);
                edtDescricao.setVisibility(View.INVISIBLE);
            }else if(id_user!=0){
                edtNota.setVisibility(View.VISIBLE);
                edtDescricao.setVisibility(View.VISIBLE);
            }

            for(Avaliacao a : avaliacoes){

                if(a.getId_user() == id_user){
                    edtNota.setText(String.valueOf(a.getNota()));
                    edtDescricao.setText(a.getDescricao());
                    buttonAvaliar.setVisibility(View.INVISIBLE);
                }
            }

            /*if(id_user == 0){
                buttonAvaliar.setVisibility(View.INVISIBLE);
                buttonAlterar.setVisibility(View.INVISIBLE);
                buttonRemover.setVisibility(View.INVISIBLE);
                edtNota.setVisibility(View.INVISIBLE);
                edtDescricao.setVisibility(View.INVISIBLE);
            }else if(id_user!=0){
                edtNota.setVisibility(View.VISIBLE);
                edtDescricao.setVisibility(View.VISIBLE);
            }*/

            adapter = new AvaliacaoAdapter(this, avaliacoes);
            listView.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dialog.dismiss();
            //CASO DE BOZO APAGAR
            ws=null;
          //  handler = null;
          //  dialog = null;
        }
    }
}
