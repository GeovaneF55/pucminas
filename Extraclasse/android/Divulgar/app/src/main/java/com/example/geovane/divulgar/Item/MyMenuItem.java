package com.example.geovane.divulgar.Item;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

import com.example.geovane.divulgar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by geovane on 15/10/17.
 */

public class MyMenuItem {
    public static Map<String, List<Object[]>> getMenuItem(Context context) {
        Map<String, List<Object[]>> expandableListData = new TreeMap<>();

        SharedPreferences sharedPreferences = context.getSharedPreferences(context
                .getString(R.string.menu_preference_file_key), Context.MODE_PRIVATE);
        Map<String, ?> itemsMenu = sharedPreferences.getAll();

        // Default menus
        if (itemsMenu.size() == 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for (int i = 1; i <= 8; i++) {
                String key = "item_" + i + "_period";
                String value = i + "º período";
                editor.putString(key, value);
                editor.apply();

                itemsMenu = sharedPreferences.getAll();
            }
        }

        List<Object> titles = new ArrayList<>(itemsMenu.values());

        List<Object[]> items = new ArrayList<>();

        Object[] itemMovie = new Object[2];
        itemMovie[0] = ContextCompat.getDrawable(context, R.drawable.ic_action_video);
        itemMovie[1] = context.getResources().getString(R.string.movies);

        items.add(itemMovie);

        Object[] itemPdf = new Object[2];
        itemPdf[0] = ContextCompat.getDrawable(context, R.drawable.ic_action_pdf);
        itemPdf[1] = context.getResources().getString(R.string.pdf);

        items.add(itemPdf);

        Object[] itemLink = new Object[2];
        itemLink[0] = ContextCompat.getDrawable(context, R.drawable.ic_action_link);
        itemLink[1] = context.getResources().getString(R.string.links);

        items.add(itemLink);

        for (Object title : titles) {
            expandableListData.put((String) title, items);
        }

        return expandableListData;
    }
}
