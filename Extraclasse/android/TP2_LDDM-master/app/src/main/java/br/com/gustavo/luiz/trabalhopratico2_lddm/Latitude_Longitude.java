package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import com.facebook.FacebookSdk;

public class Latitude_Longitude extends AppCompatActivity
{
    private TextView latitudeText;               // Texto latitude
    private TextView longitudeText;              // Texto longitude
    private FloatingActionButton floatingButton; // botao para ver a latitude e longitude
    private FloatingActionButton flFacebook;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onResume( )
    {
        super.onResume();

        if(broadcastReceiver == null)
        {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                    latitudeText.setText("" + intent.getExtras().get("lat"));
                    longitudeText.setText("" + intent.getExtras().get("lon"));
                }// end onReceive( )
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }// end onResume( )

    @Override
    protected void onDestroy( )
    {
        super.onDestroy( );

        if(broadcastReceiver != null)
        {
            unregisterReceiver(broadcastReceiver);
        }// end if
    }// end onDestroy( )

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latitude_longitude);

        // definir dados
        latitudeText  = (TextView) findViewById(R.id.latitudeTexto);
        longitudeText = (TextView) findViewById(R.id.longitudeTexto);
        floatingButton = (FloatingActionButton) findViewById(R.id.fab);
        flFacebook = (FloatingActionButton) findViewById(R.id.facebook);

        if(!runtime_permissions( ))
        {
            enable_buttons( );
        }// end if
    }// end onCreate( )

    private void enable_buttons( )
    {
        floatingButton.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GPS_Service.class);
                startService(i);
            }// end onClick( )
        });
    }// end enable_buttons( )

    private boolean runtime_permissions( )
    {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
           ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }// end if

        return false;
    }// end runtime_permissions( )

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                enable_buttons( );
            }
            else
            {
                runtime_permissions( );
            }// end if
        }// end if
    }// end onRequestPermissionsResult( )

    public void facebook(View view)
    {
        Intent intent = new Intent("android.intent.category.LAUNCHER");
        intent.setClassName("com.facebook.katana", "com.facebook.katana.LoginActivity");
        startActivity(intent);
    }// end facebook
}// end class
