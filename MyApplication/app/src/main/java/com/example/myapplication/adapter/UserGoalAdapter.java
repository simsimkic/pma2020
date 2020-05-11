package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Goal;
import com.example.myapplication.mokap_data.Goals;

import java.util.ArrayList;

public class UserGoalAdapter extends BaseAdapter {
    Context context;
    ArrayList<Goal> goals;

    public UserGoalAdapter(Context context, ArrayList<Goal> goals) {
        this.context = context;
        this.goals = goals;
    }

    @Override
    public int getCount() {
        return goals.size();
    }

    @Override
    public Object getItem(int position) {
        return goals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Goal goal = Goals.getGolas().get(position);
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.user_goals_item, null);
        }else {
            view = convertView;
        }

        TextView name = view.findViewById(R.id.name);
        TextView distance = view.findViewById(R.id.distance);
        TextView duration = view.findViewById(R.id.duration);
        TextView archived = view.findViewById(R.id.achieved);


        name.setText(goal.getName());
        if(goal.getDistance() == null){
            distance.setText("Not set" );
        }else{
            distance.setText(goal.getDistance() + " km" );
        }
        if(goal.getDuration() == null){
            duration.setText("Not set");
        }else{
            duration.setText(goal.getDuration() + " h");
        }


        return  view;
    }

}

