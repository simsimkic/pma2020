package com.example.myapplication.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivitieAdapter;
import com.example.myapplication.adapter.ActivityListAdapter;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Activity;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivityDelete extends AppCompatDialogFragment {

    public Long activityId;
    private FragmentActivity activity;
    public ActivitieAdapter adapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.confirm_delete_dialog, null);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        builder.setView(view)
                .setTitle("Delete this activity?")
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                        Long userId = SaveSharedPreference.getLoggedObject(getContext()).getId();
                        Call<Activity> call = apiService.deleteActivity(userId,
                                activityId);
                        call.enqueue(new Callback<Activity>() {
                            @Override
                            public void onResponse(Call<Activity> call, Response<Activity> response) {
                                Log.e("tag","deleted");
                                Toast.makeText(activity, "Deleted!",
                                        Toast.LENGTH_LONG).show();
                                adapter.refresh(activityId);
                            }
                            @Override
                            public void onFailure(Call<Activity> call, Throwable t) {
                                Log.e("tag","delete failure: " + t);
                                Log.e("tag","delete failure: " + call.getClass());
                            }
                        });
                    }
                });
        return builder.create();
    }

}
