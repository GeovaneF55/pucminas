package com.example.geova.agenda;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geova.agenda.Controls.MyEditTextDatePicker;
import com.example.geova.agenda.Controls.MyEditTextTimePicker;
import com.example.geova.agenda.Controls.MyDialogFragment;
import com.example.geova.agenda.Models.Convidado;
import com.example.geova.agenda.Models.Evento;
import com.example.geova.agenda.Models.Organizador;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    private Evento event;
    private Organizador organizador;
    private ArrayList<Convidado> convidados;

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
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public final static String EXTRA_MESSAGE = "com.example.geova.MESSAGE";

    public void sendMessage(View view) {
        if(validaCampos()){
            event = new Evento();
            try {
                preencheEvento(event);

                Intent intent = new Intent(this, EventActivity.class);
                intent.putExtra("Evento", event);
                startActivity(intent);
            } catch (ParseException e) {
                Intent intent = new Intent(this, EventActivity.class);
                String message = getResources().getString(R.string.erro);
                intent.putExtra(EXTRA_MESSAGE, e.toString());
                startActivity(intent);
            }
        }
    }

    public void preencheEvento(Evento event) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");

        Date horaInicial = formatador.parse(toText(edt_horaInicio));
        Date horaFinal = formatador.parse(toText(edt_horaFim));

        event.setNome(toText(edt_nomeEvento));
        event.setDataInicial(dateFormat.parse(toText(edt_dataInicio)));
        event.setHoraInicial(new Time(horaInicial.getTime()));
        event.setDataFinal(dateFormat.parse(toText(edt_dataFim)));
        event.setHoraFinal(new Time(horaFinal.getTime()));
        event.setOrganizador(this.organizador);
        /*for() {
            event.pushConvidados();
        }*/
    }

    public boolean validaCampos() {
        boolean valid = false;

        boolean emptyNome = TextUtils.isEmpty(toText(edt_nomeEvento));
        boolean emptyDataInicio =  TextUtils.isEmpty(toText(edt_dataInicio));
        boolean emptyHoraInicio =  TextUtils.isEmpty(toText(edt_horaInicio));
        boolean emptyDataFim =  TextUtils.isEmpty(toText(edt_dataFim));
        boolean emptyHoraFim =  TextUtils.isEmpty(toText(edt_horaFim));

        if(emptyNome) {
            edt_nomeEvento.setError(getResources().getString(R.string.erro_Nome));
        }

        if(emptyDataInicio) {
            edt_dataInicio.setError(getResources().getString(R.string.erro_DataInicio));
        }

        if(emptyHoraInicio) {
            edt_horaInicio.setError(getResources().getString(R.string.erro_HoraInicio));
        }

        if(emptyDataFim) {
            edt_dataFim.setError(getResources().getString(R.string.erro_DataFim));
        }

        if(emptyHoraFim)
            edt_horaFim.setError(getResources().getString(R.string.erro_HoraFim));

        if(!emptyNome && !emptyDataInicio && !emptyHoraInicio && !emptyDataFim && !emptyHoraFim) {
            valid = true;
        }
        return valid;
    }

    private String toText(EditText editText){
        return editText.getText().toString();
    }
}
