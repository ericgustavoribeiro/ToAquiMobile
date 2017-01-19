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

public class TelaAlterarUsuario extends AppCompatActivity implements Runnable, GridView.OnClickListener {

    private EditText edtNome;
    private EditText edtSenha;
    private EditText edtConfSenha;
    private Button buttonAlterar;
    private Button buttonRemover;
    private Handler handler = new Handler();
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alterar_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome = (EditText) findViewById(R.id.editTextNomeAlterar);
        edtSenha = (EditText) findViewById(R.id.editTextSenhaAlterar);
        edtConfSenha = (EditText) findViewById(R.id.editTextConfSenhaAlterar);


        buttonAlterar = (Button) findViewById(R.id.buttonAlterarUser);
        buttonAlterar.setOnClickListener(this);
        buttonRemover = (Button) findViewById(R.id.buttonRemoverUser);
        buttonRemover.setOnClickListener(this);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //chamo um método para melhor organização.
                updateUI(msg);
            }
        };

        SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
        String email = sharedPreferences.getString("email_user", null);
        if(email == null) {
        finish();
        }
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
        }
    }

    @Override
    public void run() {
        String nome = edtNome.getText().toString();
        String senha = edtSenha.getText().toString();
        String confSenha = edtConfSenha.getText().toString();

        try {

            if (senha.equals(confSenha) && !(senha.equals(""))) {
            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String email = sharedPreferences.getString("email_user", null);

            WebService ws = new WebService();

                Usuario user = ws.usuarioProcurarEmail(email);
                user.setNome(nome);
                user.setSenha(JavaMD5Hash.md5(senha));

                ws.usuarioAtualizar(user);

                editor.putInt("id_user", user.getId_user());
                editor.putString("nome_user", user.getNome());
                editor.putString("email_user", user.getEmail());
                editor.commit();

                finish();


           // } catch (Exception e) {
           //     e.printStackTrace();
           // } finally {
           //     dialog.dismiss();
                //CASO DE BOZO APAGAR
           //     ws=null;
          //      handler = null;
          //      dialog = null;
          //      edtNome= null;
          //      edtSenha= null;
          //      edtConfSenha= null;
          //      buttonAlterar= null;
          //      buttonRemover = null;
          //      nome = null;
          //      senha = null;
           //     confSenha = null;


            //}

        } else if (!(senha.equals(confSenha))) {
          //  Toast.makeText(TelaAlterarUsuario.this, "Senhas Diferentes !", Toast.LENGTH_SHORT).show();
          //  dialog.dismiss();
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
    }catch (Exception e){
            Message message = new Message();
            //defino um codigo para controle.
            message.what = 2;

            message.obj = "Email/Senha Nulos ou Invalidos !";
            //Envio da mensagem.
            handler.sendMessage(message);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAlterarUser:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //botão SIM clicado

                                Thread t = new Thread(TelaAlterarUsuario.this);
                                t.start();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                finish();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Deseja Alterar os Dados da Sua Conta ?").setPositiveButton("SIM", dialogClickListener)
                        .setNegativeButton("NÃO", dialogClickListener).show();


                //Remover e testar
                dialog = new ProgressDialog(this);
                dialog.setMessage("Alterando Usuario...");
                dialog.setTitle("#ToAqui");
                dialog.show();


                break;
            case R.id.buttonRemoverUser:

                DialogInterface.OnClickListener dialogClickListener2 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //botão SIM clicado

                                Intent it = new Intent(TelaAlterarUsuario.this, TelaRemoverUsuarioActivity.class);
                                startActivity(it);
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                finish();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage("Deseja Alterar os Dados da Sua Conta ?").setPositiveButton("SIM", dialogClickListener2)
                        .setNegativeButton("NÃO", dialogClickListener2).show();


        }
    }
}