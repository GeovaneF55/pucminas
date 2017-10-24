package com.example.geovane.divulgar.data;

import android.provider.BaseColumns;

/**
 * Created by geovane on 23/10/17.
 */

public class MaterialistContract {
    public static final class MaterialistEntry implements BaseColumns {
        public static final String TABLE_NAME = "materia";
        //ID Materia
        //NOME Materia
        public static final String COLUMN_MATERIA_NAME = "nome_materia";
        //ID Periodo
        public static final String COLUMN_FK_PERIODO = "id_periodo";
    }
}
