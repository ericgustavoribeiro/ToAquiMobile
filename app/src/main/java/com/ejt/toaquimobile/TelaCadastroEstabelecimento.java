package com.ejt.toaquimobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TelaCadastroEstabelecimento extends AppCompatActivity implements Runnable, GridView.OnClickListener{


    private AlertDialog alerta;
    private Spinner categoria;
    private Button button;
    private Button buttonImg;
    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private GoogleMap googleMap;
    private LatLng latLng;
    private MarkerOptions markerOpts;
    private Marker marker;
    private double lat;
    private double lgn;
    private String endereco;
    private EditText edtNome;
    private EditText edtDesc;
    private EditText edtEmail;
    private EditText edtTel;
    private static String cartao = "Indisponivel";
    private static String cat;
    private EditText edtCel;
    private String diretorio = "C:\\Users\\Eric\\Dropbox\\Projetos\\WEB\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\ToAqui\\imgEstabelecimento";
    public static final int CODIGO_IMAGEM = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_estabelecimento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Geocoder coder = new Geocoder(this);
        List<Address> addresses = null;

        try{
            addresses = coder.getFromLocationName("Caruaru",1);
        }catch (Exception e){
            e.printStackTrace();
        }

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_cadastro)).getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Address location = addresses.get(0);
        location.getLatitude();
        location.getLongitude();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
        markerOpts = new MarkerOptions();

     //   ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_item);
     //   categoria = (Spinner) findViewById(R.id.spinnerCategoria);
     //   categoria.setAdapter(adapter);

        edtNome = (EditText) findViewById(R.id.editTextNomeCadastroEstab);
        edtDesc = (EditText) findViewById(R.id.editTextDescCadastroEstab);
        edtEmail = (EditText) findViewById(R.id.editTextEmailCadastroEstab);
        edtTel = (EditText) findViewById(R.id.editTextTelCadastroEstab);
        edtCel = (EditText) findViewById(R.id.editTextCelCadastroEstab);
        button = (Button) findViewById(R.id.buttonCadastroEstab);
        button.setOnClickListener(this);
        buttonImg = (Button) findViewById(R.id.buttonImagem);
        buttonImg.setOnClickListener(this);

        edtCel.addTextChangedListener(Mask.insert("(##)####-####", edtCel));
        edtTel.addTextChangedListener(Mask.insert("(##)####-####", edtTel));


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Geocoder geocoder;
                List<Address> addresses = null;
                geocoder = new Geocoder(TelaCadastroEstabelecimento.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                endereco = addresses.get(0).getAddressLine(0);
                String cidade = addresses.get(0).getLocality();
                String estado = addresses.get(0).getAdminArea();
                String cep = addresses.get(0).getPostalCode();
                String pais = addresses.get(0).getCountryName();


                lat = latLng.latitude;
                lgn = latLng.longitude;
                Toast.makeText(TelaCadastroEstabelecimento.this, "Endereco : " + endereco, Toast.LENGTH_SHORT).show();


                markerOpts.position(new LatLng(lat,
                        lgn)).title("Estabeleciemento").icon(BitmapDescriptorFactory.fromResource(R.drawable.marcadormapa));

                googleMap.clear();
                marker = googleMap.addMarker(markerOpts);
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //chamo um método para melhor organização.
                updateUI(msg);
            }
        };

        categoria_estabelecimento();

    }

    private void updateUI(Message msg) {
        if (msg.what == 1) {
            //Converto o object para string (pois foi o que eu passei)
            String texto = (String) msg.obj;
            //defino no meu TextView o texto.

        } else if (msg.what == 2) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("#ToAqui");
            alertDialog.setMessage("Campos Obrigatorios Nulos ou Invalidos !");
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

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_sim:
                if (checked)
                    cartao="Disponivel";
                Toast.makeText(getApplicationContext(), "Seu Estabelecimento Aceita Cartões de Credito!", Toast.LENGTH_LONG).show();

                break;
            case R.id.radio_nao:
                if (checked)
                    cartao="Indisponivel";
                Toast.makeText(getApplicationContext(), "Seu Estabelecimento Não Aceita Cartões de Credito!", Toast.LENGTH_LONG).show();

                break;
        }
    }

    @Override
    public void run() {


     //   String cat = categoria.getOnItemSelectedListener().toString();


        WebService ws = new WebService();

        try{
            String nome = edtNome.getText().toString();
            String tel = edtTel.getText().toString();
            String cel = edtCel.getText().toString();
            String email2 = edtEmail.getText().toString();
            String desc = edtDesc.getText().toString();

            if(nome.equals("") || lgn==0 || cat.equals("")){
                Message message = new Message();
                //defino um codigo para controle.
                message.what = 2;

                message.obj = "Email/Senha Nulos ou Invalidos !";
                //Envio da mensagem.
                handler.sendMessage(message);
                Thread t = new Thread(this);
                t.stop();
                //ou faz um else assim o cod fica menor
            }

            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            String email = sharedPreferences.getString("email_user", null);

            Usuario user = ws.usuarioProcurarEmail(email);

            Estabelecimento estab = new Estabelecimento();
            Endereco end = new Endereco();
            Contato contato = new Contato();

            end.setLongitude(String.valueOf(lgn));
            end.setLatitude(String.valueOf(lat));
            end.setEndereco(endereco);

            contato.setEmail(email2);
            contato.setTelefone(tel.replaceAll("-", "").replaceAll("()", "").replaceAll(" ", "").replace("(", "").replace(")", ""));
            contato.setCelular(cel.replaceAll("-", "").replaceAll("()", "").replaceAll(" ", "").replace("(", "").replace(")", ""));
            estab.setNome(nome);
            estab.setDescricao(desc);
            estab.setCategoria(cat);
            estab.setEndereco(end);
            estab.setContato(contato);
            estab.setPagamento_outro(cartao);
                estab.setPagamento_hiper(cartao);
                estab.setPagamento_visa(cartao);
                estab.setPagamento_master(cartao);
                estab.setPagamento_cielo(cartao);

            estab.setId_user(user.getId_user());
            estab.setUser(user);

            ws.estabelecimentoCadastrar(estab);

            finish();
        }catch (Exception e){
            Message message = new Message();
            //defino um codigo para controle.
            message.what = 2;

            message.obj = "Email/Senha Nulos ou Invalidos !";
            //Envio da mensagem.
            handler.sendMessage(message);
        }finally {
            dialog.dismiss();
       //     dialog = null;
       //     ws = null;
            //CASO DE BOZO APAGAR
         //   handler = null;
         //   dialog = null;
         //   ws = null;
         //   nome = null;
         //   desc = null;
         //   tel = null;
         //   cel = null;
         //   email2 = null;
         //   edtEmail = null;
         //   edtNome = null;
         //   edtTel = null;
         //   edtCel = null;
         //   edtDesc = null;


           // finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCadastroEstab:
            dialog = new ProgressDialog(this);
            dialog.setMessage("Cadastrando Estabelecimento...");
            dialog.setTitle("#ToAqui");
            dialog.show();

            Thread t = new Thread(this);
            t.start();
                break;
            case R.id.buttonImagem:
               Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CODIGO_IMAGEM);

                File criaDir = new File(diretorio);
                boolean b = criaDir.mkdir();
                if(b || criaDir.exists()){
                    Toast.makeText(getApplicationContext(), "Ok Criou ou já existe", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Não Criou", Toast.LENGTH_LONG).show();
                }
                break;


        }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODIGO_IMAGEM) {
            //Ok
            if (resultCode == RESULT_OK) {
                // Aqui pego a imagem
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                // Seta ela no ImaView do Layout

                try {
                    SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyy_hh-MM-ss");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);


                    byte[] bytes = stream.toByteArray();
                    String nomeArquivo = diretorio + "ESTAB_" + formatoData.format(new Date()) + ".jpg";

                    FileOutputStream fos = new FileOutputStream(nomeArquivo);
                    fos.write(bytes);
                    fos.close();
                    Toast.makeText(getApplicationContext(), "Salvou foto", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Aqui posso salvar a foto se quizer.
            } else if (resultCode == RESULT_CANCELED) {//Cancelou a foto
                Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show();
            } else { //Saiu da Intent
                Toast.makeText(this, "Saiu", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void categoria_estabelecimento() {
        //Lista de itens
        ArrayList<String> itens = new ArrayList<String>();
        itens.add("BAR");
        itens.add("RESTAURANTE");
        itens.add("HOTEL");

        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_lista_categoria, itens);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tipo do Estabelecimento");
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                //   Toast.makeText(MainActivity.this, "CATEGORIA ESCOLHIDA = " + arg1, Toast.LENGTH_SHORT).show();

                alerta.dismiss();

                switch (arg1) {
                    case 0:
                        //COLOCAR AQUI PARA SETAR O ATRIBUTO

                        Toast.makeText(getApplicationContext(), "BAR", Toast.LENGTH_LONG).show();
                        cat = "Bar";
                        break;
                    case 1:
                        //COLOCAR AQUI PARA SETAR O ATRIBUTO

                        Toast.makeText(getApplicationContext(), "RESTAURANTE", Toast.LENGTH_LONG).show();
                        cat = "Restaurante";
                        break;
                    case 2:
                        //COLOCAR AQUI PARA SETAR O ATRIBUTO

                        cat = "Hotel";
                        Toast.makeText(getApplicationContext(), "HOTEL", Toast.LENGTH_LONG).show();
                        break;


                }

            }
        });

        alerta = builder.create();
        alerta.show();
    }
}
