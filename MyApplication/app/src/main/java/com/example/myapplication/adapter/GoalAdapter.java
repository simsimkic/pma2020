package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Goal;

import java.util.ArrayList;

public class GoalAdapter extends ArrayAdapter<Goal> {

    public GoalAdapter(Context context, ArrayList<Goal> friend){
        super(context, 0, friend);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Goal goal = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_goal, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView distance = convertView.findViewById(R.id.distance);
        TextView duration = convertView.findViewById(R.id.duration);
        TextView archived = convertView.findViewById(R.id.achieved);


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

        if(goal.getArchived()){
            archived.setText("Yes");
        }else{
            archived.setText("No");
        }

        return  convertView;
    }


}
