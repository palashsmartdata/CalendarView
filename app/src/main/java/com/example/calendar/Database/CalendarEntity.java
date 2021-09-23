package com.example.calendar.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Calendar")
public class CalendarEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "Eventname")
    String Eventname;

    @ColumnInfo(name = "Desc")
    String Desc;

    @ColumnInfo(name = "Date")
    String Date;

    public String getEventName() {
        return Eventname;
    }

    public void setEventName(String event) {
        Eventname = event;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
