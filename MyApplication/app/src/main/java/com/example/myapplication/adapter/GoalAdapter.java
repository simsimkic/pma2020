package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Goal;
import com.example.myapplication.ui.ProfileActivity;

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
//        TextView archived = convertView.findViewById(R.id.achieved);
        TextView end_time = convertView.findViewById(R.id.end_time);
//        ImageView btn_remove = (ImageView) convertView.findViewById(R.id.remove);
//        btn_remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        ImageView btn_edit = (ImageView) convertView.findViewById(R.id.edit);
//        btn_remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


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

//        if(goal.getArchived()){
//            archived.setText("Yes");
//        }else{
//            archived.setText("No");
//        }
        end_time.setText(goal.getEnd_time().split(". ")[0]);

        return  convertView;
    }


}
