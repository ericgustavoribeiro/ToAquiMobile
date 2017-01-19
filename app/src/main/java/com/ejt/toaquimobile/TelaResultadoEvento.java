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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class TelaResultadoEvento extends AppCompatActivity implements Runnable, GridView.OnClickListener{

    private Button button;
    private TextView txtNome;
    private TextView txtLocal;
    private TextView txtData;
    private TextView txtValor;
    private static GoogleMap googleMap;
    private static LatLng latLng;
    private static MarkerOptions markerOpts;
    private static Marker marker;
    private ImageView img;
    int id_evento;
    private Handler handler = new Handler();
    private ProgressDialog dialog;
    private static int id_user;
    private static double latitude;
    private static double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_resultado_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_evento)).getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        markerOpts = new MarkerOptions();

        txtNome = (TextView) findViewById(R.id.txtNomeEventoResul);
        txtLocal = (TextView) findViewById(R.id.txtLocalEventoResul);
        txtData = (TextView) findViewById(R.id.txtDataEventoResul);
        txtValor = (TextView) findViewById(R.id.txtValorEventoResul);
        img = (ImageView) findViewById(R.id.imgEventoResul);
        button = (Button) findViewById(R.id.buttonTelaAlterarEvento);
        button.setOnClickListener(this);

        button.setVisibility(View.INVISIBLE);

        Intent it = getIntent();
        Bundle b = it.getExtras();
        id_evento = b.getInt("id_evento");

        atualizarmaps();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Carregando Evento...");
        dialog.setTitle("#ToAqui");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void onClick(View v) {
        Intent it= new Intent(this, TelaRemoverEvento.class);
        Bundle b = new Bundle();
        b.putInt("id_evento", id_evento);
        it.putExtras(b);
        startActivity(it);
        finish();
    }

    @Override
    public void run() {

        WebService ws = new WebService();

        try{
            Evento evento = ws.eventoProcurarId(id_evento);

            txtNome.setText(evento.getNome());
            txtLocal.setText(evento.getLocal());
            txtData.setText(evento.getData_evento());
            txtValor.setText(String.valueOf(evento.getValor_ingresso()));
            String URL = "http://192.168.1.120:6090/ToAqui/";

            SharedPreferences sharedPreferences = getSharedPreferences("configuracore", MODE_PRIVATE);
            id_user = sharedPreferences.getInt("id_user", 0);

            if(id_user == evento.getId_user()){
                button.setVisibility(View.VISIBLE);
            }

            if(evento.getImagem().equals("")){
                Picasso.with(this).load(R.drawable.semimg).into(img);
            }else{
                Picasso.with(this).load(URL.concat(evento.getImagem())).into(img);
            }

            latitude = Double.parseDouble(evento.getEndereco().getLatitude());
            longitude = Double.parseDouble(evento.getEndereco().getLongitude());

            markerOpts.position(new LatLng(latitude,
                    longitude)).title("Evento");
            marker = googleMap.addMarker(markerOpts);

        }catch (Exception e){
            e.getMessage();
        }finally {
            dialog.dismiss();
            ws = null;

            button = null;
            txtNome = null;
            txtLocal = null;
            txtData = null;
            txtValor = null;
            googleMap = null;
            latLng = null;
            markerOpts = null;
            marker = null;
            img = null;
       //     id_evento = 0;
            handler = null;
            dialog = null;
      //      id_user = 0;
            latitude = 0;
            longitude = 0;
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
