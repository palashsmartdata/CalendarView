package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ShowEvents extends Dialog {
    TextView eventName, eventDescribe, eventDate;
    String name, desc, datee;

    public ShowEvents(@NonNull Context context, String name, String desc, String datee) {
        super(context);
        this.name = name;
        this.desc = desc;
        this.datee = datee;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);

        eventName = findViewById(R.id.openeditemtitle);
        eventDescribe = findViewById(R.id.openitemdes);
        eventDate = findViewById(R.id.openitemdate);


        eventName.setText(name);
        eventDescribe.setText(desc);
        eventDate.setText(datee);

    }
}
