package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
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
import com.example.myapplication.dto.request.FriendshipRequest;
import com.example.myapplication.dto.response.NotificationResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Notification;
import com.example.myapplication.model.NotificationType;
import com.example.myapplication.mokap_data.Notifications;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdapter extends BaseAdapter {
    Context context;
    ArrayList<NotificationResponse> notifications;
    ListView listView;

    public NotificationAdapter(Context context, ArrayList<NotificationResponse> notifications, ListView list) {
        this.context = context;
        this.notifications = notifications;
        this.listView = list;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.notification_item, null);
        }else{
            view = convertView;
        }

        NotificationResponse n = this.notifications.get(position);

        ImageView icon = view.findViewById(R.id.notification_icon);
        TextView description= view.findViewById(R.id.description);
        LinearLayout btn_layout = (LinearLayout)view.findViewById(R.id.button_layout);
        LinearLayout activity_info_layout = view.findViewById(R.id.activity_info);
        LinearLayout location_info = view.findViewById(R.id.location_info);

        activity_info_layout.setVisibility(View.INVISIBLE);
location_info.setVisibility(View.INVISIBLE);
        if (n.getType() == NotificationType.COMMENT){
            icon.setImageResource(R.drawable.ic_comment);
            description.setText(n.getDescription());
        }else if (n.getType() == NotificationType.LIKE){
            icon.setImageResource(R.drawable.ic_like);
            description.setText(n.getDescription());
        }else if (n.getType() == NotificationType.ACCEPT_FRIEND){
            icon.setImageResource(R.drawable.ic_accept_friend);
            description.setText(n.getDescription());
        }else if (n.getType() == NotificationType.ACCEPT_INVITATION){
            icon.setImageResource(R.drawable.checkmark);
            activity_info_layout.setVisibility(View.VISIBLE);
            location_info.setVisibility(View.VISIBLE);
            TextView locationView = view.findViewById(R.id.location);
            locationView.setText(n.getActivityDto().getLocation());
//
            TextView time = view.findViewById(R.id.date);
            time.setText(n.getActivityDto().getTimestamp());

            description.setText(n.getDescription());
        }else if (n.getType() == NotificationType.SEND_FRIEND){

            icon.setImageResource(R.drawable.ic_sent_request);
            description.setText(n.getDescription());
            Button accept =  new Button(context);
            accept.setText("Accept");
            accept.setTextSize(16);
            accept.setId(R.id.accpet_friend_btn);
            btn_layout.addView(accept);

            Button cancel =  new Button(context);
            cancel.setText("Decline");
            cancel.setTextSize(16);
            cancel.setId(R.id.decline_friend_btn);
            btn_layout.addView(cancel);
            clickAcceptFriend(n, accept);
            clickDeclineFriend(n, cancel );
        }else if (n.getType() ==  NotificationType.SEND_INVITATION){
            activity_info_layout.setVisibility(View.VISIBLE);
            location_info.setVisibility(View.VISIBLE);
            icon.setImageResource(R.drawable.question_mark);
            description.setText(n.getDescription());
            TextView locationView = view.findViewById(R.id.location);
            locationView.setText(n.getActivityDto().getLocation());
//
            TextView time = view.findViewById(R.id.date);
            time.setText(n.getActivityDto().getTimestamp());


            Button accept =  new Button(context);
            accept.setText("Accept");
            accept.setTextSize(16);
            accept.setId(R.id.accept_invite_btn);
            btn_layout.addView(accept);

            Button cancel =  new Button(context);
            cancel.setText("Decline");
            cancel.setTextSize(16);
            cancel.setId(R.id.decline_invite_btn);
            btn_layout.addView(cancel);

            clickInviteAnswerButton(n, accept, cancel);
        }




        return view;
    }

    private void clickInviteAnswerButton(NotificationResponse n, Button accept, Button cancel) {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerToInvitation(n, false);

            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerToInvitation(n, true);

            }
        });
    }

    private void answerToInvitation(NotificationResponse n, boolean accept) {
        String login_user = SaveSharedPreference.getLoggedObject(context).getName();

        ActivityRequestAnswer answer = new ActivityRequestAnswer(n.getActivityDto().getId(), accept, login_user);

         ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

         Call<List<ActivityInviteRequest>> call = apiService.acceptOrDeclineActivityRequest(answer);
         call.enqueue(new Callback() {

                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.body() != null){


                            Log.e("tag", "Prihvatili ste ili odbili zahtev za druzenje");
                            if(accept) {
                                Toast.makeText(context, "Successfully accept activity request", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(context, "Successfully reject activity request", Toast.LENGTH_LONG).show();

                            }





                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("tag","Greska prilikom prihvatanja ili odbijanja zahteva za druzenje!!!");

                    }




                });

        refreshList();
            }




    private void clickDeclineFriend(NotificationResponse n, Button cancel) {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                FriendshipRequest decline = new FriendshipRequest(n.getFriend_username(), SaveSharedPreference.getLoggedObject(context).getUsername(), false );
                Call<ResponseBody> call = apiService.acceptOrDeclineFriendshipRequest(decline);
                call.enqueue(new Callback() {

                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.body() != null){


                            Log.e("tag", "Niste prihvatili zahtev za  prijateljstvo");

                            Toast.makeText(context, "Successfully decline friendship request", Toast.LENGTH_LONG).show();




                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("tag","Greska prilikom odbijanja zahteva za prijateljstvo!!!");

                    }





                });
                refreshList();
            }
        });

    }

    private void clickAcceptFriend(NotificationResponse notificationResponse, Button accept_btn) {
        //prihvatamo zahtev za prijateljstvo
        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                FriendshipRequest accept = new FriendshipRequest(notificationResponse.getFriend_username(), SaveSharedPreference.getLoggedObject(context).getUsername(), true );
                Call<ResponseBody> call = apiService.acceptOrDeclineFriendshipRequest(accept);
                call.enqueue(new Callback() {

                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.body() != null){


                            Log.e("tag", "Prihvatili ste zahtev za prijateljstvo");
                            Toast.makeText(context, "Successfully accept friendship request", Toast.LENGTH_LONG).show();


                            //uzmem notifikacije i osvezim adapter


                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("tag","Greska prilikom prihvatanja zahteva za prijateljstvo!!!");

                    }





                });
                refreshList();
            }


        });


    }

    private void refreshList() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<NotificationResponse>> call = apiService.getNotifications(SaveSharedPreference.getLoggedObject(context).getUsername());
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){
                    ArrayList<NotificationResponse>notifications_res = (ArrayList<NotificationResponse>) response.body();

                    Log.e("tag", "Dobavili smo notifikacije, ima ih: " + notifications_res.size());

                    ;

                    notifications = notifications_res;
                    NotificationAdapter adapter = new NotificationAdapter(context, notifications, listView);
                    listView.setAdapter(adapter);


                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag", t.getMessage());
                Log.e("tag","Greska prilikom dobavljanja notifikacija!!!");

            }





        });

    }
}
