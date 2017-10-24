package com.example.geovane.divulgar.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geovane on 23/10/17.
 */

public class Util {
    public static void insertCurso(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(CursolistCotract.CursolistEntry.COLUMN_CURSO_NAME, "Ciência da Computação");
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete(CursolistCotract.CursolistEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(CursolistCotract.CursolistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }
    }

    public static void insertPeriodo(SQLiteDatabase db, int curso_id){
        if(db == null){
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM, 1);
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO, curso_id);
        list.add(cv);

        cv = new ContentValues();
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM, 2);
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO, curso_id);
        list.add(cv);

        cv = new ContentValues();
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM, 3);
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO, curso_id);
        list.add(cv);

        cv = new ContentValues();
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM, 4);
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO, curso_id);
        list.add(cv);

        cv = new ContentValues();
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM, 5);
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO, curso_id);
        list.add(cv);

        cv = new ContentValues();
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM, 6);
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO, curso_id);
        list.add(cv);

        cv = new ContentValues();
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM, 7);
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO, curso_id);
        list.add(cv);

        cv = new ContentValues();
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_PERIODO_NUM, 8);
        cv.put(PeriodolistContract.PeriodolistEntry.COLUMN_FK_CURSO, curso_id);
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete(PeriodolistContract.PeriodolistEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(PeriodolistContract.PeriodolistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }
    }

    public static void insertMateria(SQLiteDatabase db, int[] periodo_id){
        if(db == null){
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        /*1 Período*/
        ContentValues cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AED I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[0]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Cálculo I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[0]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "CS");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[0]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "ICC");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[0]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "LPV");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[0]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Seminários I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[0]);
        list.add(cv);

        /*2 Período*/
        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AED II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[1]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Cálculo II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[1]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "GA");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[1]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AC I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[1]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "CR I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[1]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Seminários II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[1]);
        list.add(cv);

        /*3 Período*/
        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AED III");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[2]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AC II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[2]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AL");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[2]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Cálculo III");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[2]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Lab. AC II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[2]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "IPCC");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[2]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Empreendedorismo");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[2]);
        list.add(cv);

        /*4 Período*/
        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AG");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[3]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "ES I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[3]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "BD");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[3]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "LDDM");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[3]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "EP");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[3]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AC III");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[3]);
        list.add(cv);

        /*5 Período*/
        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "FTC");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[4]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "PAA");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[4]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "SO");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[4]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "LP");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[4]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "CR II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[4]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "AC III");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[4]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "LPA");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[4]);
        list.add(cv);

        /*6 Período*/
        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "PID");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[5]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "ES III");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[5]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "LPS");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[5]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Redes I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[5]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Filosofia I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[5]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "CP");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[5]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "CG");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[5]);
        list.add(cv);

        /*7 Período*/
        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "RI");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[6]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "IA");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[6]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Redes II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[6]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Compiladores");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[6]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Tópicos II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[6]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "OS");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[6]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "LRSO");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[6]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "TCC I");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[6]);
        list.add(cv);

        /*8 Período*/
        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Tópicos III");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[7]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Modelagem");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[7]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "SAS");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[7]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "CD");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[7]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Filosofia II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[7]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "Seminários IV");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[7]);
        list.add(cv);

        cv = new ContentValues();
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_MATERIA_NAME, "TCC II");
        cv.put(MaterialistContract.MaterialistEntry.COLUMN_FK_PERIODO, periodo_id[7]);
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete(MaterialistContract.MaterialistEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(MaterialistContract.MaterialistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }
    }

    public static void insertTipLink(SQLiteDatabase db){
        if(db == null){
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(TipLinklistContract.TipLinklistEntry.COLUMN_TIPO_LINK_NAME, "Vídeo");
        list.add(cv);

        cv = new ContentValues();
        cv.put(TipLinklistContract.TipLinklistEntry.COLUMN_TIPO_LINK_NAME, "PDF");
        list.add(cv);

        cv = new ContentValues();
        cv.put(TipLinklistContract.TipLinklistEntry.COLUMN_TIPO_LINK_NAME, "Link");
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete(TipLinklistContract.TipLinklistEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(TipLinklistContract.TipLinklistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }
    }
}
