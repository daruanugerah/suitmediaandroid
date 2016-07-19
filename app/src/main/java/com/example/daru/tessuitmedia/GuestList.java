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
public class GuestList extends ArrayAdapter<ResponGuest> {
    private final Context context;
    private final ArrayList<ResponGuest> listguest;

    public GuestList(Context context, ArrayList<ResponGuest> listguest) {
        super(context, R.layout.activity_guest_list, listguest);
        this.context = context;
        this.listguest = listguest;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Bind row guest view dengan inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View GuestView = inflater.inflate(R.layout.activity_guest_list, parent, false);

        // Bind komponen widget row view layout activity
        ImageView imageGuestView = (ImageView) GuestView.findViewById(R.id.image_guest);
        TextView nameGuestView = (TextView) GuestView.findViewById(R.id.nama_guest);

        // Setting komponen widget pembentuk row guest
        imageGuestView.setImageResource(R.drawable.tamu);
        nameGuestView.setText(listguest.get(position).getNama());

        return GuestView;
    }
}
