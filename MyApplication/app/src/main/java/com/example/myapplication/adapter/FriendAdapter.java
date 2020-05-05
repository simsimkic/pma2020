package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Friend;

import java.util.ArrayList;

public class FriendAdapter extends ArrayAdapter<Friend> {

    public FriendAdapter(Context context, ArrayList<Friend> friend){
        super(context, 0, friend);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Friend friend = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_friend, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);

        name.setText(friend.getName());

        return  convertView;
    }

}
