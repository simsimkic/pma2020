package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dto.response.NotificationResponse;
import com.example.myapplication.model.Notification;
import com.example.myapplication.model.NotificationType;
import com.example.myapplication.mokap_data.Notifications;

import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter {
    Context context;
    ArrayList<NotificationResponse> notifications;

    public NotificationAdapter(Context context, ArrayList<NotificationResponse> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.notification_item, null);
        }else{
            view = convertView;
        }

        NotificationResponse n = this.notifications.get(position);

        ImageView icon = view.findViewById(R.id.notification_icon);
        TextView description= view.findViewById(R.id.description);
        LinearLayout btn_layout = (LinearLayout)view.findViewById(R.id.button_layout);
        LinearLayout activity_info_layout = view.findViewById(R.id.activity_info);

        if (n.getType() == NotificationType.COMMENT){
            icon.setImageResource(R.drawable.ic_comment);
            description.setText(n.getDescription());
        }else if (n.getType() == NotificationType.LIKE){
            icon.setImageResource(R.drawable.ic_like);
            description.setText(n.getDescription());
        }else if (n.getType() == NotificationType.ACCEPT_FRIEND){
            icon.setImageResource(R.drawable.ic_accept_friend);
            description.setText(n.getDescription());
        }else if (n.getType() == NotificationType.ACCEPT_INVITATION){
            icon.setImageResource(R.drawable.checkmark);
            description.setText(n.getDescription());
        }else if (n.getType() == NotificationType.SEND_FRIEND){
            icon.setImageResource(R.drawable.ic_sent_request);
            description.setText(n.getDescription());
            Button accept =  new Button(context);
            accept.setText("Accept");
            accept.setTextSize(16);
            btn_layout.addView(accept);

            Button cancel =  new Button(context);
            cancel.setText("Decline");
            cancel.setTextSize(16);
            btn_layout.addView(cancel);
        }else if (n.getType() ==  NotificationType.SEND_INVITATION){
            icon.setImageResource(R.drawable.question_mark);
            description.setText(n.getDescription());


//
//            TextView locationView = view.findViewById(R.id.location);
//            locationView.setText(n.getActivity().getLocation());
//
//            TextView time = view.findViewById(R.id.date);
//            time.setText(n.getActivity().getDate());


            Button accept =  new Button(context);
            accept.setText("Accept");
            accept.setTextSize(16);
            btn_layout.addView(accept);

            Button cancel =  new Button(context);
            cancel.setText("Decline");
            cancel.setTextSize(16);
            btn_layout.addView(cancel);
        }

        return view;
    }
}
