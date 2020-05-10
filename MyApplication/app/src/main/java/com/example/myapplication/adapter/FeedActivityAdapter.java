package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Activitie;
import com.example.myapplication.model.Activity;
import com.example.myapplication.mokap_data.Activities;

import java.util.ArrayList;

public class FeedActivityAdapter extends BaseAdapter {
    Context context;
    ArrayList<Activitie> activities;


    public FeedActivityAdapter(Context context, ArrayList<Activitie> activities) {
        this.context = context;
        this.activities = activities;
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int position) {
        return activities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.feed_activity_item, null);
        }else{
            view = convertView;
        }

        Activitie a = Activities.getActivitie().get(position);

        TextView user = (TextView)view.findViewById(R.id.user);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView duration = (TextView)view.findViewById(R.id.duration);
        TextView distance = (TextView)view.findViewById(R.id.distance);
        TextView time = (TextView)view.findViewById(R.id.time);
        TextView likes = (TextView)view.findViewById(R.id.likes);
        TextView comment = (TextView)view.findViewById(R.id.comments);
        ImageView icon = (ImageView)view.findViewById(R.id.icon_user);
        icon.setImageResource(R.drawable.baseline_person_black_24dp);

        user.setText(a.getUser());
        name.setText(a.getName());
        duration.setText(a.getDuration().toString());
        distance.setText(a.getDistance().toString());
        time.setText(a.getTime());
        likes.setText(a.getLikes());
        comment.setText(a.getComment());



        return view;
    }
}
