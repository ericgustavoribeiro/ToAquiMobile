package com.ejt.toaquimobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TelaResultadoEstab extends AppCompatActivity implements Runnable, GridView.OnClickListener {

    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtTel;
    private TextView txtCel;
    private TextView txtEndereco;
    private TextView txtCategoria;
    private EditText editTextDesc;
    private static GoogleMap googleMap;
    private static LatLng latLng;
    private static MarkerOptions markerOpts;
    private static Marker marker;
    private Button button;
    private Button buttonAvaliar;
    private ImageView img;
    private static int id_user;
    static int id_estab;
    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private static double latitude;
    private static double longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_resultado_estab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        markerOpts = new MarkerOptions();

        txtNome = (TextView) findViewById(R.id.txtNomeResul);
        txtEmail = (TextView) findViewById(R.id.txtEmailResul);
        txtTel = (TextView) findViewById(R.id.txtTelResul);
        txtCel = (TextView) findViewById(R.id.txtCelResul);
        txtEndereco = (TextView) findViewById(R.id.txtEndResul);
        txtCategoria = (TextView) findViewById(R.id.txtCategoriaResul);
        editTextDesc = (EditText) findViewById(R.id.editTextDescResul);
        img = (ImageView) findViewById(R.id.imgEstabResul);



        button = (Button) findViewById(R.id.buttonTelaAlterar);
        button.setOnClickListener(this);

        buttonAvaliar = (Button) findViewById(R.id.buttonAvaliar);
        buttonAvaliar.setOnClickListener(this);

        button.setVisibility(View.INVISIBLE);

        atualizarmaps();

        Intent it = getIntent();
        Bundle b = it.getExtras();
        id_estab = b.getInt("id_estab");


        dialog = new ProgressDialog(this);
        dialog.setMessage("Carregando Estabelecimento...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        WebService ws = new WebService();


        try{

            Estabelecimento estab = ws.estabelecimentoProcurarId(id_estab);


            latitude = Double.parseDouble(estab.getEndereco().getLatitude());
            longitude = Double.parseDouble(estab.getEndereco().getLongitude());


            txtNome.setText(estab.getNome());
            txtCel.setText(estab.getContato().getCelular());
            txtTel.setText(estab.getContato().getTelefone());
            txtEmail.setText(estab.getContato().getEmail());
            txtEndereco.setText(estab.getEndereco().getEndereco());
            txtCategoria.setText(estab.getCategoria());
            editTextDesc.setText(estab.getDescricao());
            String URL = "http://192.168.1.120:6090/ToAqui/";

            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            id_user = sharedPreferences.getInt("id_user", 0);

            if(id_user == estab.getId_user()){
                button.setVisibility(View.VISIBLE);
            }

            Picasso.with(getApplicationContext()).load("http://192.168.1.120:6090/ToAqui/imgEstabelecimento/136_0922.JPG").into(img);




            Log.i("Teste", "Fim ");

        }catch (Exception e){
           e.getMessage();
        }finally {
            dialog.dismiss();
            ws = null;
       //     txtNome=null;
       //     txtEmail=null;
       //     txtTel=null;
       //     txtCel=null;
       //     txtEndereco=null;
       //     txtCategoria=null;
       //     editTextDesc=null;
        //    googleMap=null;
        //    latLng=null;
       //     markerOpts=null;
       //     marker=null;
       //     button=null;
       //     buttonAvaliar=null;
        //    img=null;


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAvaliar :
                Intent it = new Intent(this, TelaAvaliacaoActivity.class);
                Bundle b = new Bundle();
                b.putInt("id_estab", id_estab);
                it.putExtras(b);
                startActivity(it);
  //              Thread t = new Thread(this);
  //              t.destroy();
                finish();
                break;
            case (R.id.buttonTelaAlterar) :
                Intent intent = new Intent(this, TelaRemoverEstabelecimento.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_estab", id_estab);
                intent.putExtras(bundle);
                startActivity(intent);
//                Thread t1 = new Thread(this);
//                t1.destroy();
                finish();
                break;

        }
    }

    public static void atualizarmaps(){
        markerOpts.position(new LatLng(-8.28496389999998,
                -35.986666892187486)).title("#ToAqui").snippet("Estabelecimento").icon(BitmapDescriptorFactory.fromResource(R.drawable.marcadormapa));
        marker = googleMap.addMarker(markerOpts);
        final CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(-8.28496389999998,
                        -35.986666892187486))
                .bearing(130)
                .tilt(0)
                .zoom(17)
                .build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        googleMap.moveCamera(update);
    }
}
