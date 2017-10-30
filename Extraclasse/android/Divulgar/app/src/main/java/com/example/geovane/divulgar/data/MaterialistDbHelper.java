package com.example.geovane.divulgar.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by geovane on 23/10/17.
 */

// TODO (1) extend the SQLiteOpenHelper class
public class MaterialistDbHelper extends SQLiteOpenHelper {
    // TODO (2) Create a static final String called DATABASE_NAME and set it to "waitlist.db"
    private static final String DATABASE_NAME = "materia.db";
    // TODO (3) Create a static final int called DATABASE_VERSION and set it to 1
    private static final int DATABASE_VERSION = 3;
    // TODO (4) Create a Constructor that takes a context and calls the parent constructor
    public MaterialistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // TODO (5) Override the onCreate method
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO (6) Inside, create an String query called SQL_CREATE_WAITLIST_TABLE that will create the table
        final String SQL_CREATE_MATERIA_TABLE = "CREATE TABLE " +
                MaterialistContract.MaterialistEntry.TABLE_NAME + "(" +
                MaterialistContract.MaterialistEntry._ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME + " TEXT NOT NULL," +
                MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO + " INTEGER NOT NULL," +
                "FOREIGN KEY(" + MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO + ") REFERENCES " +
                PeriodolistContract.PeriodolistEntry.TABLE_NAME + "(" + PeriodolistContract.PeriodolistEntry._ID + ")"+
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_MATERIA_TABLE);

        // TODO (7) Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE
    }

    // TODO (8) Override the onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO (9) Inside, execute a drop table query, and then call onCreate to re-create it
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MaterialistContract.MaterialistEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}