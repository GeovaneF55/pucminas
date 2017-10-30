package com.example.geovane.divulgar.data;

import android.provider.BaseColumns;

/**
 * Created by geovane on 23/10/17.
 */

public class LinklistContract {
    public static final class LinklistEntry implements BaseColumns {
        public static final String TABLE_NAME = "link";
        //ID Link
        //NOME Link
        public static final String COLUMN_LINK_NOME = "nome";
        //URL Link
        public static final String COLUMN_LINK_URL = "url";
        //ID materia
        public static final String COLUMN_FK_MATERIA = "id_materia";
        //ID tipo_link {PDF, Link, video}
        public static final String COLUMN_FK_TIP_LINK = "id_tip_link";
    }
}
