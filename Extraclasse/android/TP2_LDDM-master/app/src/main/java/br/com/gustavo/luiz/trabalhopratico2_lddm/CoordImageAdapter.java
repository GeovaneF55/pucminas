package br.com.gustavo.luiz.trabalhopratico2_lddm;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by luiz on 10/05/17.
 */

public class CoordImageAdapter extends ArrayAdapter<Coordenadas>
{
    // definir dados
    public Context context;
    public int layoutResourceId;
    public ArrayList<Coordenadas> data = new ArrayList<>();

    public CoordImageAdapter(Context context, int layoutResourceId, ArrayList<Coordenadas> data)
    {
        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        ImageHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ImageHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.fto);
            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder) row.getTag();
        }// end if

        Coordenadas picture = data.get(position);

        //byte[] outImage = picture.getFoto();

        //ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        //Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        //holder.imgIcon.setImageBitmap(theImage);

        return row;
    }

    static class ImageHolder
    {
        ImageView imgIcon;
    }
}
