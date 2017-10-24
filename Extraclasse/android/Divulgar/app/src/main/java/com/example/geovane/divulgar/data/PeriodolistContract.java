package com.example.geovane.divulgar.data;

import android.provider.BaseColumns;

/**
 * Created by geovane on 23/10/17.
 */

public class PeriodolistContract {
    public static final class PeriodolistEntry implements BaseColumns {
        public static final String TABLE_NAME = "periodo";
        //ID Periodo
        //NUM Periodo
        public static final String COLUMN_PERIODO_NUM = "cod_periodo";
        //ID Curso
        public static final String COLUMN_FK_CURSO = "id_curso";
    }
}
