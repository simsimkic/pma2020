package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Friend;

import java.util.ArrayList;

public class FriendListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Friend> friends;
    public FriendListAdapter(Context applicationContext, ArrayList<Friend> friendsList){
        this.context = applicationContext;
        this.friends = friendsList;
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
           view = inflater.inflate(R.layout.view_friend_in_list, null);
       }else{
           view = convertView;
       }

        TextView name = (TextView) view.findViewById(R.id.friend_name);
        ImageView image = (ImageView)view.findViewById(R.id.friend_icon);
        ImageView imageAdd = (ImageView)view.findViewById(R.id.addFriend);

        name.setText(friends.get(position).getName());
        image.setImageResource(R.drawable.baseline_person_black_24dp);
        imageAdd.setImageResource(R.drawable.baseline_add_friend);

        return view;
    }
}
