package com.example.geovane.divulgar.data;

import android.provider.BaseColumns;

/**
 * Created by geovane on 23/10/17.
 */

public class TipLinklistContract {
    public static final class TipLinklistEntry implements BaseColumns {
        public static final String TABLE_NAME = "tipo_link";
        //ID Tipo Link
        //NOME Tipo Link
        public static final String COLUMN_TIPO_LINK_NAME = "nome_tipo_link";
        //ALIAS Tipo Link
        public static final String COLUMN_TIPO_LINK_ALIAS = "alias_tipo_link";
    }
}
