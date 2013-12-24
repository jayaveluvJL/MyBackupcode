
package com.rajeshvijayakumar.timepicker;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends Activity implements OnClickListener {

    private Button mTimeButton;

    private Calendar mCalen;
    private int hourOfDay;
    private int minute;
    private int ampm;

    private static final int Time_PICKER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimeButton = (Button) findViewById(R.id.time_button);
        mCalen = Calendar.getInstance();
        hourOfDay = mCalen.get(Calendar.HOUR_OF_DAY);
        minute = mCalen.get(Calendar.MINUTE);
        ampm = mCalen.get(Calendar.AM_PM);
        mTimeButton.setOnClickListener(this);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case Time_PICKER_ID:
                return new TimePickerDialog(this, TimePickerListener,
                        hourOfDay, minute, false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener TimePickerListener =
            new TimePickerDialog.OnTimeSetListener() {

                // while dialog box is closed, below method is called.
                public void onTimeSet(TimePicker view, int hour,
                        int minute) {

                    mCalen.set(Calendar.HOUR_OF_DAY, hour);
                    mCalen.set(Calendar.MINUTE, minute);

                    int hour12format = mCalen.get(Calendar.HOUR);
                    hourOfDay = mCalen.get(Calendar.HOUR_OF_DAY);
                    minute = mCalen.get(Calendar.MINUTE);
                    ampm = mCalen.get(Calendar.AM_PM);
                    String ampmStr = (ampm == 0) ? "AM" : "PM";
                    // Set the Time String in Button
                    mTimeButton.setText(hour12format + " : " + minute + " / " + ampmStr);
                }

            };

    @Override
    public void onClick(View v) {

        showDialog(Time_PICKER_ID);

    }

}
