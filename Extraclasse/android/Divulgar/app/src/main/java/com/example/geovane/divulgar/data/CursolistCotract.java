package com.example.geovane.divulgar.data;

import android.provider.BaseColumns;

/**
 * Created by geovane on 23/10/17.
 */

public class CursolistCotract {
    public static final class CursolistEntry implements BaseColumns{
        public static final String TABLE_NAME = "curso";
        //ID Curso
        //NOME Curso
        public static final String COLUMN_CURSO_NAME = "nome_curso";
    }
}
