package com.example.calendar.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Daoo {

    @Insert
    void setData(CalendarEntity data);


    @Query("select * from Calendar")
    LiveData<List<CalendarEntity>> getData();

    @Delete
    void delete(CalendarEntity data);
}

