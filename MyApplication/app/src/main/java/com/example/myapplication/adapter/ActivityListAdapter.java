package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Activity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Activity> activities;

    public ActivityListAdapter(Context applicationContext, ArrayList<Activity> activities){
        this.context = applicationContext;
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
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_activity_in_list, null);

        }else{
            view = convertView;
        }

        TextView description = (TextView)view.findViewById(R.id.description);
        TextView location = (TextView)view.findViewById(R.id.location_time);

        description.setText(activities.get(position).getDescription());
        location.setText(activities.get(position).getLocation());

        return view;
    }
}
