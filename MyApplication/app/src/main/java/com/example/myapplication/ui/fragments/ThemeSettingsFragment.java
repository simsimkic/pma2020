package com.example.myapplication.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class ThemeSettingsFragment extends Fragment {

    public ThemeSettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHeader();
        return inflater.inflate(R.layout.fragment_theme_settings, container, false);
    }

    private void setHeader() {
        TextView textView = getActivity().findViewById(R.id.toolbar_header);
        textView.setText(R.string.setting_theme_header);
    }

}