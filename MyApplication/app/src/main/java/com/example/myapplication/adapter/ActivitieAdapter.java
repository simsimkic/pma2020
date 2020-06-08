package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Activitie;

import java.util.ArrayList;

public class ActivitieAdapter extends ArrayAdapter<Activitie> {

    public ActivitieAdapter(Context context, ArrayList<Activitie> friend){
        super(context, 0, friend);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Activitie activitie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_activitie, parent, false);
        }

        TextView distance = convertView.findViewById(R.id.distance);
        TextView duration = convertView.findViewById(R.id.duration);
        TextView time = convertView.findViewById(R.id.time);
        ImageView imageView = convertView.findViewById(R.id.map_image);


        imageView.setImageBitmap(activitie.getEncodedMap());
        distance.setText(activitie.getDistance() + " km" );
        duration.setText(activitie.getDuration() + " sec");
        time.setText(activitie.getTime());



        return  convertView;
    }

}
