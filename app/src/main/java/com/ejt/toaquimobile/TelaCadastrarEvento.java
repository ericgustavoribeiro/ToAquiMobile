package com.ejt.toaquimobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class TelaCadastrarEvento extends AppCompatActivity implements Runnable, GridView.OnClickListener{

    private Button button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar_evento);
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

        edtNome = (EditText) findViewById(R.id.editTextNomeCadastroEvento);
        edtLocal = (EditText) findViewById(R.id.editTextLocalCadastroEvento);
        edtEmail = (EditText) findViewById(R.id.editTextEmailCadastroEvento);
        edtTel = (EditText) findViewById(R.id.editTextTelCadastroEvento);
        edtCel = (EditText) findViewById(R.id.editTextCelCadastroEvento);
        edtDesc = (EditText) findViewById(R.id.editTextDescCadastroEvento);
        edtData = (EditText) findViewById(R.id.editTextDataCadastroEvento);
        edtValor = (EditText) findViewById(R.id.editTextValorCadastroEvento);
        button = (Button) findViewById(R.id.buttonCadastroEvento);
        button.setOnClickListener(this);
        edtCel.addTextChangedListener(Mask.insert("(##)####-####", edtCel));
        edtTel.addTextChangedListener(Mask.insert("(##)####-####", edtTel));
        edtData.addTextChangedListener(Mask.insert("##/##/####", edtData));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Geocoder geocoder;
                List<Address> addresses = null;
                geocoder = new Geocoder(TelaCadastrarEvento.this, Locale.getDefault());

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
                Toast.makeText(TelaCadastrarEvento.this, "Endereco : " + endereco, Toast.LENGTH_SHORT).show();

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
        dialog = new ProgressDialog(this);
        dialog.setMessage("Cadastrando Evento...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();
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

            if(nome.equals("") && lat==0){
                Message message = new Message();
                //defino um codigo para controle.
                message.what = 2;

                message.obj = "Email/Senha Nulos ou Invalidos !";
                //Envio da mensagem.
                handler.sendMessage(message);
                Thread t = new Thread(this);
                t.stop();
            }

            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            String email = sharedPreferences.getString("email_user", null);

            Usuario user = ws.usuarioProcurarEmail(email);

            Evento evento = new Evento();
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
            evento.setId_user(user.getId_user());
            // evento.setUser(user);

            ws.eventoCadastrar(evento);

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
          //  handler = null;
            dialog = null;
            ws = null;
          //  REMOVIR PRA TESTAR
       //     nome = null;
       //     desc = null;
       //     tel = null;
       //     cel = null;
       //     email2 = null;
         //   edtEmail = null;
         //   edtNome = null;
         //   edtTel = null;
         //   edtCel = null;
         //   edtDesc = null;
         //   edtData = null;
         //   edtLocal = null;
         //   edtValor = null;

        }

    }
}
