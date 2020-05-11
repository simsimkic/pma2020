package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.model.Friend;
import com.example.myapplication.ui.fragments.ActivityFragment;
import com.example.myapplication.ui.fragments.GoalsFragment;
import com.example.myapplication.ui.fragments.UserFriendsList;
import com.example.myapplication.ui.fragments.UserGoalsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class FriendDetailActivity extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    Intent intent;
    Toolbar toolbar;
    TabLayout tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_36dp);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        setBottomNavigationActions();
        setNavigationToolbar();


        TextView tv = findViewById(R.id.name);
        Friend user = (Friend) getIntent().getSerializableExtra("friend");

        tv.setText(user.getName());

        ImageView imageView = findViewById(R.id.icon_friend);
        if(user.isFriends()){
            imageView.setImageResource(R.drawable.ic_group);
        }else{
            imageView.setImageResource(R.drawable.ic_add_friend);
        }

        setHandleTabs();
        setInitialTab();



        clickInviteButton();


    }

    private void clickInviteButton() {
        Button invite_btn = findViewById(R.id.invite_btn);
        invite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.invite_popup, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                RelativeLayout rl = (RelativeLayout)findViewById(R.id.detail_layout);
//                rl.setAlpha(0.5F);

//                ImageView remove = findViewById(R.id.remove);
//                remove.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.dismiss();
//                    }
//                };
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                ImageView remove = popupView.findViewById(R.id.remove);
                remove.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  popupWindow.dismiss();
                                              }
                                          }
                );



            }
        });
    }

    private void setHandleTabs() {
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
                        fragment = new UserFriendsList();
                        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        Log.i("Goals", "Goals inside1 Goals");
                        fragment = new UserGoalsFragment();
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
    }
    private void setInitialTab() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ActivityFragment();
        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
        fragmentTransaction.commit();
    }

    private void setNavigationToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                Intent intent = new Intent(FriendDetailActivity.this, FindFriendsActivity.class);
                startActivity(intent);

            }
        });
    }

    private void setBottomNavigationActions() {
        bottom_navigation = findViewById(R.id.bottom_navigation);
        Menu menu = bottom_navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Log.i("home", "home inside1 home");
                        intent = new Intent(FriendDetailActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        Log.i("tracking", "tracking inside1 tracking");
                        intent = new Intent(FriendDetailActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        Log.i("profile", "profile activity");
                        return true;
                    case R.id.activities:
                        Log.i("activities", "activities inside1 activities");
                        intent = new Intent(FriendDetailActivity.this, GroupActivitiesActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

}
