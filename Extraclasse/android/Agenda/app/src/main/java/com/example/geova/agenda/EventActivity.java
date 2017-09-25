package com.example.geova.agenda;

import com.facebook.FacebookSdk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geova.agenda.Models.Evento;
import com.facebook.share.model.ShareLinkContent;
import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventActivity extends SwipeBackActivity {

    // IDs
    private int id_txtEvento = R.id.txt_evento_act;
    private int id_txtInfoEvento = R.id.txt_infoEvento_act;
    private int id_txtOrganizador = R.id.txt_organizador_act;
    private int id_txtOrganizadorInfo = R.id.txt_organizadorInfo_act;
    private int id_txtConvidados = R.id.txt_convidados_act;
    private int id_txtConvidadosInfo = R.id.txt_convidadosInfo_act;

    private int id_agenda = R.id.btn_agenda;
    private int id_whatsapp = R.id.btn_whatsapp;
    private int id_facebook = R.id.btn_facebook;

    // TextViews
    TextView txt_evento_act;
    TextView txt_infoEvento_act;
    TextView txt_organizador_act;
    TextView txt_organizadorInfo_act;
    TextView txt_convidados_act;
    TextView txt_convidadosInfo_act;

    // Buttons
    FloatingActionButton btn_agenda;
    FloatingActionButton btn_whatsapp;
    FloatingActionButton btn_facebook;

    private static final int AGENDA = 1;
    private static final int WHATSAPP = 2;
    private static final int FACEBOOK = 3;

    Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        setDragEdge(SwipeBackLayout.DragEdge.LEFT);

        btn_agenda = (FloatingActionButton) findViewById(id_agenda);
        btn_whatsapp = (FloatingActionButton) findViewById(id_whatsapp);
        btn_facebook = (FloatingActionButton) findViewById(id_facebook);

        Intent intent = getIntent();
        evento = (Evento)intent.getSerializableExtra("Evento");

        setEventoTexts(evento);
    }

    @Override
    protected void onStart(){
        super.onStart();

        btn_agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = getResources().getString(R.string.invited);
                try {
                    createEventAgenda(view, evento, message);
                } catch (ParseException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.agenda_error),
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        btn_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = txt_evento_act.getText().toString() + "\n"
                        + txt_infoEvento_act.getText().toString() + "\n"
                        + txt_organizador_act.getText().toString() + "\n"
                        + txt_organizadorInfo_act.getText().toString() + "\n"
                        + getResources().getString(R.string.invited);
                createEventWhatsApp(view, message);
            }
        });
        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEventFacebook(view, "FB");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast toast;

        if (requestCode == EventActivity.AGENDA) {
            toast = Toast.makeText(getApplicationContext(), getString(R.string.agenda_success),
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        if (requestCode == EventActivity.WHATSAPP) {
            toast = Toast.makeText(getApplicationContext(), getString(R.string.whatsapp_success),
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        if (requestCode == EventActivity.FACEBOOK) {
            toast = Toast.makeText(getApplicationContext(), getString(R.string.facebook_success),
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void createEventAgenda(View view, Evento evento, String message) throws ParseException {
        SimpleDateFormat parseDateTime  = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        String dataHoraInicial = evento.getDataInicial() + " " + evento.getHoraInicial();
        String dataHoraFinal = evento.getDataFinal() + " " + evento.getHoraFinal();

        Date inicial = parseDateTime.parse(dataHoraInicial);
        Date fim = parseDateTime.parse(dataHoraFinal);

        dataHoraInicial = formatDateTime.format(inicial);
        dataHoraFinal   = formatDateTime.format(fim);

        Calendar beginTime = Calendar.getInstance();
        Calendar endTime   = Calendar.getInstance();

        beginTime.set(EventActivity.getYear(dataHoraInicial), EventActivity.getMonth(dataHoraInicial),
                EventActivity.getDay(dataHoraInicial), EventActivity.getHour(dataHoraInicial),
                EventActivity.getMinute(dataHoraInicial));

        endTime.set(EventActivity.getYear(dataHoraFinal), EventActivity.getMonth(dataHoraFinal),
                EventActivity.getDay(dataHoraFinal), EventActivity.getHour(dataHoraFinal),
                EventActivity.getMinute(dataHoraFinal));

        StringBuilder emailConvidados = new StringBuilder();

        for(int i=0; i<evento.getConvidados().size(); i++){
            emailConvidados.append(evento.getConvidados().get(i).getEmail());
        }

        if (emailConvidados.length() > 0) {
            emailConvidados.deleteCharAt(emailConvidados.length() - 1);
        }

        Intent eventIntent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, evento.getNome())
                .putExtra(CalendarContract.Events.DESCRIPTION, message)
                .putExtra(CalendarContract.Events.OWNER_ACCOUNT, evento.getOrganizador().getNome())
                .putExtra(CalendarContract.Events.ORGANIZER, evento.getOrganizador().getEmail())
                .putExtra(Intent.EXTRA_EMAIL, emailConvidados.toString())
                .putExtra(CalendarContract.Events.ALL_DAY, 0);
        startActivityForResult(eventIntent, EventActivity.AGENDA);
    }

    public void createEventWhatsApp(View view, String message){
        Intent wppIntent = new Intent(Intent.ACTION_SEND);
        wppIntent.setType("text/plain");
        wppIntent.putExtra(Intent.EXTRA_TEXT, message);
        wppIntent.setPackage("com.whatsapp");
        startActivityForResult(wppIntent, EventActivity.WHATSAPP);
    }

    public void createEventFacebook(View view, String message){
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("Game Result Highscore")
                .setContentDescription("My new highscore is !!")
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();
        String urlToShare;

        try {
            urlToShare = content.getContentUrl().toString();
        }catch(NullPointerException e){
            urlToShare = "https://9gag.com/";
        }

        try {
            Intent intent1 = new Intent();
            intent1.setClassName("com.facebook.katana", "com.facebook.katana.activity.composer.ImplicitShareIntentHandler");
            intent1.setAction("android.intent.action.SEND");
            intent1.setType("text/plain");
            intent1.putExtra("android.intent.extra.TEXT", urlToShare);
            startActivity(intent1);
        } catch (Exception e) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
            startActivity(intent);
        }
    }

    public void setEventoTexts(Evento evento){
        txt_evento_act = (TextView) findViewById(id_txtEvento);
        txt_infoEvento_act = (TextView) findViewById(id_txtInfoEvento);
        txt_organizador_act = (TextView) findViewById(id_txtOrganizador);
        txt_organizadorInfo_act = (TextView) findViewById(id_txtOrganizadorInfo);
        txt_convidados_act = (TextView) findViewById(id_txtConvidados);
        txt_convidadosInfo_act = (TextView) findViewById(id_txtConvidadosInfo);

        txt_evento_act.setText(getResources().getString(R.string.evento) + ": " + evento.getNome());

        txt_infoEvento_act.setText(getResources().getString(R.string.data_inicio) + ": " + evento.getDataInicial() + " "
                + evento.getHoraInicial() + "\n"
                + getResources().getString(R.string.data_fim) + ": " + evento.getDataFinal() + " "
                + evento.getHoraFinal());

        if(evento.getOrganizador() == null){
            txt_organizador_act.setText(getResources().getString(R.string.organizador) + ": ");

            txt_organizadorInfo_act.setText(getResources().getString(R.string.nenhumOrganizador));
        } else{
            txt_organizador_act.setText(getResources().getString(R.string.organizador) + ": " + evento.getOrganizador().getNome());

            txt_organizadorInfo_act.setText(getResources().getString(R.string.email) + ": " + evento.getOrganizador().getEmail() + "\n"
                    + getResources().getString(R.string.telefone) + ": " + evento.getOrganizador().getTelefone());
        }

        txt_convidados_act.setText(getResources().getString(R.string.convidados) + ": ");

        if(evento.getConvidados().isEmpty()){
            txt_convidadosInfo_act.setText(getResources().getString(R.string.nenhumConvidado));
        } else{
            String nomeConvidados = "";
            for(int i=0; i<evento.getConvidados().size(); i++){
                nomeConvidados += getResources().getString(R.string.nome) + ": " + evento.getConvidados().get(i).getNome() + "\n";
            }

            txt_convidadosInfo_act.setText(nomeConvidados);
        }
    }

    public static int getYear(String dateTime) {
        return Integer.parseInt(dateTime.substring(0, dateTime.indexOf("/")));
    }

    public static int getMonth(String dateTime) {
        return Integer.parseInt(dateTime.substring(dateTime.indexOf("/") + 1,
                dateTime.lastIndexOf("/")));
    }

    public static int getDay(String dateTime) {
        return Integer.parseInt(dateTime.substring(dateTime.lastIndexOf("/") + 1,
                dateTime.indexOf(" ")));
    }

    public static int getHour(String dateTime) {
        return Integer.parseInt(dateTime.substring(dateTime.indexOf(" ") + 1,
                dateTime.indexOf(":")));

    }

    public static int getMinute(String dateTime) {
        return Integer.parseInt(dateTime.substring(dateTime.indexOf(":") + 1));
    }
}
