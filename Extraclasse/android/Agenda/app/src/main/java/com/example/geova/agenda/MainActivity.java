package com.example.geova.agenda;

import android.app.DialogFragment;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geova.agenda.Controls.MyEditTextDatePicker;
import com.example.geova.agenda.Controls.MyEditTextTimePicker;
import com.example.geova.agenda.Controls.MyDialogFragment;

public class MainActivity extends FragmentActivity implements MyDialogFragment.NoticeDialogListener{

    final Context self = this;

    // IDs
    private int id_nomeEvento = R.id.edt_nomeEvento;
    private int id_dataInicio = R.id.edt_dataInicio;
    private int id_horaInicio = R.id.edt_horaInicio;
    private int id_dataFim = R.id.edt_dataFim;
    private int id_horaFim = R.id.edt_horaFim;

    private int id_add_Organizador = R.id.btn_add_Organizador;
    private int id_add_Convidado = R.id.btn_add_Convidado;
    private int id_criarEvento = R.id.btn_criarEvento;

    // TextEdits
    EditText edt_nomeEvento;
    EditText edt_dataInicio;
    EditText edt_horaInicio;
    EditText edt_dataFim;
    EditText edt_horaFim;

    // Buttons
    FloatingActionButton btn_add_Organizador;
    FloatingActionButton btn_add_Convidado;
    Button btn_criarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nomeEvento = (EditText) findViewById(id_nomeEvento);
        edt_dataInicio = (EditText) findViewById(id_dataInicio);
        edt_horaInicio = (EditText) findViewById(id_horaInicio);
        edt_dataFim = (EditText) findViewById(id_dataFim);
        edt_horaFim = (EditText) findViewById(id_horaFim);
        btn_add_Organizador = (FloatingActionButton) findViewById(id_add_Organizador);
        btn_add_Convidado = (FloatingActionButton) findViewById(id_add_Convidado);
        btn_criarEvento = (Button) findViewById(id_criarEvento);
    }

    @Override
    protected void onStart(){
        super.onStart();

        // Listeners
        new MyEditTextDatePicker(self , id_dataInicio);
        new MyEditTextDatePicker(self , id_dataFim);
        new MyEditTextTimePicker(self , id_horaInicio);
        new MyEditTextTimePicker(self , id_horaFim);

        btn_add_Organizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFragment dialog = MyDialogFragment.newInstance(R.layout.activity_organizador);
                dialog.show(getFragmentManager(), "Org");
            }
        });

        btn_add_Convidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFragment dialog = MyDialogFragment.newInstance(R.layout.activity_convidado);
                dialog.show(getFragmentManager(),"Conv");

            }
        });

        btn_criarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
