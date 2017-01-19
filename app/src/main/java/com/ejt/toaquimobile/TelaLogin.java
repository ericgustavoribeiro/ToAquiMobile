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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity implements Runnable, GridView.OnClickListener{

    private EditText edtEmail;
    private EditText edtSenha;
    private Button button;
    private Handler handler = new Handler();

    private ProgressDialog dialog;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.buttonLogin);
        button.setOnClickListener(this);
        edtEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        edtSenha = (EditText) findViewById(R.id.editTextSenhaLogin);
        
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //chamo um método para melhor organização.
                updateUI(msg);
            }
        };
    }

    private void updateUI(Message msg) {
        if (msg.what == 1) {
            //Converto o object para string (pois foi o que eu passei)
            String texto = (String) msg.obj;
            //defino no meu TextView o texto.

        } else if (msg.what == 2) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("#ToAqui");
            alertDialog.setMessage("Email/Senha Nulos ou Invalidos !");
            alertDialog.setIcon(R.drawable.error);

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();
            //finalizo a activity
   //         Toast.makeText(this, "Android Handler - DevMedia", Toast.LENGTH_LONG).show();
   //         finish();
        }
    }


    @Override
    public void onClick(View v) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Logando...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }


    @Override
    public void run() {
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        WebService ws = new WebService();

    try {

        if (ws.usuarioLogin(email, JavaMD5Hash.md5(senha)) == true) {

            //   try{
            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();


            Usuario user = ws.usuarioProcurarEmail(email);
            // ArrayList<Estabelecimento> lista = ws.estabelecimentoListar();
            editor.putInt("id_user", user.getId_user());
            editor.putString("nome_user", user.getNome());
            editor.putString("email_user", user.getEmail());
            editor.commit();
            finish();
            Message message = new Message();
            //defino um codigo para controle.
            message.what = 1;

            message.obj = user.getNome();
            //Envio da mensagem.
            handler.sendMessage(message);
            // } catch (Exception e) {
            //     e.printStackTrace();
            // }finally {
            //     dialog.dismiss();
            //     ws=null;
            //handler = null;
            //dialog = null;
            //edtEmail= null;
            //edtSenha= null;
            //button= null;
            //email = null;
            //senha = null;

        }else if( ws.usuarioLogin(email,JavaMD5Hash.md5(senha)) != true){

            Message message = new Message();
            //defino um codigo para controle.
            message.what = 2;

            message.obj = "Email/Senha Nulos ou Invalidos !";
            //Envio da mensagem.
            handler.sendMessage(message);
    }
          //
          // }
    }catch (Exception e){
   /*     //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.error, null);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //exibe um Toast informativo.
                Toast.makeText(TelaLogin.this, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
                //desfaz o alerta.
                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("#ToAqui");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
   */

        Message message = new Message();
        //defino um codigo para controle.
        message.what = 2;

        message.obj = "Email/Senha Nulos ou Invalidos !";
        //Envio da mensagem.
        handler.sendMessage(message);

//        ErrorDialog("Teste");
    }finally {
     dialog.dismiss();
     //   Message message = new Message();
     //   message.what = 2;
     //   handler.sendMessage(message);
    }
    }



    private void ErrorDialog(String Description) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("You get Error...");
        alertDialog.setMessage(Description);
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
