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
import com.example.myapplication.dto.request.NotificationSettingsDto;
import com.example.myapplication.dto.response.UserSettingsResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsSettingsFragment extends Fragment {

    private Button saveButton;
    private Switch newCommentSwitch;
    private Switch newLikeSwitch;
    private Switch friendshipRequestSwitch;
    private Switch acceptedFriendshipSwitch;
    private Switch activityRequestSwitch;
    private Switch acceptedActivitySwitch;
    private Switch canceledActivitySwitch;

    public NotificationsSettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications_settings, container, false);
        setHeader();
        findSwitches(view);
        setSwitches(view);
        configureSaveButton(view);
        return view;
    }

    private void setHeader() {
        TextView textView = getActivity().findViewById(R.id.toolbar_header);
        textView.setText(R.string.setting_notifications_header);
    }

    private void findSwitches(View view) {
        newCommentSwitch = view.findViewById(R.id.new_comment_switch);
        newLikeSwitch = view.findViewById(R.id.new_like_switch);
        friendshipRequestSwitch = view.findViewById(R.id.friend_req_switch);
        acceptedFriendshipSwitch = view.findViewById(R.id.accept_friend_req_switch);
        activityRequestSwitch = view.findViewById(R.id.act_invite_switch);
        acceptedActivitySwitch = view.findViewById(R.id.accept_act_invite_switch);
        canceledActivitySwitch = view.findViewById(R.id.canceled_act_switch);
    }

    private void setSwitches(View view) {
        newCommentSwitch.setChecked(SaveSharedPreference.getSettings(getContext()).isNewComments());
        newLikeSwitch.setChecked(SaveSharedPreference.getSettings(getContext()).isNewLikes());
        friendshipRequestSwitch.setChecked(SaveSharedPreference.getSettings(getContext()).isFriendshipRequest());
        acceptedFriendshipSwitch.setChecked(SaveSharedPreference.getSettings(getContext()).isAcceptedFriendship());
        activityRequestSwitch.setChecked(SaveSharedPreference.getSettings(getContext()).isActivityRequest());
        acceptedActivitySwitch.setChecked(SaveSharedPreference.getSettings(getContext()).isAcceptedActivity());
        canceledActivitySwitch.setChecked(SaveSharedPreference.getSettings(getContext()).isCanceledActivity());
    }

    private void configureSaveButton(View view) {
        saveButton = view.findViewById(R.id.notification_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NotificationSettingsDto dto = new NotificationSettingsDto();
                dto.setUsername(SaveSharedPreference.getLoggedObject(getContext()).getUsername());
                dto.setNewLikes(newLikeSwitch.isChecked());
                dto.setNewComments(newCommentSwitch.isChecked());
                dto.setFriendshipRequest(friendshipRequestSwitch.isChecked());
                dto.setAcceptedFriendship(acceptedFriendshipSwitch.isChecked());
                dto.setActivityRequest(activityRequestSwitch.isChecked());
                dto.setAcceptedActivity(acceptedActivitySwitch.isChecked());
                dto.setCanceledActivity(canceledActivitySwitch.isChecked());

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<UserSettingsResponse> call = apiService.updateNotificationSettings(dto);
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

}