package com.example.geovane.divulgar.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.example.geovane.divulgar.Model.Link;
import com.example.geovane.divulgar.R;

/**
 * Created by geovane on 31/10/17.
 */

public class MyOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getTag(R.id.img_url).toString()){
            case "V":
                watchYoutubeVideo(view.getContext(), Link.getVideoId(view.getTag(R.id.txt_url).toString()));
                break;
            case "P":
                break;
            case "L":
                break;
        }
    }

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
}
