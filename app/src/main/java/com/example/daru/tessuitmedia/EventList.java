package com.example.daru.tessuitmedia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 15/07/2016.
 */
public class EventList extends ArrayAdapter<ResponEvent> {
    private final Context context;
    private final ArrayList<ResponEvent> namaTanggalEvent;

    public EventList(Context context, ArrayList<ResponEvent> namaTanggalEvent) {
        super(context, R.layout.activity_event_list, namaTanggalEvent);
        this.context = context;
        this.namaTanggalEvent = namaTanggalEvent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View EventView = inflater.inflate(R.layout.activity_event_list, parent, false);


        TextView namaEventView = (TextView) EventView.findViewById(R.id.nama_event);
        TextView tanggalEventView = (TextView) EventView.findViewById(R.id.tanggal_event);
        ImageView imageEventView = (ImageView) EventView.findViewById(R.id.image_event);


        namaEventView.setText(namaTanggalEvent.get(position).getNama());
        tanggalEventView.setText(namaTanggalEvent.get(position).getTanggal());
        if (namaTanggalEvent.get(position).getNama().equals("acara buka bersama")) {
            imageEventView.setImageResource(R.drawable.bukber);
        }
        else if (namaTanggalEvent.get(position).getNama().equals("acara liburan")) {
            imageEventView.setImageResource(R.drawable.liburan);
        }
        else if (namaTanggalEvent.get(position).getNama().equals("acara seminar")) {
            imageEventView.setImageResource(R.drawable.seminar);
        }
        else if (namaTanggalEvent.get(position).getNama().equals("acara ulang tahun")) {
            imageEventView.setImageResource(R.drawable.ultah);
        }
        else {
            imageEventView.setImageResource(R.drawable.musik);
        }

        return EventView;
    }
}
