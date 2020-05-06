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
import com.example.myapplication.model.Activity;
import com.example.myapplication.mokap_data.Activities;
import com.example.myapplication.ui.fragments.NotificationsSettingsFragment;
import com.example.myapplication.ui.fragments.PrivacySettingsFragment;
import com.example.myapplication.ui.fragments.ThemeSettingsFragment;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class GroupActivitiesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottom_navigation;
    Intent intent;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_activities);

        setToolbar();
        setDrawerLayout(this);
        setNavigationView();

        drawList();

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
        ActivityListAdapter adapter = new ActivityListAdapter(getApplicationContext(), Activities.getActivities());
        list.setAdapter(adapter);
    }

    private void setNavigationView() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setDrawerLayout(GroupActivitiesActivity groupActivitiesActivity) {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.theme:
                adaptActivity();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ThemeSettingsFragment()).commit();
                break;
            case R.id.notifications:
                adaptActivity();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationsSettingsFragment()).commit();
                break;
            case R.id.privacy:
                adaptActivity();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PrivacySettingsFragment()).commit();
                break;
            case R.id.logout:
                SaveSharedPreference.setLoggedIn(this, false);
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_option:
                findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
                findViewById(R.id.fragment_container).setVisibility(View.GONE);
                navigationView.getMenu().findItem(R.id.profile_option).setVisible(false);
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void adaptActivity() {
        findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        findViewById(R.id.edit).setVisibility(View.GONE);
//        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        navigationView.getMenu().findItem(R.id.profile_option).setVisible(true);
    }
}
