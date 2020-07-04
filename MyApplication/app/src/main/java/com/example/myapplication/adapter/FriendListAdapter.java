package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.model.Friend;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FriendListAdapter extends BaseAdapter {
    Context context;
    ArrayList<FriendResponse> friends;
    ArrayList<FriendResponse> friendsFiltered;
    public FriendListAdapter(Context applicationContext, ArrayList<FriendResponse> friendsList){
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
        ImageView image_add = (ImageView)view.findViewById(R.id.add_friend_icon);

        name.setText(friends.get(position).getName());
        image.setImageResource(R.drawable.baseline_person_black_24dp);

        if(friends.get(position).getFriend()==1){
            image_add.setImageResource(R.drawable.ic_group);
        }else if(friends.get(position).getFriend()==0){
            image_add.setImageResource(R.drawable.ic_add_friend);
        }else if(friends.get(position).getFriend()==2) {
            //poslat zahtev
            image_add.setImageResource(R.drawable.ic_send_request);
        }
         else
        {
            //zahtev primljen, treba da odgovori
            image_add.setImageResource(R.drawable.ic_accept_request);
        }

        return view;
    }

   public void filter(String text){
        text = text.toLowerCase(Locale.getDefault());
        friendsFiltered = new ArrayList<>();
        if(text.length() == 0){
            friendsFiltered.addAll(friends);
        }else {
            for (FriendResponse f : friends){
                if(f.getName().toLowerCase(Locale.getDefault()).contains(text)){
                    friendsFiltered.add(f);
                }
            }
        }
        notifyDataSetChanged();
   }

    public interface FriendsAdapterListener{
        void onContactSelected(FriendResponse friendResponse);
    }
}
