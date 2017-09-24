package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper
{
    // definir dados
    public static final String NOME_BANCO = "bd.db";
    public static final String TABELA = "UserLocation";
    public static final String ID = "_id";
    public static final String LAT = "latitude";
    public static final String LONG = "longitude";
    public static final String DATE = "data";
    public static final int VERSAO = 1;

    public BD(Context context)
    {
        super(context, NOME_BANCO, null, VERSAO);
    }// end contrutor

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + TABELA + " ( " + ID + " integer primary key autoincrement," + LAT + " double," + LONG + " double," + DATE + " text" +")";
        db.execSQL(sql);
    }// end onCreate( )

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }// end onUpgrade( )
}// end class BD
