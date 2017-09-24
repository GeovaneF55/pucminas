package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by luiz on 13/05/17.
 */

public class CoordAdapter extends ArrayAdapter<Coordenadas>
{
    // definir dados
    private final Context context;
    private final ArrayList<Coordenadas> elementos;

    public CoordAdapter(Context context, ArrayList<Coordenadas> elementos)
    {
        super(context, R.layout.content_db, elementos);

        this.context = context;
        this.elementos = elementos;
    }// end construtor

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.content_db, parent, false);

        TextView latitude = (TextView) rowView.findViewById(R.id.lat);
        TextView longitude = (TextView) rowView.findViewById(R.id.lon);

        latitude.setText(elementos.get(position).getLatitude());
        longitude.setText(elementos.get(position).getLongitude());

        return(rowView);
    }// end getView( )
}// end class
