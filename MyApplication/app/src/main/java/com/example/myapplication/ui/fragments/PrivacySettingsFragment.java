package com.example.myapplication.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dto.request.PrivacySettingsDto;
import com.example.myapplication.dto.response.UserSettingsResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.settings.PrivacyOptionAdapter;
import com.example.myapplication.settings.PrivacyOptionItem;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacySettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ArrayList<PrivacyOptionItem> options;
    private PrivacyOptionAdapter optionAdapter;
    private Spinner persInfoSpinner;
    private Spinner sharedActSpinner;
    private Spinner goalsSpinner;
    private Button saveButton;

    public PrivacySettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privacy_settings, container, false);
        setHeader();
        initOptions();
        findSpinners(view);
        setSpinnerOptions();
        setSpinners();
        configureSaveButton(view);
        return view;
    }

    private void setHeader() {
        TextView textView = getActivity().findViewById(R.id.toolbar_header);
        textView.setText(R.string.setting_privacy_header);
    }

    private void initOptions() {
        options = new ArrayList<>();
        options.add(new PrivacyOptionItem("Public", R.drawable.ic_public_24dp));
        options.add(new PrivacyOptionItem("Friends", R.drawable.ic_group_24dp));
        options.add(new PrivacyOptionItem("Private", R.drawable.ic_person_dark_24dp));
    }

    private void configureSaveButton(View view) {
        saveButton = view.findViewById(R.id.privacy_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PrivacySettingsDto dto = new PrivacySettingsDto();
                dto.setUsername(SaveSharedPreference.getLoggedObject(getContext()).getUsername());
                dto.setGoalPrivacy(((PrivacyOptionItem)goalsSpinner.getSelectedItem()).getItemName());
                dto.setPostPrivacy(((PrivacyOptionItem)sharedActSpinner.getSelectedItem()).getItemName());
                dto.setUserInfoPrivacy(((PrivacyOptionItem)persInfoSpinner.getSelectedItem()).getItemName());

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<UserSettingsResponse> call = apiService.updatePrivacySettings(dto);
                call.enqueue(new Callback<UserSettingsResponse>() {
                    @Override
                    public void onResponse(Call<UserSettingsResponse> call, Response<UserSettingsResponse> response) {
                        Log.e("tag","Updated");
                        Toast.makeText(getActivity(), "Saved!",
                                Toast.LENGTH_SHORT).show();
                        SaveSharedPreference.setSettings(getContext(), response.body());
                    }
                    @Override
                    public void onFailure(Call<UserSettingsResponse> call, Throwable t) {
                        Log.e("tag","Update failure: " + t);
                        Log.e("tag","Update failure: " + call.getClass());
                    }

                });
            }
        });
    }

    private void findSpinners(View view) {
        persInfoSpinner = view.findViewById(R.id.pers_info_spinner);
        sharedActSpinner = view.findViewById(R.id.shared_act_spinner);
        goalsSpinner = view.findViewById(R.id.goals_spinner);
    }

    private void setSpinners() {
        String persInfoValue = SaveSharedPreference.getSettings(getContext()).getUserInfoPrivacy();
        String postValue = SaveSharedPreference.getSettings(getContext()).getPostPrivacy();
        String goalValue = SaveSharedPreference.getSettings(getContext()).getGoalPrivacy();

        persInfoSpinner.setSelection(findOptionIndex(persInfoValue));
        sharedActSpinner.setSelection(findOptionIndex(postValue));
        goalsSpinner.setSelection(findOptionIndex(goalValue));
    }

    private int findOptionIndex(String value) {
        for(int i = 0; i < options.size(); i++) {
            if (options.get(i).getItemName().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return -1;
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