package com.example.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.calendar.Database.CalendarEntity;
import com.example.calendar.Database.Daoo;
import com.example.calendar.Database.RoomDatabase;

import java.util.List;

public class ViewModelClasss extends ViewModel {

    Daoo dao;

    public ViewModelClasss() {

        dao = RoomDatabase.instance.getDao();
    }
    public LiveData<List<CalendarEntity>> getData()
    {
        return dao.getData();
    }
}
