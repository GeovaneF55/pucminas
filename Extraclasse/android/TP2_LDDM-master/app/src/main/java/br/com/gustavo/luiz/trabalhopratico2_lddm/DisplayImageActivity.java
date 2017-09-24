package br.com.gustavo.luiz.trabalhopratico2_lddm;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by luiz on 10/05/17.
 */

public class DisplayImageActivity extends Activity
{
    // definir dados
    public ImageView imageDetail;
    public int imageId;
    public Bitmap theImage;

    @Override
    public void onCreate(Bundle savedInstaceState)
    {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_banco_de_dados);

        imageDetail = (ImageView) findViewById(R.id.fto);

        Intent intent = getIntent();

        theImage = (Bitmap) intent.getParcelableExtra("imagename");
        imageId = intent.getIntExtra("imageid", 20);

        imageDetail.setImageBitmap(theImage);

    }// end onCreate( )
}// end class
