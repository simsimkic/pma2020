package com.example.myapplication.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.myapplication.adapter.FriendListAdapter;
import com.example.myapplication.model.Friend;
import com.example.myapplication.mokap_data.Friends;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class FindFriendsActivity extends AppCompatActivity {

    private ArrayList<Friend> friends = Friends.getFriends();
    private ListView list;
    private BottomNavigationView bottom_navigation;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_36dp);
        toolbar.setTitle("Find Friends");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.friends_show);
        FriendListAdapter adapter = new FriendListAdapter(getApplicationContext(), Friends.getFriends());
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend friend = Friends.getFriends().get(position);
//                Toast.makeText(getApplicationContext(), "You click on friend " + friend.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FindFriendsActivity.this, FriendDetailActivity.class);
                intent.putExtra("user_name", friend.getName());
                startActivity(intent);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                Intent intent = new Intent(FindFriendsActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

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
                        return true;
                    case R.id.tracking:
                        Log.i("tracking", "tracking inside1 tracking");
                        intent = new Intent(FindFriendsActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        Log.i("profile", "profile activity");
                        intent = new Intent(FindFriendsActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.activities:
                        Log.i("activities", "activities inside1 activities");
                        intent = new Intent(FindFriendsActivity.this, GroupActivitiesActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

    //klik na element u listi
    private class DrawItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
        }


    }
    private void selectItemFromDrawer(int position) {

    }

}
