package com.example.geova.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.telephony.PhoneNumberFormattingTextWatcher;

public class MainActivity extends AppCompatActivity {

    // IDs
    private int id_nomeEvento = R.id.edt_nomeEvento;
    private int id_dataInicio = R.id.edt_dataInicio;
    private int id_dataFim = R.id.edt_dataFim;
    private int id_email = R.id.edt_emailOrganizador;
    private int id_phone = R.id.edt_telefoneOrganizador;

    private int country_code = R.string.country_code;

    private int id_criarEvento = R.id.btn_criarEvento;

    // TextEdits
    EditText edt_nomeEvento;
    EditText edt_dataInicio;
    EditText edt_dataFim;
    EditText edt_email;
    EditText edt_phone;

    // Buttons
    Button btn_criarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nomeEvento = (EditText) findViewById(id_nomeEvento);
        edt_dataInicio = (EditText) findViewById(id_dataInicio);
        edt_dataFim = (EditText) findViewById(id_dataFim);
        edt_email = (EditText) findViewById(id_email);
        edt_phone = (EditText) findViewById(id_phone);
        btn_criarEvento = (Button) findViewById(id_criarEvento);
    }

    @Override
    protected void onStart(){
        super.onStart();
        // Listeners
        new MyEditTextDatePicker(this , id_dataInicio);
        new MyEditTextDatePicker(this , id_dataFim);
        edt_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(getResources().getString(country_code)));
    }
}
