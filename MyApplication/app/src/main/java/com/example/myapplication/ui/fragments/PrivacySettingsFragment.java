package com.example.myapplication.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.settings.PrivacyOptionAdapter;
import com.example.myapplication.settings.PrivacyOptionItem;

import java.util.ArrayList;

public class PrivacySettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ArrayList<PrivacyOptionItem> options;
    private PrivacyOptionAdapter optionAdapter;
    private Spinner persInfoSpinner;
    private Spinner sharedActSpinner;
    private Spinner goalsSpinner;

    public PrivacySettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privacy_settings, container, false);
        setHeader();
        initOptions();
        findSpinners(view);
        setSpinnerOptions();
        return view;
    }

    private void setHeader() {
        TextView textView = getActivity().findViewById(R.id.toolbar_header);
        textView.setText(R.string.setting_privacy_header);
    }

    private void initOptions() {
        options = new ArrayList<>();
        options.add(new PrivacyOptionItem("Everyone", R.drawable.ic_public_24dp));
        options.add(new PrivacyOptionItem("Friends", R.drawable.ic_group_24dp));
        options.add(new PrivacyOptionItem("Only me", R.drawable.ic_person_dark_24dp));
    }

    private void findSpinners(View view) {
        persInfoSpinner = view.findViewById(R.id.pers_info_spinner);
        sharedActSpinner = view.findViewById(R.id.shared_act_spinner);
        goalsSpinner = view.findViewById(R.id.goals_spinner);
    }

    private void setSpinnerOptions() {
        setAdapter();
        setListener();
    }

    private void setAdapter() {
        optionAdapter = new PrivacyOptionAdapter(this.getActivity(), options);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
//                R.array.privacy_options, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        persInfoSpinner.setAdapter(optionAdapter);
        sharedActSpinner.setAdapter(optionAdapter);
        goalsSpinner.setAdapter(optionAdapter);
    }

    private void setListener() {
        persInfoSpinner.setOnItemSelectedListener(this);
        sharedActSpinner.setOnItemSelectedListener(this);
        goalsSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //TODO
//        PrivacyOptionItem clickedItem = (PrivacyOptionItem) parent.getItemAtPosition(position);
//        String clickedItemName = clickedItem.getItemName();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO
    }
}