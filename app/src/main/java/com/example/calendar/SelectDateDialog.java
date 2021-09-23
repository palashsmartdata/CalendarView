package com.example.calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calendar.Database.CalendarEntity;
import com.example.calendar.Database.Daoo;
import com.example.calendar.Database.RoomDatabase;


public class SelectDateDialog extends Dialog {

    EditText eventname, eventdesc;
    Button setevent;
    Context context;
    String date;
    CalendarEntity data;
    Daoo dao;

    public SelectDateDialog(@NonNull Context context, String date) {
        super(context);
        this.context = context;
        this.date = date;
        dao = RoomDatabase.instance.getDao();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_date_dialog);


        eventname = findViewById(R.id.eventname);
        eventdesc = findViewById(R.id.eventDes);
        setevent = findViewById(R.id.setbtn);

        setevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        data = new CalendarEntity();
                        data.setDate(date);
                        data.setEventName(eventname.getText().toString());
                        data.setDesc(eventdesc.getText().toString());
                        dao.setData(data);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(context, "Inserted!", Toast.LENGTH_SHORT).show();

                            }

                        });


                    }
                }).start();
            }
        });
    }
}
