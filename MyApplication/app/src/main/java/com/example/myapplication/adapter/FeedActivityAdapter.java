package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dto.response.PostResponse;
import com.example.myapplication.model.Activitie;

import java.util.ArrayList;

public class FeedActivityAdapter extends BaseAdapter {
    Context context;
    ArrayList<PostResponse> posts;


    public FeedActivityAdapter(Context context, ArrayList<PostResponse> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
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
            view = inflater.inflate(R.layout.feed_activity_item, null);
        }else{
            view = convertView;
        }

        PostResponse p = posts.get(position);
        TextView user =(TextView)view.findViewById(R.id.user);
        TextView description = (TextView)view.findViewById(R.id.description);
        TextView duration = (TextView)view.findViewById(R.id.duration);
        TextView distance = (TextView)view.findViewById(R.id.distance);
        TextView time = (TextView)view.findViewById(R.id.time);
        TextView likes = (TextView)view.findViewById(R.id.likes);
        TextView comment = (TextView)view.findViewById(R.id.comments);
        ImageView icon = (ImageView)view.findViewById(R.id.icon_user);
        icon.setImageResource(R.drawable.baseline_person_black_24dp);
        ImageView map = (ImageView)view.findViewById(R.id.map_image);

        map.setImageBitmap(decodeBase64(p.getBitmap()));
        user.setText(p.getUser());
        description.setText(p.getDescription());
        duration.setText(Double.toString(p.getDuration()) + " min");
        distance.setText(Double.toString(p.getDistance()) + " km");
        time.setText(p.getDate());
        likes.setText(Integer.toString(p.getLike_num()));
        comment.setText(Integer.toString(p.getComment_num()));



        return view;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
