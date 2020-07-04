package com.example.myapplication.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dto.request.ThemeSettingsDto;
import com.example.myapplication.dto.response.UserSettingsResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemeSettingsFragment extends Fragment {

    private Button saveButton;
    private Switch nightThemeSwitch;

    public ThemeSettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
        View view = inflater.inflate(R.layout.fragment_theme_settings, container, false);
        setHeader();
        setSwitch(view);
        configureSaveButton(view);
        return view;
    }

    private void setHeader() {
        TextView textView = getActivity().findViewById(R.id.toolbar_header);
        textView.setText(R.string.setting_theme_header);
    }

    private void setSwitch(View view) {
        nightThemeSwitch = view.findViewById(R.id.night_theme_switch);
        nightThemeSwitch.setChecked(SaveSharedPreference.getSettings(getContext()).isNightTheme());
    }

    private void configureSaveButton(View view) {
        saveButton = view.findViewById(R.id.theme_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ThemeSettingsDto dto = new ThemeSettingsDto();
                dto.setUsername(SaveSharedPreference.getLoggedObject(getContext()).getUsername());
                dto.setNightTheme(nightThemeSwitch.isChecked());

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<UserSettingsResponse> call = apiService.updateThemeSettings(dto);
                call.enqueue(new Callback<UserSettingsResponse>() {
                    @Override
                    public void onResponse(Call<UserSettingsResponse> call, Response<UserSettingsResponse> response) {
                        Log.e("tag","Updated");
                        Toast.makeText(getActivity(), "Saved!",
                                Toast.LENGTH_SHORT).show();
                        SaveSharedPreference.setSettings(getContext(), response.body());
//                        if (nightThemeSwitch.isChecked()) {
//                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                        } else {
//                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                        }
//                        getActivity().recreate();
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

}