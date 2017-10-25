package com.example.geovane.divulgar;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.geovane.divulgar.Adapter.LinklistAdapter;
import com.example.geovane.divulgar.Adapter.MyExpandableListAdapter;
import com.example.geovane.divulgar.Item.MyMenuItem;
import com.example.geovane.divulgar.Model.Curso;
import com.example.geovane.divulgar.Model.Materia;
import com.example.geovane.divulgar.Model.Periodo;
import com.example.geovane.divulgar.data.CursolistCotract;
import com.example.geovane.divulgar.data.CursolistDbHelper;
import com.example.geovane.divulgar.data.LinklistContract;
import com.example.geovane.divulgar.data.LinklistDbHelper;
import com.example.geovane.divulgar.data.MaterialistContract;
import com.example.geovane.divulgar.data.MaterialistDbHelper;
import com.example.geovane.divulgar.data.PeriodolistContract;
import com.example.geovane.divulgar.data.PeriodolistDbHelper;
import com.example.geovane.divulgar.data.TipLinklistContract;
import com.example.geovane.divulgar.data.TipLinklistDbHelper;
import com.example.geovane.divulgar.data.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavigationActivity extends AppCompatActivity {

    public Curso curso;

    private Menu mOptionsMenu;
    private SQLiteDatabase myDb;
    private LinklistAdapter mAdapter;

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

        /*RecyclerView linkListRecyclerView;
        linkListRecyclerView = (RecyclerView) this.findViewById(R.id.lista);
        linkListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new LinklistAdapter(this);

        linkListRecyclerView.setAdapter(mAdapter);*/
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

    public Curso populateCurso(){
        CursolistDbHelper cursoHelper = new CursolistDbHelper(this);
        PeriodolistDbHelper periodoHelper = new PeriodolistDbHelper(this);
        MaterialistDbHelper materiaHelper = new MaterialistDbHelper(this);
        TipLinklistDbHelper tipLinkHelper = new TipLinklistDbHelper(this);
        LinklistDbHelper linkHelper = new LinklistDbHelper(this);

        /****Curso****/
        myDb = cursoHelper.getWritableDatabase();
        Util.insertCurso(myDb);
        Cursor c_curso = getCurso();

        Curso curso;
        try {
            c_curso.moveToFirst();
            curso = new Curso(
                    c_curso.getInt(c_curso.getColumnIndex(CursolistCotract.CursolistEntry._ID)),
                    c_curso.getString(c_curso.getColumnIndex(CursolistCotract.CursolistEntry.COLUMN_CURSO_NAME))
            );

        } finally {
            c_curso.close();
        }

        /****Períodos****/
        myDb = periodoHelper.getWritableDatabase();
        Util.insertPeriodo(myDb, curso.getId());
        Cursor c_periodos = getPeriodosOfCurso(curso.getId());

        try {
            for(c_periodos.moveToFirst(); !c_periodos.isAfterLast(); c_periodos.moveToNext()) {
                Periodo periodo = new Periodo(
                        c_periodos.getInt(c_periodos.getColumnIndex(PeriodolistContract.PeriodolistEntry._ID)),
                        c_periodos.getInt(c_periodos.getColumnIndex(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM)),
                        c_periodos.getInt(c_periodos.getColumnIndex(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO))
                );
                curso.addPeriodo(periodo);
            }
        } finally {
            c_periodos.close();
        }

        /****Matérias****/
        myDb = materiaHelper.getWritableDatabase();
        Util.insertMateria(myDb, new int[]{
                curso.getPeriodos().get(0).getId(),
                curso.getPeriodos().get(1).getId(),
                curso.getPeriodos().get(2).getId(),
                curso.getPeriodos().get(3).getId(),
                curso.getPeriodos().get(4).getId(),
                curso.getPeriodos().get(5).getId(),
                curso.getPeriodos().get(6).getId(),
                curso.getPeriodos().get(7).getId()
        });
        Cursor c_materias;

        for(Periodo periodo: curso.getPeriodos()){
            c_materias = getAllMateriasOfPeriodo(periodo.getId());
            try {
                for(c_materias.moveToFirst(); !c_materias.isAfterLast(); c_materias.moveToNext()) {
                    Materia materia = new Materia(
                            c_materias.getInt(c_materias.getColumnIndex(MaterialistContract.MaterialistEntry._ID)),
                            c_materias.getString(c_materias.getColumnIndex(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME)),
                            c_materias.getInt(c_materias.getColumnIndex(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO))
                    );

                    periodo.addMateria(materia);
                }
            }catch(Exception e){
                c_materias.close();
            }
        }

        /****Tipo Links****/
        myDb = tipLinkHelper.getWritableDatabase();
        Util.insertTipLink(myDb);

        return curso;
    }

    private Cursor getCurso(){
        return myDb.query(
                CursolistCotract.CursolistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CursolistCotract.CursolistEntry.COLUMN_CURSO_NAME
        );
    }

    private Cursor getPeriodosOfCurso(int curso_id){
        return myDb.query(
                PeriodolistContract.PeriodolistEntry.TABLE_NAME,
                new String[]{
                        PeriodolistContract.PeriodolistEntry._ID,
                        PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM,
                        PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO
                },
                PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO + "=?",
                new String[]{String.valueOf(curso_id)},
                null,
                null,
                PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM
        );
    }

    private Cursor getAllMateriasOfPeriodo(int id_periodo){
        return myDb.query(
                MaterialistContract.MaterialistEntry.TABLE_NAME,
                new String[]{
                        MaterialistContract.MaterialistEntry._ID,
                        MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME,
                        MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO
                },
                MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO + "=?",
                new String[]{String.valueOf(id_periodo)},
                null,
                null,
                MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME
        );
    }

    private Cursor getAllTipLinks(){
        return myDb.query(
                TipLinklistContract.TipLinklistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                TipLinklistContract.TipLinklistEntry._ID
        );
    }

    private Cursor getAllLinks(){
        return myDb.query(
                LinklistContract.LinklistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                LinklistContract.LinklistEntry._ID
        );
    }

    public void setTextCurso(Curso curso) {
        TextView tv_curso = (TextView) findViewById(R.id.curso);
        tv_curso.append(curso.getNomeCurso());
    }
}
