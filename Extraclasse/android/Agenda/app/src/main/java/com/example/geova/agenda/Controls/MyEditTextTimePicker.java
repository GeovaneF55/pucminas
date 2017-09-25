package com.example.geova.agenda.Controls;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import android.widget.EditText;
import android.view.View;

import com.example.geova.agenda.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by geova on 12/09/2017.
 */

public class MyEditTextTimePicker implements View.OnClickListener, TimePickerDialog.OnTimeSetListener{
    private EditText _editText;
    private int _hour;
    private int _minute;
    private Context _context;

    public MyEditTextTimePicker(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this._editText = (EditText)act.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = context;
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        _hour = hour;
        _minute = minute;

        try {
            updateDisplay();
        } catch (ParseException e) {
            this._editText.setError(null);
        }
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        TimePickerDialog dialog = new TimePickerDialog(_context, this, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);
        dialog.show();
    }

    // updates the date in the birth date EditText
    private void updateDisplay() throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        StringBuilder stringBuilder = new StringBuilder().append(_hour).append(":").append(_minute).append(" ");
        Date hora = formatador.parse(stringBuilder.toString());

        this._editText.setText(formatador.format(hora));
        this._editText.setError(null);
    }
}
