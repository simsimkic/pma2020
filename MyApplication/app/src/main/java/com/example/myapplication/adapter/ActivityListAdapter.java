package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dto.request.ActivityInviteRequest;
import com.example.myapplication.dto.request.ActivityRequestAnswer;
import com.example.myapplication.dto.request.ActivityRequestStatus;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.ui.GroupActivitiesActivity;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityListAdapter extends BaseAdapter {
    Context context;
    ArrayList<ActivityInviteRequest> activities;
    ListView listView;

    public ActivityListAdapter(Context applicationContext, ArrayList<ActivityInviteRequest> activities, ListView list){
        this.context = applicationContext;
        this.activities = activities;
        this.listView = list;
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int position) {
        return activities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        String login_user = SaveSharedPreference.getLoggedObject(context).getName();
        String username_login = SaveSharedPreference.getLoggedObject(context).getUsername();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_activity_in_list, null);

        } else {
            view = convertView;
        }

        TextView description = (TextView) view.findViewById(R.id.description);
        ImageView state = (ImageView) view.findViewById(R.id.activity_state);
        TextView location = (TextView) view.findViewById(R.id.location);
        TextView date = (TextView) view.findViewById(R.id.date);
        LinearLayout btn_layout = (LinearLayout) view.findViewById(R.id.button_layout);

        ActivityInviteRequest activity = activities.get(position);


        location.setText(activities.get(position).getLocation());
        date.setText(activity.getTimestamp());

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(R.color.primary);
        gradientDrawable.setCornerRadius(10);

        if (activity.getStatus() == ActivityRequestStatus.ACCEPT && activity.getUsername_requestor().equals(login_user)) {
            state.setImageResource(R.drawable.checkmark);
            description.setText(activity.getUsername_requestee() + " accepted your activity invitation.");
            Button cancel = new Button(context);
            cancel.setText("Cancel");
            cancel.setTextSize(16);

//            cancel.setBackground(gradientDrawable);

//            cancel.setBackgroundColor(R.color.primary);
            cancel.setId(R.id.cancel_btn);
            btn_layout.addView(cancel);
            clickAccept(username_login, activity, cancel, false, view);

        }else if (activity.getStatus() == ActivityRequestStatus.ACCEPT && activity.getUsername_requestee().equals(login_user)) {
            state.setImageResource(R.drawable.checkmark);
            description.setText("You accepted activity invitation.");
            Button cancel = new Button(context);
            cancel.setText("Cancel");
            cancel.setTextSize(16);

//            cancel.setBackground(gradientDrawable);

//            cancel.setBackgroundColor(R.color.primary);
            cancel.setId(R.id.cancel_btn);
            btn_layout.addView(cancel);
            clickAccept(username_login, activity, cancel, false,view);

        }else if(activity.getStatus() == ActivityRequestStatus.SEND && activity.getUsername_requestor().equals(login_user)){
            state.setImageResource(R.drawable.waiting);
            description.setText("Waiting for " + activity.getUsername_requestee() + " to accept activity invitation.");
//
        }else if(activity.getStatus() == ActivityRequestStatus.SEND && activity.getUsername_requestee().equals(login_user)) {
            description.setText(activity.getUsername_requestor() + " invites you to an activity.");

            state.setImageResource(R.drawable.question_mark);
            Button accept =  new Button(context);
            accept.setText("Accept");
            accept.setTextSize(16);
            accept.setId(R.id.accept_button);
//            accept.setBackground(gradientDrawable);
//            accept.setBackgroundColor(R.color.primary);
            btn_layout.addView(accept);
            clickAccept(username_login, activity, accept, true, view);

            Button cancel =  new Button(context);
            cancel.setText("Decline");
            cancel.setTextSize(16);
            cancel.setId(R.id.cancel_btn);
//            cancel.setBackground(gradientDrawable);
//            cancel.setBackgroundColor(R.color.primary);


            btn_layout.addView(cancel);
            clickAccept(username_login, activity, cancel, false, view);
        }else if(activity.getStatus() == ActivityRequestStatus.DECLINE ){
            description.setText( "Group activity has been canceled.");
            state.setImageResource(R.drawable.ic_cancel);

        }
        return view;
    }

    private void clickAccept(String login_user, ActivityInviteRequest activity, Button accept, Boolean accept_request, View view) {
        ActivityRequestAnswer answer = new ActivityRequestAnswer(activity.getId(), accept_request, login_user);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                Call<List<ActivityInviteRequest>> call = apiService.acceptOrDeclineActivityRequest(answer);
                call.enqueue(new Callback() {

                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.body() != null){


                            Log.e("tag", "Prihvatili ste ili odbili zahtev za druzenje");
                            if(accept_request) {
                                Toast.makeText(context, "Successfully accept activity request", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(context, "Successfully reject activity request", Toast.LENGTH_LONG).show();

                            }

                            activities= (ArrayList<ActivityInviteRequest>) response.body();
                            ActivityListAdapter a = new ActivityListAdapter(context, activities, listView);
                            listView.setAdapter(a);
//                            activities.clear();
//                            activities.addAll((Collection<? extends ActivityInviteRequest>) response.body());
//                            notifyDataSetChanged();
                            //uzmem notifikacije i osvezim adapter



                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("tag","Greska prilikom prihvatanja ili odbijanja zahteva za druzenje!!!");

                    }





                });
            }


        });

    }

}
