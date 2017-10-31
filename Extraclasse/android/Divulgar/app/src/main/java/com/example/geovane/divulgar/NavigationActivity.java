package com.example.geovane.divulgar;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.geovane.divulgar.Adapter.LinklistAdapter;
import com.example.geovane.divulgar.Adapter.MyExpandableListAdapter;
import com.example.geovane.divulgar.Fragment.MyDialogFragment;
import com.example.geovane.divulgar.Item.MyMenuItem;
import com.example.geovane.divulgar.Model.Curso;
import com.example.geovane.divulgar.Model.Link;
import com.example.geovane.divulgar.Model.Materia;
import com.example.geovane.divulgar.Model.Periodo;
import com.example.geovane.divulgar.Model.TipoLink;
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

public class NavigationActivity extends AppCompatActivity implements MyDialogFragment.NoticeDialogListener{

    public Curso curso;
    public ArrayList<TipoLink> tipoLinks;
    public long idMateria;
    public long idTipoLink;

    public int indexCurso;
    public int indexPeriodo;
    public int indexMateria;
    public int indexTipoLinks;

    private Menu mOptionsMenu;
    private LinklistAdapter mAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mExpandableListView;
    private List<String> mExpandableListTitle;
    private Map<String, List<Object[]>> mExpandableListData;

    private int id_add_link = R.id.btn_add_link;
    private FloatingActionButton btn_add_link;

    private int id_bottom_navigation = R.id.bottom_navigation;
    private BottomNavigationView view_bottom_navigation;

    private SQLiteDatabase cursoDb;
    private SQLiteDatabase periodoDb;
    private SQLiteDatabase materiaDb;
    private SQLiteDatabase tipLinkDb;
    private SQLiteDatabase linkDb;

    private CursolistDbHelper cursoHelper;
    private PeriodolistDbHelper periodoHelper;
    private MaterialistDbHelper materiaHelper;
    private TipLinklistDbHelper tipLinkHelper;
    private LinklistDbHelper linkHelper;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        btn_add_link = (FloatingActionButton) findViewById(id_add_link);
        view_bottom_navigation = (BottomNavigationView) findViewById(id_bottom_navigation);

        cursoHelper = new CursolistDbHelper(this);
        periodoHelper = new PeriodolistDbHelper(this);
        materiaHelper = new MaterialistDbHelper(this);
        tipLinkHelper = new TipLinklistDbHelper(this);
        linkHelper = new LinklistDbHelper(this);

        cursoDb = cursoHelper.getWritableDatabase();
        periodoDb = periodoHelper.getWritableDatabase();
        materiaDb = materiaHelper.getWritableDatabase();
        tipLinkDb = tipLinkHelper.getWritableDatabase();
        linkDb = linkHelper.getWritableDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mExpandableListView = (ExpandableListView) findViewById(R.id.nav_view);

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header_navigation, null, false);
        mExpandableListView.addHeaderView(listHeaderView);

        curso = populateCurso();

        indexCurso = 0;
        indexPeriodo = 0;
        indexMateria = 0;
        indexTipoLinks = 0;

        Materia materia = curso.getPeriodos().get(indexPeriodo).getMaterias().get(indexMateria);

        idMateria = materia.getId();
        idTipoLink = tipoLinks.get(indexTipoLinks).getId();

        getSupportActionBar().setTitle(materia.getNomeMateria());

        RecyclerView linkListRecyclerView;
        linkListRecyclerView = (RecyclerView) this.findViewById(R.id.lista);
        linkListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor links = getAllLinksOfMateriaAndTipo(idMateria, idTipoLink);
        String alias = "V";
        for(TipoLink tipoLink:tipoLinks){
            if(tipoLink.getId() == idTipoLink){
                alias = tipoLink.getAliasTipo();
            }
        }

        mAdapter = new LinklistAdapter(this, links, alias);

        linkListRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeLink(id);
                curso.getPeriodos().get(indexPeriodo).getMaterias().get(indexMateria).removeLinks(findLink(id));
                updateData();
            }
        }).attachToRecyclerView(linkListRecyclerView);
    }

    @Override
    public void onStart(){
        super.onStart();

        setTextCurso(curso);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btn_add_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFragment dialog = MyDialogFragment.newInstance(R.layout.activity_link);
                dialog.show(getFragmentManager(), "Link");
            }
        });

        view_bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String alias = String.valueOf(item.getAlphabeticShortcut());
                Cursor tipLink = getTipLinkByAlias(alias);
                try {
                    tipLink.moveToFirst();
                    idTipoLink = tipLink.getInt(tipLink.getColumnIndex(TipLinklistContract.TipLinklistEntry._ID));
                    if(alias == "V"){
                        indexTipoLinks = 0;
                    } else if(alias == "P"){
                        indexTipoLinks = 1;
                    } else if(alias == "L"){
                        indexTipoLinks = 2;
                    }
                    updateData();
                } finally {
                    tipLink.close();
                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        mExpandableListData = MyMenuItem.getMenuItem(curso, this);
        mExpandableListTitle = new ArrayList<>(mExpandableListData.keySet());

        addDrawerItems();
        setupDrawer();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Object object) {
        if (object instanceof Link) {
            long id = addLink((Link)object);
            ((Link) object).setId(id);
            curso.getPeriodos().get(indexPeriodo).getMaterias().get(indexMateria).addLinks((Link)object);

            updateData();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // DO NOTHING
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
    protected void onPostResume() {
        super.onPostResume();
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
                Object[] objects = (Object[])parent.getExpandableListAdapter().getChild(groupPosition, childPosition);

                idMateria = Integer.parseInt(objects[2].toString());

                indexPeriodo = groupPosition;
                indexMateria = findMateria();

                updateData();

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

    public int findMateria() {
        for(Materia materia : curso.getPeriodos().get(indexPeriodo).getMaterias()) {
            if(materia.getId() == idMateria) {
                return curso.getPeriodos().get(indexPeriodo).getMaterias().indexOf(materia);
            }
        }
        return 0;
    }

    public Link findLink(long id) {
        for(Link link : curso.getPeriodos().get(indexPeriodo).getMaterias().get(indexMateria).getLinks()) {
            if(link.getId() == id) {
                return link;
            }
        }
        return null;
    }

    public void updateData(){
        Cursor materia = getMateria(idMateria);
        Cursor links = getAllLinksOfMateriaAndTipo(idMateria, idTipoLink);
        String alias = "?";
        for(TipoLink tipoLink:tipoLinks){
            if(tipoLink.getId() == idTipoLink){
                alias = tipoLink.getAliasTipo();
            }
        }

        mAdapter.setItems(links, alias);
        mAdapter.notifyDataSetChanged();
        String nomeMateria;
        try {
            materia.moveToFirst();
            nomeMateria = materia.getString(materia.getColumnIndex(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME));
            getSupportActionBar().setTitle(nomeMateria);
        } finally {
            materia.close();
        }
    }

    public Curso populateCurso(){
        /****Curso****/
        Util.insertCurso(cursoDb);
        Cursor c_curso = getAllCursos();

        ArrayList<Curso> cursos = new ArrayList<Curso>();
        try {
            c_curso.moveToFirst();
            Curso curso = new Curso(
                    c_curso.getInt(c_curso.getColumnIndex(CursolistCotract.CursolistEntry._ID)),
                    c_curso.getString(c_curso.getColumnIndex(CursolistCotract.CursolistEntry.COLUMN_CURSO_NAME))
            );

            cursos.add(curso);

        } finally {
            c_curso.close();
        }

        /****Períodos****/
        Util.insertPeriodo(periodoDb, cursos.get(indexCurso).getId());
        Cursor c_periodos = getAllPeriodosOfCurso(cursos.get(indexCurso).getId());

        try {
            for(c_periodos.moveToFirst(); !c_periodos.isAfterLast(); c_periodos.moveToNext()) {
                Periodo periodo = new Periodo(
                        c_periodos.getInt(c_periodos.getColumnIndex(PeriodolistContract.PeriodolistEntry._ID)),
                        c_periodos.getInt(c_periodos.getColumnIndex(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM)),
                        c_periodos.getInt(c_periodos.getColumnIndex(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO))
                );
                cursos.get(indexCurso).addPeriodo(periodo);
            }
        } finally {
            c_periodos.close();
        }

        /****Matérias****/
        Util.insertMateria(materiaDb, new long[]{
                cursos.get(indexCurso).getPeriodos().get(0).getId(),
                cursos.get(indexCurso).getPeriodos().get(1).getId(),
                cursos.get(indexCurso).getPeriodos().get(2).getId(),
                cursos.get(indexCurso).getPeriodos().get(3).getId(),
                cursos.get(indexCurso).getPeriodos().get(4).getId(),
                cursos.get(indexCurso).getPeriodos().get(5).getId(),
                cursos.get(indexCurso).getPeriodos().get(6).getId(),
                cursos.get(indexCurso).getPeriodos().get(7).getId()
        });
        Cursor c_materias;

        for(Periodo periodo: cursos.get(indexCurso).getPeriodos()){
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
            } finally {
                c_materias.close();
            }
        }

        /****Tipo Links****/
        Util.insertTipLink(tipLinkDb);
        Cursor c_tipLink;
        ArrayList<TipoLink> tipoLinks = new ArrayList<TipoLink>();

        c_tipLink = getAllTipLinks();
        try {
            for(c_tipLink.moveToFirst(); !c_tipLink.isAfterLast(); c_tipLink.moveToNext()) {
                TipoLink tipoLink = new TipoLink(
                        c_tipLink.getInt(c_tipLink.getColumnIndex(TipLinklistContract.TipLinklistEntry._ID)),
                        c_tipLink.getString(c_tipLink.getColumnIndex(TipLinklistContract.TipLinklistEntry.COLUMN_TIPO_LINK_NAME)),
                        c_tipLink.getString(c_tipLink.getColumnIndex(TipLinklistContract.TipLinklistEntry.COLUMN_TIPO_LINK_ALIAS))
                );

                tipoLinks.add(tipoLink);
            }
        } finally {
            c_tipLink.close();
        }

        this.tipoLinks = tipoLinks;

        Util.insertLink(linkDb, new long[]{
                cursos.get(indexCurso).getPeriodos().get(0).getMaterias().get(0).getId(),
                cursos.get(indexCurso).getPeriodos().get(0).getMaterias().get(1).getId(),
                cursos.get(indexCurso).getPeriodos().get(0).getMaterias().get(2).getId(),
                cursos.get(indexCurso).getPeriodos().get(0).getMaterias().get(3).getId(),
                cursos.get(indexCurso).getPeriodos().get(0).getMaterias().get(4).getId()
        }, new long[]{
                tipoLinks.get(0).getId(),
                tipoLinks.get(1).getId(),
                tipoLinks.get(2).getId()
        });
        Cursor c_link;

        for(Periodo periodo: cursos.get(indexCurso).getPeriodos()){
            for(Materia materia: periodo.getMaterias()){
                for(TipoLink tipoLink: tipoLinks){
                    c_link = getAllLinksOfMateriaAndTipo(materia.getId(), tipoLink.getId());
                    try {
                        for(c_link.moveToFirst(); !c_link.isAfterLast(); c_link.moveToNext()) {

                            Link link = new Link(
                                    c_link.getInt(c_link.getColumnIndex(LinklistContract.LinklistEntry._ID)),
                                    c_link.getString(c_link.getColumnIndex(LinklistContract.LinklistEntry.COLUMN_LINK_NOME)),
                                    c_link.getString(c_link.getColumnIndex(LinklistContract.LinklistEntry.COLUMN_LINK_URL)),
                                    c_link.getInt(c_link.getColumnIndex(LinklistContract.LinklistEntry.COLUMN_FK_MATERIA)),
                                    c_link.getInt(c_link.getColumnIndex(LinklistContract.LinklistEntry.COLUMN_FK_TIP_LINK))
                            );

                            materia.getLinks().add(link);
                        }
                    } finally {
                        c_link.close();
                    }
                }
            }
        }

        return cursos.get(indexCurso);
    }

    private Cursor getAllCursos(){
        return cursoDb.query(
                CursolistCotract.CursolistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CursolistCotract.CursolistEntry.COLUMN_CURSO_NAME
        );
    }

    private Cursor getAllPeriodosOfCurso(long curso_id){
        return periodoDb.query(
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

    private Cursor getMateria(long id){
        return materiaDb.query(
                MaterialistContract.MaterialistEntry.TABLE_NAME,
                new String[]{MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME},
                MaterialistContract.MaterialistEntry._ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME
        );
    }

    private Cursor getAllMateriasOfPeriodo(long id_periodo){
        return materiaDb.query(
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

    private Cursor getTipLinkByAlias(String alias){
        return tipLinkDb.query(
                TipLinklistContract.TipLinklistEntry.TABLE_NAME,
                new String[]{
                        TipLinklistContract.TipLinklistEntry._ID,
                        TipLinklistContract.TipLinklistEntry.COLUMN_TIPO_LINK_NAME
                },
                TipLinklistContract.TipLinklistEntry.COLUMN_TIPO_LINK_ALIAS + "=?",
                new String[]{alias.toUpperCase()},
                null,
                null,
                TipLinklistContract.TipLinklistEntry._ID
        );
    }

    private Cursor getAllTipLinks(){
        return tipLinkDb.query(
                TipLinklistContract.TipLinklistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                TipLinklistContract.TipLinklistEntry._ID
        );
    }

    private Cursor getAllLinksOfMateriaAndTipo(long id_materia, long id_tipoLink){
        return linkDb.query(
                LinklistContract.LinklistEntry.TABLE_NAME,
                new String[]{
                        LinklistContract.LinklistEntry._ID,
                        LinklistContract.LinklistEntry.COLUMN_LINK_NOME,
                        LinklistContract.LinklistEntry.COLUMN_LINK_URL,
                        LinklistContract.LinklistEntry.COLUMN_FK_MATERIA,
                        LinklistContract.LinklistEntry.COLUMN_FK_TIP_LINK
                },
                LinklistContract.LinklistEntry.COLUMN_FK_MATERIA + "=? AND " +
                        LinklistContract.LinklistEntry.COLUMN_FK_TIP_LINK + "=?",
                new String[]{String.valueOf(id_materia), String.valueOf(id_tipoLink)},
                null,
                null,
                LinklistContract.LinklistEntry._ID
        );
    }

    private long addLink(Link link){
        link.setId_Materia(idMateria);
        link.setId_TipLink(idTipoLink);

        ContentValues cv = new ContentValues();
        cv.put(LinklistContract.LinklistEntry.COLUMN_LINK_NOME, link.getNome());
        cv.put(LinklistContract.LinklistEntry.COLUMN_LINK_URL, link.getUrl());
        cv.put(LinklistContract.LinklistEntry.COLUMN_FK_MATERIA, link.getId_Materia());
        cv.put(LinklistContract.LinklistEntry.COLUMN_FK_TIP_LINK, link.getId_TipLink());
        return linkDb.insert(LinklistContract.LinklistEntry.TABLE_NAME,null, cv);
    }

    private boolean removeLink(long id){
        return linkDb.delete(LinklistContract.LinklistEntry.TABLE_NAME,
                LinklistContract.LinklistEntry._ID + "=" + id,
                null) > 0;
    }

    public void setTextCurso(Curso curso) {
        TextView tv_curso = (TextView) findViewById(R.id.curso);
        tv_curso.append(curso.getNomeCurso());
    }
}
