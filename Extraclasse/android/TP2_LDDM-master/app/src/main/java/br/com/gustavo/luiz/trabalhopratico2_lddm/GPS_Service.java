package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by luiz on 07/05/17.
 */

public class GPS_Service extends Service
{
    // definir dados
    private LocationListener listener;
    private LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }// end onBind( )

    @Override
    public void onCreate( )
    {
        listener = new LocationListener( ) {
            @Override
            public void onLocationChanged(Location location)
            {
                Intent i = new Intent("location_update");
                i.putExtra("lat", location.getLatitude( ));
                i.putExtra("lon", location.getLongitude( ));
                sendBroadcast(i);
            }// end onLocationChanged( )

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}

            @Override
            public void onProviderEnabled(String s) {}

            @Override
            public void onProviderDisabled(String s)
            {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }// end onProviderDisabled( )
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);
    }// end onCreate( )

    @Override
    public void onDestroy( )
    {
        super.onDestroy();

        if(locationManager != null)
        {
            // noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }// end if
    }// end onDestroy( )
}// end class
