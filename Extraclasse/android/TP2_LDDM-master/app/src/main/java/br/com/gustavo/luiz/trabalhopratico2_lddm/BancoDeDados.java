package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends AppCompatActivity
{
    private DbHelper db;
    ArrayList<Coordenadas> listaCoordenadas;

//    public ArrayList<Coordenadas> imageArray = new ArrayList<>();
//    public ListView dataList;
//    public CoordImageAdapter imageAdapter;
//    //public DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco_de_dados);

//        dataList = (ListView) findViewById(R.id.listView);

        db = new DbHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart( )
    {
        super.onStart();

        ListView lista = (ListView) findViewById(R.id.listView);
        listaCoordenadas = db.getAllCoordenadas();
        CoordAdapter adapter = new CoordAdapter(this, listaCoordenadas);
        lista.setAdapter(adapter);
    }
}
