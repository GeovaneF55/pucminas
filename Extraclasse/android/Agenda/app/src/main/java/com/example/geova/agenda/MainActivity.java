package com.example.geova.agenda;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.PhoneNumberFormattingTextWatcher;

import com.example.geova.agenda.Controls.MyEditTextDatePicker;

public class MainActivity extends AppCompatActivity {

    // IDs
    private int id_nomeEvento = R.id.edt_nomeEvento;
    private int id_dataInicio = R.id.edt_dataInicio;
    private int id_dataFim = R.id.edt_dataFim;

    private int id_add_Organizador = R.id.btn_add_Organizador;
    private int id_criarEvento = R.id.btn_criarEvento;

    // TextEdits
    EditText edt_nomeEvento;
    EditText edt_dataInicio;
    EditText edt_dataFim;

    // Buttons
    FloatingActionButton btn_add_Organizador;
    Button btn_criarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nomeEvento = (EditText) findViewById(id_nomeEvento);
        edt_dataInicio = (EditText) findViewById(id_dataInicio);
        edt_dataFim = (EditText) findViewById(id_dataFim);
        btn_add_Organizador = (FloatingActionButton) findViewById(id_add_Organizador);
        btn_criarEvento = (Button) findViewById(id_criarEvento);
    }

    @Override
    protected void onStart(){
        super.onStart();
        // Listeners
        new MyEditTextDatePicker(this , id_dataInicio);
        new MyEditTextDatePicker(this , id_dataFim);

        btn_add_Organizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_criarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
