package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    // definir dados
    private TextView dia_noite;                  // Texto da tela
    private SensorManager sensorManager;         // Gerenciador dos sensores
    private Sensor sensorLuz;                    // Sensor de luz
    private View view;                           // Tela
    private ImageView image;                     // Imagem do sol ou da lua

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setando o toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // definindo os textviews
        dia_noite     = (TextView) findViewById(R.id.dia_noite);

        // definindo a view
        view = findViewById(R.id.view);

        // definindo a imageView
        image = (ImageView) findViewById(R.id.image);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // criando o sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        /*  Sensor de Luz */

        // criando sensor de luz
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // registrando o sensor
        sensorManager.registerListener(new LuzSensor( ), sensorLuz, SensorManager.SENSOR_DELAY_UI);
    }// end onCreate( )

    /**
     * Metodo onPause( ).
     */
    @Override
    public void onPause( )
    {
        super.onPause( );
        sensorManager.unregisterListener(new LuzSensor( ));
    }// end onPause( )

    /**
     * Metodo onResume( ).
     */
    @Override
    public void onResume( )
    {
        super.onResume( );
    }// end onResume( )

    /**
     * Metodo bomDia( ) - escreve na tela "Bom Dia!" e muda a cor da tela.
     */
    public void bomDia( )
    {
        // colocando texto
        dia_noite.setText(R.string.bomDia);

        // mudando a cor do texto
        dia_noite.setTextColor(getResources().getColor(R.color.white));

        // mudando a cor do fundo da tela
        view.setBackgroundColor(getResources().getColor(R.color.sky));

        // mudando imagem
        image.setImageResource(R.drawable.sunset);
    }// end bomDia( )

    /**
     * Metodo boaNoite( ) - escreve na tela "Boa Noite!" e muda a cor da tela.
     */
    public void boaNoite( )
    {
        // colocando texto
        dia_noite.setText(R.string.boaNoite);

        // mudando a cor do texto
        dia_noite.setTextColor(getResources().getColor(R.color.white));

        // mudando a cor do fundo da tela
        view.setBackgroundColor(getResources().getColor(R.color.midnight));

        // mudando imagem
        image.setImageResource(R.drawable.night);
    }// end boaNoite( )

    /**
     * Classe para o sensor de Luz.
     */
    class LuzSensor implements SensorEventListener
    {
        public void onAccuracyChanged(Sensor sensor, int accuracy){}// end onAccuracyChanged( )

        public void onSensorChanged(SensorEvent event)
        {
            // definir dados
            float vl = event.values[0];

            // valor do sensor de luz menor que 10
            if(vl < 10)
            {
                // mostra boa noite
                boaNoite( );
            }
            else
            {
                // mostra bom dia
                bomDia( );
            }// end if
        }// end onSensorChanged( )
    }// end class LuzSensor

    @Override
    public void onBackPressed( )
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed( );
        }// end if
    }// end onBackPressed( )

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }// end onCreateOptionsMenu( )

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       return super.onOptionsItemSelected(item);
    }// end onOptionsItemSelected( )

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_camera) {

            // vai pra tela da camera
            chamaCamera( );

        } else if(id == R.id.nav_mapa) {

            // vai pra tela do mapa
            telaMapa( );

        } else if(id == R.id.nav_sobre) {

            // mostra uma tela explicando como o aplicativo funciona
            sobre( );

        }else if(id == R.id.nav_lat_long) {

            // chama a tela de lat/long
            latLong( );
        }else if(id == R.id.nav_db){
            db( );
        }// end if

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }// end onNavigationItemSelected( )

    public void db( )
    {
        Intent intent = new Intent(this, BancoDeDados.class);

        startActivity(intent);
    }// end db( )

    public void telaMapa( )
    {
        // criando intent
        Intent intent = new Intent(this, MapsActivity.class);

        // chamando a tela do mapa
        startActivity(intent);
    }// end telaMapa( )

    public void chamaCamera( )
    {
        // criando intent
        Intent intent = new Intent(this, CameraActivity.class);

        // chamando a tela da camera
        startActivity(intent);
    }// end chamaCamera( )

    public void latLong( )
    {
        // criando intent
        Intent intent = new Intent(this, Latitude_Longitude.class);

        // chamando a tela da lat/long
        startActivity(intent);
    }// end latLong( )

    public void sobre( )
    {
        // criando intent
        Intent intent = new Intent(this, Sobre.class);

        // chamando a tela sobre
        startActivity(intent);
    }// end sobre( )
}// end class
