package com.example.geovane.divulgar.Item;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;

import com.example.geovane.divulgar.Model.Curso;
import com.example.geovane.divulgar.Model.Materia;
import com.example.geovane.divulgar.Model.Periodo;
import com.example.geovane.divulgar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by geovane on 15/10/17.
 */

public class MyMenuItem {

    public static Map<String, List<Object[]>> getMenuItem(Curso curso, Context context) {
        Map<String, List<Object[]>> expandableListData = new TreeMap<>();

        SharedPreferences sharedPreferences = context.getSharedPreferences(context
                .getString(R.string.menu_preference_file_key), Context.MODE_PRIVATE);
        Map<String, ?> itemsMenu = sharedPreferences.getAll();

        // Default menus
        if (itemsMenu.size() == 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            for (Periodo periodo:curso.getPeriodos()) {
                String key = "item_" + periodo.getPeriodo() + "_period";
                String value = periodo.getPeriodo() + "º período";
                editor.putString(key, value);
                editor.apply();
            }
        }

        for (Periodo periodo:curso.getPeriodos()) {
            List<Object[]> items = new ArrayList<>();

            for(Materia materia:periodo.getMaterias()){
                Object[] itemMateria = new Object[3];
                itemMateria[0] = ContextCompat.getDrawable(context, R.drawable.ic_action_book);
                itemMateria[1] = materia.getNomeMateria();
                itemMateria[2] = String.valueOf(materia.getId());

                items.add(itemMateria);
            }
            expandableListData.put((periodo.getPeriodo() + "º período"), items);
        }

        return expandableListData;
    }
}
