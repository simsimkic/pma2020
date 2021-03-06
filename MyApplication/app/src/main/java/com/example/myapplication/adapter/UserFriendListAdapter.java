package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.model.Friend;

import java.util.ArrayList;

public class UserFriendListAdapter extends BaseAdapter {
    Context context;
    ArrayList<FriendResponse> friends;

    public UserFriendListAdapter(Context context, ArrayList<FriendResponse> friends) {
        this.context = context;
        this.friends = friends;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
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
            view = inflater.inflate(R.layout.item_user_friends, null);
        }else{
            view = convertView;
        }

        TextView name = (TextView) view.findViewById(R.id.friend_name);
        ImageView icon_view = view.findViewById(R.id.friend_icon);

        name.setText(friends.get(position).getName());
        icon_view.setImageResource(R.drawable.baseline_person_black_48);

        return view;

    }
}
