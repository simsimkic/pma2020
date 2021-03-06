package com.example.myapplication.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.ui.fragments.NotificationsSettingsFragment;
import com.example.myapplication.ui.fragments.PrivacySettingsFragment;
import com.example.myapplication.ui.fragments.ThemeSettingsFragment;
import com.example.myapplication.util.SaveSharedPreference;

import com.example.myapplication.ui.fragments.ActivityFragment;
import com.example.myapplication.ui.fragments.FriendsFragment;
import com.example.myapplication.ui.fragments.GoalsFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.osmdroid.config.Configuration;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView address,email,name,bio;
    BottomNavigationView bottom_navigation;
    Intent intent;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TabLayout tab;
    ActionMenuItemView edit;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        name.setText(SaveSharedPreference.getLoggedObject(getApplicationContext()).getName());
        email = findViewById(R.id.email);
        email.setText(SaveSharedPreference.getLoggedObject(getApplicationContext()).getEmail());
        address = findViewById(R.id.address);
        address.setText(SaveSharedPreference.getLoggedObject(getApplicationContext()).getAddress());
        bio = findViewById(R.id.bio);
        bio.setText(SaveSharedPreference.getLoggedObject(getApplicationContext()).getBiography());

        setMapPermission();

        setToolbar();
        setDrawerLayout(this);
        setNavigationView();
        setBottomNavigation();
        setInitialTab();
        handleTabSelection();



    }



    private void setMapPermission() {
        Configuration.getInstance().setUserAgentValue(getPackageName());

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

    }

    private void handleTabSelection() {
        tab = findViewById(R.id.tabs);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch(tab.getPosition()) {
                    case 0:
                        Log.i("Activities", "Activities inside1 Activities");
                        Fragment fragment = new ActivityFragment();
                        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        Log.i("Friends", "Friends inside1 Friends");
                        fragment = new FriendsFragment();
                        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        Log.i("Goals", "Goals inside1 Goals");
                        fragment = new GoalsFragment();
                        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                        fragmentTransaction.commit();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setInitialTab() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ActivityFragment();
        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
        fragmentTransaction.commit();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setDrawerLayout(ProfileActivity thisProfileActivity) {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setNavigationView() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setBottomNavigation() {
        bottom_navigation = findViewById(R.id.bottom_navigation);
        Menu menu = bottom_navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        intent = new Intent(ProfileActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.activities:
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
                SaveSharedPreference.setLoggedIn(this, false, null);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_top_menu, menu);

        return true;
    }

    private void adaptActivity() {
        findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        findViewById(R.id.edit).setVisibility(View.GONE);
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
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
