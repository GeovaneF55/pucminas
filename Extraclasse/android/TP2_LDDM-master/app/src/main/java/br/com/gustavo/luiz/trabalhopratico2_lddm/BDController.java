package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BDController
{
    // definir dados
    private SQLiteDatabase db;
    private BD banco;

    public BDController(Context context)
    {
        banco = new BD(context);
    }// end contrutor

    public void insereDado(double lat, double lon, String date)
    {
        // definir dados
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase( );
        valores = new ContentValues( );
        valores.put(BD.LAT, lat);
        valores.put(BD.LONG, lon);
        valores.put(BD.DATE, date);

        resultado = db.insert(BD.TABELA, null, valores);

        // fecha o banco de dados
        db.close( );

        if(resultado == -1)
        {
            Log.d("BD", "Erro ao inserir registro");
        }
        else
        {
            Log.d("BD", "Registro inserido com sucesso");
        }// end if
    }// end insereDado( )

    public Cursor carregaDados( )
    {
        // definir dados
        Cursor cursor;
        String[] campos = {banco.ID, banco.LAT, banco.LONG, banco.DATE};

        db = banco.getReadableDatabase( );

        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }// end if

        db.close( );

        return(cursor);
    }// end carregaDados( )

    public void deletaDados( )
    {
        db = banco.getWritableDatabase( );
        db.execSQL("delete from " + banco.TABELA);
        db.close();
    }// end deleteDados( )
}// end class BDController
