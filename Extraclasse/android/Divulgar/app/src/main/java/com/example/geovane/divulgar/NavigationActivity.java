package com.example.geovane.divulgar;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.geovane.divulgar.Adapter.MyExpandableListAdapter;
import com.example.geovane.divulgar.Item.MyMenuItem;
import com.example.geovane.divulgar.Model.Curso;
import com.example.geovane.divulgar.Model.Materia;
import com.example.geovane.divulgar.Model.Periodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavigationActivity extends AppCompatActivity {

    public Curso curso;

    private Menu mOptionsMenu;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mExpandableListView;
    private List<String> mExpandableListTitle;
    private Map<String, List<Object[]>> mExpandableListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mExpandableListView = (ExpandableListView) findViewById(R.id.nav_view);

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header_navigation, null, false);
        mExpandableListView.addHeaderView(listHeaderView);

        curso = populateCurso();

        setTextCurso(curso);

        mExpandableListData = MyMenuItem.getMenuItem(curso, this);
        mExpandableListTitle = new ArrayList<>(mExpandableListData.keySet());

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mOptionsMenu = menu;

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void addDrawerItems() {
        ExpandableListAdapter mExpandableListAdapter = new MyExpandableListAdapter(this,
                mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    public static Curso populateCurso(){
        Curso curso = new Curso("Ciência da Computação");

        Periodo primeiro = new Periodo(1);
        Periodo segundo = new Periodo(2);
        Periodo terceiro = new Periodo(3);
        Periodo quarto = new Periodo(4);
        Periodo quinto = new Periodo(5);
        Periodo sexto = new Periodo(6);
        Periodo setimo = new Periodo(7);
        Periodo oitavo = new Periodo(8);

        //primeiro
        Materia m1_1 = new Materia("AED I");
        Materia m1_2 = new Materia("Cálculo I");
        Materia m1_3 = new Materia("CS");
        Materia m1_4 = new Materia("ICC");
        Materia m1_5 = new Materia("LPV");
        Materia m1_6 = new Materia("Seminários I");

        //segundo
        Materia m2_1 = new Materia("AED II");
        Materia m2_2 = new Materia("Cálculo II");
        Materia m2_3 = new Materia("GA");
        Materia m2_4 = new Materia("AC I");
        Materia m2_5 = new Materia("CR I");
        Materia m2_6 = new Materia("Seminários II");

        //terceiro
        Materia m3_1 = new Materia("IPCC");
        Materia m3_2 = new Materia("Empreendedorismo");
        Materia m3_3 = new Materia("Cálculo III");
        Materia m3_4 = new Materia("AL");
        Materia m3_5 = new Materia("AED III");
        Materia m3_6 = new Materia("AC II");
        Materia m3_7 = new Materia("Lab. AC II");

        //quarto
        Materia m4_1 = new Materia("ES I");
        Materia m4_2 = new Materia("AG");
        Materia m4_3 = new Materia("BD");
        Materia m4_4 = new Materia("LDDM");
        Materia m4_5 = new Materia("EP");
        Materia m4_6 = new Materia("AC III");

        //quinto
        Materia m5_1 = new Materia("FTC");
        Materia m5_2 = new Materia("PAA");
        Materia m5_3 = new Materia("ES II");
        Materia m5_4 = new Materia("SO");
        Materia m5_5 = new Materia("LP");
        Materia m5_6 = new Materia("CR II");
        Materia m5_7 = new Materia("LPA");

        //sexto
        Materia m6_1 = new Materia("PID");
        Materia m6_2 = new Materia("ES III");
        Materia m6_3 = new Materia("LPS");
        Materia m6_4 = new Materia("Redes I");
        Materia m6_5 = new Materia("Filosofia I");
        Materia m6_6 = new Materia("CP");
        Materia m6_7 = new Materia("CG");

        //setimo
        Materia m7_1 = new Materia("RI");
        Materia m7_2 = new Materia("IA");
        Materia m7_3 = new Materia("Redes II");
        Materia m7_4 = new Materia("Compiladores");
        Materia m7_5 = new Materia("Tópicos II");
        Materia m7_6 = new Materia("OS");
        Materia m7_7 = new Materia("LRSO");
        Materia m7_8 = new Materia("TCC I");

        //oitavo
        Materia m8_1 = new Materia("Tópicos III");
        Materia m8_2 = new Materia("Modelagem");
        Materia m8_3 = new Materia("SAS");
        Materia m8_4 = new Materia("CD");
        Materia m8_5 = new Materia("Filosofia II");
        Materia m8_6 = new Materia("Seminários IV");
        Materia m8_7 = new Materia("TCC II");

        primeiro.addMateria(m1_1);
        primeiro.addMateria(m1_2);
        primeiro.addMateria(m1_3);
        primeiro.addMateria(m1_4);
        primeiro.addMateria(m1_5);
        primeiro.addMateria(m1_6);

        segundo.addMateria(m2_1);
        segundo.addMateria(m2_2);
        segundo.addMateria(m2_3);
        segundo.addMateria(m2_4);
        segundo.addMateria(m2_5);
        segundo.addMateria(m2_6);

        terceiro.addMateria(m3_1);
        terceiro.addMateria(m3_2);
        terceiro.addMateria(m3_3);
        terceiro.addMateria(m3_4);
        terceiro.addMateria(m3_5);
        terceiro.addMateria(m3_6);
        terceiro.addMateria(m3_7);

        quarto.addMateria(m4_1);
        quarto.addMateria(m4_2);
        quarto.addMateria(m4_3);
        quarto.addMateria(m4_4);
        quarto.addMateria(m4_5);
        quarto.addMateria(m4_6);

        quinto.addMateria(m5_1);
        quinto.addMateria(m5_2);
        quinto.addMateria(m5_3);
        quinto.addMateria(m5_4);
        quinto.addMateria(m5_5);
        quinto.addMateria(m5_6);
        quinto.addMateria(m5_7);

        sexto.addMateria(m6_1);
        sexto.addMateria(m6_2);
        sexto.addMateria(m6_3);
        sexto.addMateria(m6_4);
        sexto.addMateria(m6_5);
        sexto.addMateria(m6_6);
        sexto.addMateria(m6_7);

        setimo.addMateria(m7_1);
        setimo.addMateria(m7_2);
        setimo.addMateria(m7_3);
        setimo.addMateria(m7_4);
        setimo.addMateria(m7_5);
        setimo.addMateria(m7_6);
        setimo.addMateria(m7_7);
        setimo.addMateria(m7_8);

        oitavo.addMateria(m8_1);
        oitavo.addMateria(m8_2);
        oitavo.addMateria(m8_3);
        oitavo.addMateria(m8_4);
        oitavo.addMateria(m8_5);
        oitavo.addMateria(m8_6);
        oitavo.addMateria(m8_7);

        curso.addPeriodo(primeiro);
        curso.addPeriodo(segundo);
        curso.addPeriodo(terceiro);
        curso.addPeriodo(quarto);
        curso.addPeriodo(quinto);
        curso.addPeriodo(sexto);
        curso.addPeriodo(setimo);
        curso.addPeriodo(oitavo);

        return curso;
    }

    public void setTextCurso(Curso curso) {
        TextView tv_curso = (TextView) findViewById(R.id.curso);
        tv_curso.append(curso.getNomeCurso());
    }
}
