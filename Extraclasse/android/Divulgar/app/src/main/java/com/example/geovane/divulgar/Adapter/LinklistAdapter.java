package com.example.geovane.divulgar.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geovane.divulgar.R;

/**
 * Created by geovane on 24/10/17.
 */

public class LinklistAdapter extends RecyclerView.Adapter<LinklistAdapter.LinkViewHolder> {

    private Context mContext;
// TODO (8) Add a new local variable mCount to store the count of items to be displayed in the recycler view

    /**
     * Constructor using the context and the db cursor
     *
     * @param context the calling context/activity
     */
// TODO (9) Update the Adapter constructor to accept an integer for the count along with the context
    public LinklistAdapter(Context context) {
        this.mContext = context;
        // TODO (10) Set the local mCount to be equal to count
    }

    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.link_list_item, parent, false);
        return new LinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LinkViewHolder holder, int position) {

    }


    // TODO (11) Modify the getItemCount to return the mCount value rather than 0
    @Override
    public int getItemCount() {
        return 0;
    }


    /**
     * Inner class to hold the views needed to display a single item in the recycler-view
     */
    class LinkViewHolder extends RecyclerView.ViewHolder {

        // Will display the url
        TextView urlTextView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews
         *
         * @param itemView The View that you inflated in
         *                 {@link LinklistAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public LinkViewHolder(View itemView) {
            super(itemView);
            urlTextView = (TextView) itemView.findViewById(R.id.url);
        }
    }
}