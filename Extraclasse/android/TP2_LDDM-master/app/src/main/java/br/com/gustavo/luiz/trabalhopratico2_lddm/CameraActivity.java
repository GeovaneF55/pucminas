package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity
{
    // definir dados
    private ImageView imageView;
    private FloatingActionButton btnCamera;
    private final int TIRAR_FOTO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // associando botoes e a imagem
        btnCamera = (FloatingActionButton) findViewById(R.id.btnCamera);
        imageView = (ImageView) findViewById(R.id.fotoTirada);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivityForResult(intent, TIRAR_FOTO);
                }// end if
            }// end onClick( )
        });
    }// end onCreate( )

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TIRAR_FOTO && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }// end if
    }// end onActivityResult( )
}// end class
