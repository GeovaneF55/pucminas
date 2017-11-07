package com.example.geovane.divulgar.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.FileUriExposedException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.geovane.divulgar.Model.Link;
import com.example.geovane.divulgar.R;

import java.io.File;

/**
 * Created by geovane on 31/10/17.
 */

public class MyOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        String url = view.getTag(R.id.txt_url).toString();
        switch (view.getTag(R.id.img_url).toString()){
            case "V":
                watchYoutubeVideo(view.getContext(), Link.getVideoId(url));
                break;
            case "P":
                openPDF(view.getContext(), url);
                break;
            case "L":
                openLink(view.getContext(), url);
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
        } catch (FileUriExposedException e){
            Toast.makeText(context, "Vídeo não Funcionou", Toast.LENGTH_SHORT).show();
        }
    }

    public static void openPDF(Context context, String path){
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.parse(path),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Sem Activity", Toast.LENGTH_SHORT).show();
        }
    }

    public static void openLink(Context context, String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(link));
        try {
            context.startActivity(browserIntent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "Sem Activity", Toast.LENGTH_SHORT).show();
        } catch (FileUriExposedException e){
            Toast.makeText(context, "Link não Funcionou", Toast.LENGTH_SHORT).show();
        }
    }
}
