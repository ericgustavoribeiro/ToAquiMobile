package com.ejt.toaquimobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class TelaRemoverEvento extends AppCompatActivity implements Runnable, GridView.OnClickListener{

    private Button button;
    private Button buttonRemover;
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
    private EditText edtLocal;
    private EditText edtDesc;
    private EditText edtEmail;
    private EditText edtTel;
    private EditText edtCel;
    private EditText edtData;
    private EditText edtValor;
    private static int id_evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_remover_evento);
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

        Intent it = getIntent();
        Bundle b = it.getExtras();
        id_evento = b.getInt("id_evento");
        Log.e("Teste", "id : " +id_evento);

        edtNome = (EditText) findViewById(R.id.editTextNomeAlterarEvento);
        edtLocal = (EditText) findViewById(R.id.editTextLocalAlterarEvento);
        edtEmail = (EditText) findViewById(R.id.editTextEmailAlterarEvento);
        edtTel = (EditText) findViewById(R.id.editTextTelAlterarEvento);
        edtCel = (EditText) findViewById(R.id.editTextCelAlterarEvento);
        edtDesc = (EditText) findViewById(R.id.editTextDescAlterarEvento);
        edtData = (EditText) findViewById(R.id.editTextDataAlterarEvento);
        edtValor = (EditText) findViewById(R.id.editTextValorAlterarEvento);
        button = (Button) findViewById(R.id.buttonAlterarEvento);
        button.setOnClickListener(this);
        buttonRemover = (Button) findViewById(R.id.buttonRemoverEvento);
        buttonRemover.setOnClickListener(this);
        edtCel.addTextChangedListener(Mask.insert("(##)####-####", edtCel));
        edtTel.addTextChangedListener(Mask.insert("(##)####-####", edtTel));
        edtData.addTextChangedListener(Mask.insert("##/##/####", edtData));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Geocoder geocoder;
                List<Address> addresses = null;
                geocoder = new Geocoder(TelaRemoverEvento.this, Locale.getDefault());

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
                Toast.makeText(TelaRemoverEvento.this, "Endereco : " + endereco, Toast.LENGTH_SHORT).show();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAlterarEvento:

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //botão SIM clicado
                                //    dialog = new ProgressDialog(TelaRemoverEstabelecimento.this);
                                //  dialog.setMessage("Alterando Estabelecimento...");
                                //  dialog.setTitle("#ToAqui");
                                //   dialog.show();

                                Thread t = new Thread(TelaRemoverEvento.this);
                                t.start();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                finish();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Deseja Alterar o Evento?").setPositiveButton("SIM", dialogClickListener)
                        .setNegativeButton("NÃO", dialogClickListener).show();

                dialog = new ProgressDialog(this);
                dialog.setMessage("Alterando Evento...");
                dialog.setTitle("#ToAqui");
                dialog.show();

               // Thread t = new Thread(this);
               // t.start();
                break;

            case R.id.buttonRemoverEvento:

                DialogInterface.OnClickListener dialogClickListener2 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent it= new Intent(TelaRemoverEvento.this, RemoverEventoActivity.class);
                                Bundle b = new Bundle();
                                b.putInt("id_evento", id_evento);
                                it.putExtras(b);
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
                builder2.setMessage("Deseja Excluir o Evento?").setPositiveButton("SIM", dialogClickListener2)
                        .setNegativeButton("NÃO", dialogClickListener2).show();


                break;
        }
    }

    @Override
    public void run() {



        WebService ws = new WebService();
        try{

            String nome = edtNome.getText().toString();
            String tel = edtTel.getText().toString();
            String cel = edtCel.getText().toString();
            String email2 = edtEmail.getText().toString();
            String desc = edtDesc.getText().toString();
            String local = edtLocal.getText().toString();
            String data = edtData.getText().toString();
            Float valor = Float.parseFloat(edtValor.getText().toString());

            Evento evento = ws.eventoProcurarId(id_evento);
            Endereco end = new Endereco();
            Contato contato = new Contato();

            end.setLongitude(String.valueOf(lgn));
            end.setLatitude(String.valueOf(lat));
            end.setEndereco(endereco);

            contato.setEmail(email2);
            contato.setTelefone(tel.replaceAll("-", "").replaceAll("()", "").replaceAll(" ", "").replace("(", "").replace(")", ""));
            contato.setCelular(cel.replaceAll("-", "").replaceAll("()", "").replaceAll(" ", "").replace("(", "").replace(")", ""));


            evento.setNome(nome);
            evento.setLocal(local);
            evento.setData_evento(data);
            evento.setDescricao(desc);
            evento.setValor_ingresso(valor);
            evento.setEndereco(end);
            evento.setContato(contato);
            ws.eventoAtualizar(evento);

            finish();
        }catch (Exception e){
            Log.e("Teste", e.getMessage());
            Message message = new Message();
            message.what = 2;
            message.obj = "Email/Senha Nulos ou Invalidos !";
            handler.sendMessage(message);
        }finally {
            dialog.dismiss();
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


        }
    }
}
