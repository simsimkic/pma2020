package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.model.Friend;
import com.example.myapplication.mokap_data.Friends;
import com.example.myapplication.ui.fragments.ActivityFragment;
import com.example.myapplication.ui.fragments.ActivityInvitationFragment;
import com.example.myapplication.ui.fragments.User_profile_details_1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.gson.Gson;

public class FriendDetailActivity extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    Intent intent;
    Toolbar toolbar;


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
//        String friend = getIntent().getStringExtra("friend");
//        int position =  Integer.valueOf(friend);
//        Toast.makeText(getApplicationContext(), "Position is: " + position, Toast.LENGTH_LONG).show();
//        Friend user = Friends.getFriends().get(position);
//        Toast.makeText(getApplicationContext(), "Name is: "+ user.getName(), Toast.LENGTH_LONG ).show();
        Friend user = (Friend) getIntent().getSerializableExtra("friend");

        tv.setText(user.getName());

        ImageView imageView = findViewById(R.id.icon_friend);
        if(user.isFriends()){
            imageView.setImageResource(R.drawable.ic_group);
        }else{
            imageView.setImageResource(R.drawable.ic_add_friend);
        }

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new User_profile_details_1();
        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
        fragmentTransaction.commit();



        Button invite_btn = findViewById(R.id.invite_btn);
        invite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Toast.makeText(getApplicationContext(), "Click on the button", Toast.LENGTH_LONG).show();
                Fragment fragment = new ActivityInvitationFragment();
                fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                fragmentTransaction.commit();
            }
        });


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
