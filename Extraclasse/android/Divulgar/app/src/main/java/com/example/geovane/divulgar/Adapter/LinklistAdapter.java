package com.example.geovane.divulgar.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geovane.divulgar.Model.TipoLink;
import com.example.geovane.divulgar.R;
import com.example.geovane.divulgar.data.LinklistContract;

import java.util.List;

/**
 * Created by geovane on 24/10/17.
 */

public class LinklistAdapter extends RecyclerView.Adapter<LinklistAdapter.LinkViewHolder> {

    private Context mContext;
// TODO (8) Add a new local variable mCount to store the count of items to be displayed in the recycler view
    private Cursor mCursor;
    private String tipoLink;

    /**
     * Constructor using the context and the db cursor
     *
     * @param context the calling context/activity
     */
// TODO (9) Update the Adapter constructor to accept an integer for the count along with the context
    public LinklistAdapter(Context context, Cursor cursor, String tipoLink) {
        this.mContext = context;
        // TODO (10) Set the local mCount to be equal to count
        this.mCursor = cursor;
        this.tipoLink = tipoLink;
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
        if(!mCursor.moveToPosition(position)){
            return;
        }
        long id = mCursor.getLong(mCursor.getColumnIndex(LinklistContract.LinklistEntry._ID));
        String nome = mCursor.getString(mCursor.getColumnIndex(LinklistContract.LinklistEntry.COLUMN_LINK_NOME));
        String url = mCursor.getString(mCursor.getColumnIndex(LinklistContract.LinklistEntry.COLUMN_LINK_URL));

        holder.urlImgView.setText(tipoLink);
        holder.nomeTextView.setText(nome);
        holder.urlTextView.setText(url);
        holder.itemView.setTag(id);
    }

    // TODO (11) Modify the getItemCount to return the mCount value rather than 0
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void setItems(Cursor cursor, String tipoLink){
        this.mCursor = cursor;
        this.tipoLink = tipoLink;
    }


    /**
     * Inner class to hold the views needed to display a single item in the recycler-view
     */
    class LinkViewHolder extends RecyclerView.ViewHolder {

        // Will display the url
        TextView urlImgView;
        TextView nomeTextView;
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
            urlImgView = (TextView) itemView.findViewById(R.id.img_url);
            nomeTextView = (TextView) itemView.findViewById(R.id.txt_nome);
            urlTextView = (TextView) itemView.findViewById(R.id.txt_url);
        }
    }
}