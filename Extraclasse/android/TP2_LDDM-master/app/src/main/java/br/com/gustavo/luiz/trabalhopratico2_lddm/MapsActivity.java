package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.DialogPreference;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener
{
    // definir dados
    private int i = 1;
    private double lat;
    private double lon;
    private AlertDialog alerta;
    private SensorManager sm;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private static final String TAG = "MapsActivity";
    private final int TIRAR_FOTO = 123;
    private float acelVal;  // valor atual da aceleracao e gravidade
    private float acelLast; // ultimo valor da aceleracao e gravidade
    private float shake;    // valor da aceleracao diferente da gravidade
    private DbHelper dbHelper;
    //private BDController bancoDeDados;
    public Bitmap imageBitmap;
    public byte imageInByte[];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        dbHelper = new DbHelper(this);

        // Banco de dados
//        bancoDeDados = new BDController(this);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.0f;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }// end onCreate( )

    @Override
    public void onResume( )
    {
        super.onResume();

        // ativa GPS
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }// end onResume( )

    @Override
    public void onPause( )
    {
        super.onPause();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(this);
    }// end onPause( )

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            mMap = googleMap;

            // adiciona o botao de zoom
            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setMyLocationEnabled(true);

        } catch(SecurityException ex){
            Log.e(TAG, "Error", ex);
        }// end try catch
    }// end onMapReady( )

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            float x = event.values[0];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double)(x*x));

            float delta = acelVal - acelLast;

            shake = shake * 0.9f + delta;

            if(shake > 12)
            {
                marcaPosicao( );
            }// end if
        }// end onSensorChanged( )

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void marcaPosicao( )
    {
        // pega a posicao
        LatLng minhaPosicao = new LatLng(lat, lon);

        // marca no mapa
        mMap.addMarker(new MarkerOptions().position(minhaPosicao).title(i + " Localização"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(minhaPosicao));

        i++;

        // chama o alertDialog pra ver se a pessoa quer tirar foto
        dialog( );
    }// end marcaPosicao( )

    public void dialog( )
    {
        // cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // define o titulo
        builder.setTitle("Tirar Foto");

        // define a mensagem
        builder.setMessage("Deseja Tirar uma foto?");

        // define um botao positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1)
            {
                // abre a camera
                chamaCamera( );
                salvaBDSemFoto();
                arg0.cancel( );
            }// end onClick( )
        });

        // define um botao negativo
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1)
            {
                salvaBDSemFoto( );
                arg0.cancel( );
            }// end onClick( )
        });

        // cria o AlertDialog
        alerta = builder.create();

        // exibe
        alerta.show( );
    }// end chamaCamera( )

    public void chamaCamera( )
    {
        // cria intent para chama camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager( )) != null)
        {
            // chama camera
            startActivityForResult(intent, TIRAR_FOTO);
        }// end if
    }// end chamaCamera( )

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TIRAR_FOTO && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();

            if (extras != null)
            {
                imageBitmap = extras.getParcelable("data");

                // convert bitmap to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imageInByte = stream.toByteArray();

                // Inserting Contacts
                //bd.addCoordenadas(new Coordenadas("" + lat, "" + lon, imageInByte));
//                Intent i = new Intent(MapsActivity.this, BancoDeDados.class);
//                startActivity(i);
//                finish();
            }// end if
        }// end if
    }// end onActivityResult( )

    public void salvaBDComFoto( )
    {
        Coordenadas coordenadas = new Coordenadas( );

        coordenadas.setLatitude("" + lat);
        coordenadas.setLongitude("" + lon);

        dbHelper.addCoordenadas(coordenadas);

        //bd.addCoordenadas(coordenadas);
        //bancoDeDados.insereDado(lat, lon, "");

        Toast.makeText(getApplicationContext(), "Coordenadas e Foto inseridas com sucesso!", Toast.LENGTH_SHORT).show();
    }// end salvaBD( )

    public void salvaBDSemFoto( )
    {
//        Coordenadas coordenadas = new Coordenadas( );
//
//        coordenadas.setLatitude("" + lat);
//        coordenadas.setLongitude("" + lon);

        //bd.addCoordenadas(coordenadas);
        //bancoDeDados.insereDado(lat, lon, "");

        Toast.makeText(getApplicationContext(), "Coordenadas inseridas com sucesso!", Toast.LENGTH_SHORT).show();
    }// end salvaBD( )

    @Override
    public void onLocationChanged(Location location)
    {
        lat = location.getLatitude();
        lon = location.getLongitude();
    }// end onLocationChanged( )

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}// end class MapsActivity
