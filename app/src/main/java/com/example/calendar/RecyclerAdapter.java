package com.example.calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.Database.CalendarEntity;
import com.example.calendar.Database.Daoo;
import com.example.calendar.Database.RoomDatabase;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<CalendarEntity> list;
    CalendarEntity calendarEntity;
    Daoo dao;
    Context context;

    public RecyclerAdapter(List<CalendarEntity> list, Context context) {
        this.list = list;
        this.context = context;
        dao = RoomDatabase.instance.getDao();
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemlist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        calendarEntity = list.get(position);
        holder.title.setText(calendarEntity.getEventName());
        holder.desc.setText(calendarEntity.getDesc());
        holder.date.setText(calendarEntity.getDate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.itemtitle);
            desc = itemView.findViewById(R.id.itemdes);
            date = itemView.findViewById(R.id.itemdate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CalendarEntity ce = list.get(getAdapterPosition());
                    ShowEvents dialog= new ShowEvents(context, ce.getEventName(), ce.getDesc(), ce.getDate());
                    dialog.getWindow();
                    dialog.show();
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder alertdailog = new AlertDialog.Builder(context);
                    alertdailog.setTitle("Delete");
                    alertdailog.setMessage("Are you Sure!!!!");
                    alertdailog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    CalendarEntity calendarEntity = list.get(getAdapterPosition());
                                    dao.delete(calendarEntity);
                                }
                            }).start();

                        }
                    });

                    alertdailog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(context, "Cancled Success", Toast.LENGTH_SHORT).show();

                        }
                    });
                    alertdailog.create();
                    alertdailog.show();
                    return false;
                }
            });
        }
    }
}
