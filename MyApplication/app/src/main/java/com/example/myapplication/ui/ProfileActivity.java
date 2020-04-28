package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ui.fragments.NotificationsSettingsFragment;
import com.example.myapplication.ui.fragments.PrivacySettingsFragment;
import com.example.myapplication.ui.fragments.ThemeSettingsFragment;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottom_navigation;
    Intent intent;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Menu menu;
    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setToolbar();
        setDrawerLayout(this);
        setNavigationView();
        setBottomNavigation();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setDrawerLayout(ProfileActivity thisProfileActivity) {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(thisProfileActivity, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setNavigationView() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setBottomNavigation() {
        bottom_navigation = findViewById(R.id.bottom_navigation);
        menu = bottom_navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Log.i("home", "home inside1 home");
                        intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        Log.i("tracking", "tracking inside1 tracking");
                        intent = new Intent(ProfileActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        Log.i("profile", "profile activity");
                        return true;
                    case R.id.activities:
                        Log.i("Activities", "Activities inside1 Activities");
                        intent = new Intent(ProfileActivity.this, GroupActivitiesActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.theme:
                adaptNavigationDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ThemeSettingsFragment()).commit();
                break;
            case R.id.notifications:
                adaptNavigationDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationsSettingsFragment()).commit();
                break;
            case R.id.privacy:
                adaptNavigationDrawer();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PrivacySettingsFragment()).commit();
                break;
            case R.id.logout:
                SaveSharedPreference.setLoggedIn(this, false);
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_option:
                navigationView.getMenu().findItem(R.id.profile_option).setVisible(false);
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void adaptNavigationDrawer() {
        menu.clear();
        navigationView.getMenu().findItem(R.id.profile_option).setVisible(true);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
