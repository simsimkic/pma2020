package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dto.response.CommentResponseDto;
import com.example.myapplication.model.Activitie;
import com.example.myapplication.model.Activity;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends BaseAdapter {
    Context context;
    List<CommentResponseDto> commentDtos;

    public CommentAdapter(Context context, List<CommentResponseDto> commentDtos) {
        this.context = context;
        this.commentDtos = commentDtos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<CommentResponseDto> getCommentDtos() {
        return commentDtos;
    }

    public void setCommentDtos(ArrayList<CommentResponseDto> commentDtos) {
        this.commentDtos = commentDtos;
    }

    @Override
    public int getCount() {
        return commentDtos.size();
    }

    @Override
    public Object getItem(int position) {
        return commentDtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return commentDtos.get(position).getPost_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentResponseDto commentResponseDto = (CommentResponseDto) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_item, parent, false);
        }


        TextView name = convertView.findViewById(R.id.name);
        TextView date = convertView.findViewById(R.id.date);
        TextView comment = convertView.findViewById(R.id.comment);

        name.setText(commentResponseDto.getUser());
        date.setText(commentResponseDto.getDate());
        comment.setText(commentResponseDto.getComment());
        ImageView icon = convertView.findViewById(R.id.icon_friend);
        icon.setImageResource(R.drawable.baseline_person_black_24dp);

        //dodavanje komentara



        return  convertView;
    }
}
