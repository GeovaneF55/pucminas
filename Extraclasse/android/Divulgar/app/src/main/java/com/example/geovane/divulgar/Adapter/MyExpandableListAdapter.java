package com.example.geovane.divulgar.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.geovane.divulgar.R;

import java.util.List;
import java.util.Map;

/**
 * Created by geovane on 15/10/17.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private final Context mContext;
    private List<String> mExpandableListTitle;
    private Map<String, List<Object[]>> mExpandableListDetail;
    private LayoutInflater mLayoutInflater;

    public MyExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       Map<String, List<Object[]>> expandableListDetail) {
        mContext = context;
        mExpandableListTitle = expandableListTitle;
        mExpandableListDetail = expandableListDetail;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition))
                .get(expandedListPosition)[1];
    }

    private Object[] getChildArray(int listPosition, int expandedListPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Object[] item = getChildArray(listPosition, expandedListPosition);
        final String expandedListText = (String) item[1];

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.nav_item, null);
        }

        TextView expandableListTextView = convertView
                .findViewById(R.id.expandedListItem);

        expandableListTextView.setText(expandedListText);
        expandableListTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) item[0],
                null, null, null);
        expandableListTextView.setCompoundDrawablePadding((int) mContext.getResources()
                .getDimension(R.dimen.layout_padding_default));
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return mExpandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return mExpandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.nav_group, null);
        }

        TextView listTitleTextView = convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
