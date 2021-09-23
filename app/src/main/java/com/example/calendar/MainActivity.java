package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calendar.Database.CalendarEntity;
import com.example.calendar.Database.Daoo;
import com.example.calendar.Database.RoomDatabase;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CalendarView cv;
    TextView event, allevents;
    ImageButton set;
    RecyclerView rv;
    RecyclerAdapter ra;
    Activity activity;
    Daoo dao;
    ViewModelClasss model;
    String date = "";
    List<CalendarEntity> list;
    LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv = findViewById(R.id.calenderview);
        event = findViewById(R.id.events);
        set = findViewById(R.id.SetEventbtn);
        allevents = findViewById(R.id.showallevents);
        getSupportActionBar().setSubtitle("Calendar App");
        activity = this;
        dao = RoomDatabase.getInstance(this).getDao();
        linearLayout = findViewById(R.id.linearmain);
        Snackbar.make(linearLayout, "Long press to delete event", BaseTransientBottomBar.LENGTH_LONG).show();

        model = ViewModelProviders.of(this).get(ViewModelClasss.class);
        rv = findViewById(R.id.Recyclerview);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        list = new ArrayList<>();


        model.getData().observe(this, new Observer<List<CalendarEntity>>() {
            @Override
            public void onChanged(List<CalendarEntity> calendarEntities) {
                ra = new RecyclerAdapter(calendarEntities, MainActivity.this);
                rv.setAdapter(ra);
            }
        });

        allevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getData().observe(MainActivity.this, new Observer<List<CalendarEntity>>() {
                    @Override
                    public void onChanged(List<CalendarEntity> calendarEntities) {
                        list = calendarEntities;
                        ra = new RecyclerAdapter(list, MainActivity.this);
                        rv.setAdapter(ra);
                    }
                });
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDateDialog dateDialog = new SelectDateDialog(MainActivity.this, date);
               // dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDialog.show();
            }
        });






        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               // set.setVisibility(View.VISIBLE);

                date = String.valueOf(dayOfMonth + "/" + (month + 1) + "/" + year);
                String date2 ;
                list.clear();
                date2 = String.valueOf(dayOfMonth + "/" + (month+1) + "/" + year);
                setDateLists(date2);
            }
        });


    }

    private void setDateLists(String date2) {
        list.clear();

        model.getData().observe(this, new Observer<List<CalendarEntity>>() {
            @Override
            public void onChanged(List<CalendarEntity> calendarEntities) {
                if (!date2.equals("")) {
                    for (int i = 0; i < calendarEntities.size(); i++) {
                        CalendarEntity ce = calendarEntities.get(i);
                        if (date2.equals(ce.getDate())) {
                            list.add(ce);
                            break;
                        } else {
                            list.clear();
                            ra.notifyDataSetChanged();
                        }
                    }
                }


                else
                    list = calendarEntities;
                ra = new RecyclerAdapter(list, MainActivity.this);
                rv.setAdapter(ra);


            }
        });

    }
}