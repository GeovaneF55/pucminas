package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

/**
 * Created by luiz on 09/05/17.
 */

public class DbHelper extends SQLiteOpenHelper
{
    // definir dados
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CoordenadasDB";
    private static final String TABLE_COORDENADAS = "coordenadas";
    private static final String ID = "_id";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String[] COLUNAS = {ID, LATITUDE, LONGITUDE};

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }// end constructor

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE coordenadas (" +
                "id        INTEGER PRIMARY KEY AUTOINCREMENT," +
                "latitude  TEXT," +
                "longitude TEXT)";

        db.execSQL(CREATE_TABLE);
    }// end onCreate( )

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS coordenadas");
        onCreate(db);
    }// end onUpgrade( )

    public void addCoordenadas(Coordenadas coordenadas)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LATITUDE, coordenadas.getLatitude());
        values.put(LONGITUDE, coordenadas.getLongitude());

        db.insert(TABLE_COORDENADAS, null, values);
        db.close();
    }// end addCoordenadas( )

    public Coordenadas getCoordenadas(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COORDENADAS, COLUNAS, " id = ?",
                                 new String[] {String.valueOf(id)},
                                 null, null, null, null);

        if(cursor == null)
        {
            return(null);
        }
        else
        {
            cursor.moveToFirst();

            Coordenadas coordenadas = cursorToCoordenadas(cursor);

            return(coordenadas);
        }// end if
    }// end getCoordenadas( )

    private Coordenadas cursorToCoordenadas(Cursor cursor)
    {
        Coordenadas coordenadas = new Coordenadas();

        coordenadas.setId(Integer.parseInt(cursor.getString(0)));
        coordenadas.setLatitude(cursor.getString(1));
        coordenadas.setLongitude(cursor.getString(2));

        return(coordenadas);
    }// end cursorToCoordenadas( )

    public ArrayList<Coordenadas> getAllCoordenadas( )
    {
        ArrayList<Coordenadas> listaCoordenadas = new ArrayList<>();

        String query = "SELECT  * FROM " + TABLE_COORDENADAS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do {
                Coordenadas coordenadas = cursorToCoordenadas(cursor);
                listaCoordenadas.add(coordenadas);
            }while(cursor.moveToNext());
        }// end if

        return(listaCoordenadas);
    }// end getAllCoordenadas( )

    public int updateCoordenadas(Coordenadas coordenadas)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(LATITUDE, coordenadas.getLatitude());
        values.put(LONGITUDE, coordenadas.getLongitude());

        int i = db.update(TABLE_COORDENADAS, values, ID + " = ?",
                          new String[]{String.valueOf(coordenadas.getId())});
        db.close();
        return i;
    }// end updateCoordenadas( )

    public int deleteCoordenadas(Coordenadas coordenadas)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABLE_COORDENADAS, ID + " = ?",
                          new String[]{String.valueOf(coordenadas.getId())});

        db.close();

        return(i);
    }// end deleteCoordenadas( )
}// end class
