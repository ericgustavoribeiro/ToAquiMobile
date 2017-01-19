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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

public class TelaCasdastroUsuario extends AppCompatActivity implements Runnable, GridView.OnClickListener {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText edtConfirmarSenha;
    private Button button;
    private Handler handler = new Handler();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_casdastro_usuaro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome = (EditText) findViewById(R.id.editTextNomeCadastroUser);
        edtEmail = (EditText) findViewById(R.id.editTextEmailCadastroUser);
        edtSenha = (EditText) findViewById(R.id.editTextSenhaCadastroUser);
        edtConfirmarSenha = (EditText) findViewById(R.id.editTextSenha2CadastroUser);

        button = (Button) findViewById(R.id.buttonCadastroUsuario);
        button.setOnClickListener(this);

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
            Toast.makeText(this, "Bem Vindo " + texto, Toast.LENGTH_SHORT).show();
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
        }
    }

    @Override
    public void onClick(View v) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Cadastrando Usuario...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();
    }


    @Override
    public void run() {
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        String confSenha = edtConfirmarSenha.getText().toString();
        try {
        if(senha.equals(confSenha) && !(senha.equals(""))){
            Usuario user = new Usuario(nome, email, JavaMD5Hash.md5(senha));
            WebService ws = new WebService();
            try {
                ws.usuarioCadastrar(user);
                SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

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
                finish();
            }catch (SoapFault e){
                Message message = new Message();
                //defino um codigo para controle.
                message.what = 2;

                message.obj = "Email/Senha Nulos ou Invalidos !";
                //Envio da mensagem.
                handler.sendMessage(message);
            }

            }else if (!(senha.equals(confSenha))){
            Message message = new Message();
            //defino um codigo para controle.
            message.what = 2;

            message.obj = "Email/Senha Nulos ou Invalidos !";
            //Envio da mensagem.
            handler.sendMessage(message);
            }else if (senha.equals("") || nome.equals("") || confSenha.equals("")){
            Message message = new Message();
            //defino um codigo para controle.
            message.what = 2;

            message.obj = "Email/Senha Nulos ou Invalidos !";
            //Envio da mensagem.
            handler.sendMessage(message);
        }
        } catch (SoapFault e){
            Message message = new Message();
            //defino um codigo para controle.
            message.what = 2;

            message.obj = "Email/Senha Nulos ou Invalidos !";
            //Envio da mensagem.
            handler.sendMessage(message);
        } catch (Exception e) {
            Message message = new Message();
            //defino um codigo para controle.
            message.what = 2;

            message.obj = "Email/Senha Nulos ou Invalidos !";
            //Envio da mensagem.
            handler.sendMessage(message);
        } finally {

            dialog.dismiss();
          //   handler = null;
            //   dialog = null;
            //   edtNome= null;
            //   edtEmail= null;
            //   edtSenha= null;
            //  edtConfirmarSenha= null;
            //  button = null;
        //    finish();
        }


    }
}
