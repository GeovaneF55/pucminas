package com.example.geovane.divulgar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.geovane.divulgar.R;

import java.util.List;

/**
 * Created by geovane on 15/10/17.
 */

public class MyListAdapter extends BaseAdapter {
    private List<String> mListMenuItem;
    private LayoutInflater mLayoutInflater;

    public MyListAdapter(Context context, List<String> listMenuItem) {
        Context mContext = context;
        mListMenuItem = listMenuItem;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListMenuItem.size();
    }

    @Override
    public Object getItem(int position) {
        return mListMenuItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        String itemTitle = mListMenuItem.get(position);

        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.menu_list_item, null);
        }

        TextView listTextView = view.findViewById(R.id.menuListItem);
        listTextView.setText(itemTitle);

        return view;
    }
}
