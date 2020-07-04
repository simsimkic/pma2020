package com.example.myapplication.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.myapplication.adapter.FeedActivityAdapter;
import com.example.myapplication.adapter.NotificationAdapter;
import com.example.myapplication.dto.response.NotificationResponse;
import com.example.myapplication.dto.response.PostResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.mokap_data.Notifications;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    private BottomNavigationView bottom_navigation;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_36dp);
        toolbar.setTitle("Notifications");
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.notifications_list);
        getNotifications(list);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });

        setBottomNavigation();



    }

    private void getNotifications(ListView list) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<NotificationResponse>> call = apiService.getNotifications(SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername());
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){
                    ArrayList<NotificationResponse>notifications = (ArrayList<NotificationResponse>) response.body();

                    Log.e("tag", "Dobavili smo notifikacije, ima ih: " + notifications.size());

                    ;
                    list.setAdapter(new NotificationAdapter(getApplicationContext(), notifications, list));

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag", t.getMessage());
                Log.e("tag","Greska prilikom dobavljanja notifikacija!!!");

            }





        });

    }

    private void setBottomNavigation() {

        bottom_navigation = findViewById(R.id.bottom_navigation);
        Menu menu = bottom_navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Log.i("home", "home inside1 home");
                        intent = new Intent(NotificationActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        Log.i("tracking", "tracking inside1 tracking");
                        intent = new Intent(NotificationActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        Log.i("profile", "profile activity");
                        intent = new Intent(NotificationActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.activities:
                        Log.i("activities", "activities inside1 activities");
                        intent = new Intent(NotificationActivity.this, GroupActivitiesActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

}
