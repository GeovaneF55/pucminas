package com.example.geova.agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.widget.EditText;

/**
 * Created by geovane on 17/09/17.
 */

public class OrganizadorActivity extends AppCompatActivity {

    private int id_email = R.id.edt_emailOrganizador;
    private int id_phone = R.id.edt_telefoneOrganizador;

    private int country_code = R.string.country_code;


    EditText edt_email;
    EditText edt_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizador);

        edt_email = (EditText) findViewById(id_email);
        edt_phone = (EditText) findViewById(id_phone);
    }

    @Override
    protected void onStart(){
        super.onStart();
        // Listeners
        edt_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(getResources().getString(country_code)));
    }
}