package com.example.myapplication.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;

public class PrivacyOptionAdapter extends ArrayAdapter<PrivacyOptionItem> {

    public PrivacyOptionAdapter(Context context, ArrayList<PrivacyOptionItem> privacyOptionItems) {
        super(context, 0, privacyOptionItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.privacy_option_spinner, parent, false
            );
        }
        ImageView imageView = convertView.findViewById(R.id.private_option_icon);
        TextView textView = convertView.findViewById(R.id.private_option_text);

        PrivacyOptionItem currentItem = getItem(position);
        if (currentItem != null) {
            imageView.setImageResource(currentItem.getItemIcon());
            textView.setText(currentItem.getItemName());
        }
        return convertView;
    }
}
