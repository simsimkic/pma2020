package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivityListAdapter;
import com.example.myapplication.adapter.FriendListAdapter;
import com.example.myapplication.dto.request.ActivityInviteRequest;
import com.example.myapplication.dto.request.ActivityRequestStatus;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Activity;
import com.example.myapplication.mokap_data.Activities;
import com.example.myapplication.ui.fragments.NotificationsSettingsFragment;
import com.example.myapplication.ui.fragments.PrivacySettingsFragment;
import com.example.myapplication.ui.fragments.ThemeSettingsFragment;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupActivitiesActivity extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    Intent intent;
    DrawerLayout drawerLayout;
    ArrayList<ActivityInviteRequest> activityInviteRequests;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_activities);

        setToolbar();


        setBottomNavigation();


        drawList();



    }

    private void setBottomNavigation() {
        bottom_navigation = findViewById(R.id.bottom_navigation);
        Menu menu = bottom_navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Log.i("home", "home inside1 home");
                        intent = new Intent(GroupActivitiesActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        Log.i("tracking", "tracking inside1 tracking");
                        intent = new Intent(GroupActivitiesActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        Log.i("profile", "profile activity");
                        intent = new Intent(GroupActivitiesActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.activities:
                        Log.i("activities", "activities inside1 activities");
                        return true;
                }
                return false;
            }
        });
    }

    private void drawList() {
        ListView list = findViewById(R.id.activity_list);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ActivityInviteRequest>> call = apiService.getGroupActivity(SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername());
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){
                    activityInviteRequests = (ArrayList<ActivityInviteRequest>) response.body();

                    Log.e("tag", "Dobavili smo grupne aktivnosti, ima ih: " + activityInviteRequests.size());


                    ActivityListAdapter adapter = new ActivityListAdapter(getApplicationContext(), activityInviteRequests, list);
                    list.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag","Greska prilikom dobavljanja grupnih aktivnosti!!");

            }





        });



    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Group activities");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }




}
