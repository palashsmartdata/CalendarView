package com.example.calendar.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

@Database(entities = {CalendarEntity.class}, version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract Daoo getDao();
    public static RoomDatabase instance;

    public static synchronized RoomDatabase getInstance(Context context) {

        if (instance==null)
        {
            instance = Room.databaseBuilder(context,RoomDatabase.class,"db_Calendar").build();
        }
        return instance;
    }

}
