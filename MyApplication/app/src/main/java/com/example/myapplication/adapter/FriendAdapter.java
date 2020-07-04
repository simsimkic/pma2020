package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.model.Friend;

import java.util.ArrayList;

public class FriendAdapter extends BaseAdapter {
    Context context;
    ArrayList<FriendResponse> friends;

    public FriendAdapter(Context context, ArrayList<FriendResponse> friends) {
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
//        FriendResponse friend = (FriendResponse) getItem(position);

        View view;
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_friend, null);
        }else{
            view = convertView;
        }

        TextView name = view.findViewById(R.id.name);

        name.setText(friends.get(position).getName());

        return  view;
    }

}
