package com.example.geova.agenda.Controls;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import android.widget.EditText;
import android.view.View;
import java.util.Calendar;
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
        updateDisplay();
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        TimePickerDialog dialog = new TimePickerDialog(_context, this, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);
        dialog.show();
    }

    // updates the date in the birth date EditText
    private void updateDisplay() {
        this._editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_hour).append(":").append(_minute).append(" "));
    }
}
