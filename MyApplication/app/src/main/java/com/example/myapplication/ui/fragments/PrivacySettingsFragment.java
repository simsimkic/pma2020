package com.example.myapplication.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class PrivacySettingsFragment extends Fragment {

    public PrivacySettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privacy_settings, container, false);
        privacyOptionsValues(view);
        setHeader();
        return view;
    }

    private void setHeader() {
        TextView textView = getActivity().findViewById(R.id.toolbar_header);
        textView.setText("Privacy settings");
    }

    private void privacyOptionsValues(View view) {
        String[] privacyOptions = { "Everyone", "Friends", "Only me"};
        Spinner persInfoSpinner = view.findViewById(R.id.pers_info_spinner);
        Spinner sharedActSpinner = view.findViewById(R.id.shared_act_spinner);
        Spinner goalsSpinner = view.findViewById(R.id.goals_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, privacyOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        persInfoSpinner.setAdapter(adapter);
        sharedActSpinner.setAdapter(adapter);
        goalsSpinner.setAdapter(adapter);
    }

}