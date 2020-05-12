package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Activity;
import com.example.myapplication.model.State;

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

    @SuppressLint("ResourceAsColor")
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
        ImageView state = (ImageView)view.findViewById(R.id.activity_state);
        TextView location = (TextView)view.findViewById(R.id.location);
        LinearLayout btn_layout = (LinearLayout)view.findViewById(R.id.button_layout);
        description.setText(activities.get(position).getDescription());
        location.setText(activities.get(position).getLocation());

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(R.color.primary);
        gradientDrawable.setCornerRadius(10);

        if (activities.get(position).getState() == State.ACCEPT){
            state.setImageResource(R.drawable.checkmark);
            Button cancel =  new Button(context);
            cancel.setText("Cancel");
            cancel.setTextSize(16);

//            cancel.setBackground(gradientDrawable);

//            cancel.setBackgroundColor(R.color.primary);
            btn_layout.addView(cancel);

        }else if(activities.get(position).getState() == State.SENT){
            state.setImageResource(R.drawable.waiting);
            Button cancel =  new Button(context);
            cancel.setText("Cancel");
            cancel.setTextSize(16);
//            cancel.setBackground(gradientDrawable);
//            cancel.setBackgroundColor(R.color.primary);

            btn_layout.addView(cancel);
        }else if(activities.get(position).getState() == State.RECEIVED){
            state.setImageResource(R.drawable.question_mark);
            Button accept =  new Button(context);
            accept.setText("Accept");
            accept.setTextSize(16);
//            accept.setBackground(gradientDrawable);
//            accept.setBackgroundColor(R.color.primary);
            btn_layout.addView(accept);

            Button cancel =  new Button(context);
            cancel.setText("Decline");
            cancel.setTextSize(16);
//            cancel.setBackground(gradientDrawable);
//            cancel.setBackgroundColor(R.color.primary);


            btn_layout.addView(cancel);
        }
        return view;
    }
}
