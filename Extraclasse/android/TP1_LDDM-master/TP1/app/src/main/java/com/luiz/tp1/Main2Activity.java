package com.luiz.tp1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity
{
    // definir dados
    ListView listView;
    ArrayList<String> StoreContacts;
    ArrayAdapter<String> arrayAdapter;
    Cursor cursor;
    String name, phoneNumber;
    public  static final int RequestPermissionCode = 1;
    private AlertDialog alerta;

    /**
     * Metodo onCreate( ).
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // criando listview
        listView = (ListView) findViewById(R.id.list_view);

        StoreContacts = new ArrayList<>( );

        enableRuntimePermission( );

        // linkando botao
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.reload);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getContactsIntoArrayList();

                arrayAdapter = new ArrayAdapter<>(Main2Activity.this, R.layout.content_main2, R.id.Nome, StoreContacts);

                listView.setAdapter(arrayAdapter);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cliqueNoContato(parent, position, view);
            }
        });
    }// end onCreate( )

    /**
     * Metodo cliqueNoContato( ) - cria um pop-up para escolha.
     * @param p
     * @param pos
     * @param v
     */
    public void cliqueNoContato(final AdapterView<?> p, int pos, View v)
    {
        //Lista de itens
        ArrayList<String> itens = new ArrayList<>();
        itens.add("Ligar");
        itens.add("Enviar Email");
        itens.add("Enviar SMS");
        itens.add("Enviar WhatsApp");

        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.dialog, itens);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha um opção:");

        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(Main2Activity.this, "posição selecionada = " + arg1, Toast.LENGTH_SHORT).show();

                alerta.dismiss();

                if(arg1 == 0){
                    ligar(p.getAdapter().toString());
                } else if(arg1 == 1){
                    email(p.getAdapter().toString());
                } else if(arg1 == 2){
                    sms(p.getAdapter().toString());
                } else if(arg1 == 3){
                    whatsapp(p.getAdapter().toString());
                }// end if
            }// end onClick( )
        });

        alerta = builder.create();
        alerta.show();
    }// end cliqueNoContato( )

    /**
     * Metodo ligar( ).
     * @param telefone
     */
    public void ligar(String telefone)
    {
        Uri uri = Uri.parse("tel:" + telefone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

        startActivity(intent);
    }// end ligar( )

    /**
     * Metodo email( ).
     * @param address
     */
    public void email(String address)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, address);

        startActivity(intent);
    }// end email( )

    /**
     * Metodo sms( ).
     * @param sms
     */
    public void sms(String sms)
    {
        Intent enviarSMS = new Intent(Intent.ACTION_VIEW);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(sms, null, null, null, null);

        startActivity(enviarSMS);
    }// end sms( )

    /**
     * Metodo whatsapp( ).
     * @param number
     */
    public void whatsapp(String number)
    {
        // passa o numero do contato
        Uri uri = Uri.parse("smsto:" + number);

        // criando a intent
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);

        // tipo texto
        sendIntent.setPackage("text/plain");

        // adiciona o package do whatsapp
        sendIntent.setPackage("com.whatsapp");

        // iniciando activity
        startActivity(Intent.createChooser(sendIntent, ""));
    }// end whatsapp( )

    /**
     * Metodo getContactsIntoArrayList( ).
     */
    public void getContactsIntoArrayList( )
    {
        cursor = getContentResolver( ).query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

        while(cursor.moveToNext( ))
        {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            StoreContacts.add(name + " " + " : " + " " + phoneNumber);

        }// end while

        cursor.close( );
    }// end getContactsIntoArrayList( )

    /**
     * Metodo enableRuntimePermission( ).
     */
    public void enableRuntimePermission( )
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this, android.Manifest.permission.READ_CONTACTS))
        {
            Toast.makeText(Main2Activity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show( );
        }
        else
        {
            ActivityCompat.requestPermissions(Main2Activity.this,new String[]{android.Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
        }// end if
    }// end enableRuntimePermission( )
    /**
     * Metodo onRequestPermissionsResult( ).
     * @param RC
     * @param per
     * @param PResult
     */
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult)
    {
        switch(RC)
        {
            case RequestPermissionCode:

                if(PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Main2Activity.this,"Permissão Garantida", Toast.LENGTH_LONG).show( );
                }
                else

                {
                    Toast.makeText(Main2Activity.this,"Permissão Negada", Toast.LENGTH_LONG).show( );
                }// end if
                break;
        }// end switch
    }// end onRequestPermissionsResult( )
}// end class