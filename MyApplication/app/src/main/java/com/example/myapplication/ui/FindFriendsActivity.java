package com.example.myapplication.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.myapplication.adapter.FriendAdapter;
import com.example.myapplication.adapter.FriendListAdapter;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Friend;
import com.example.myapplication.mokap_data.Friends;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindFriendsActivity extends AppCompatActivity {

    private BottomNavigationView bottom_navigation;
    private Intent intent;
    private ArrayList<FriendResponse> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_36dp);
        toolbar.setTitle("Find Friends");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        setUserList();

        ListView list = findViewById(R.id.friends_show);
        //klik na korisnika iz liste
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendResponse friend = friends.get(position);
//                Toast.makeText(getApplicationContext(), "You click on friend " + friend.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FindFriendsActivity.this, FriendDetailActivity.class);

                intent.putExtra("friend", friend);

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
                        intent = new Intent(FindFriendsActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        Log.i("tracking", "tracking inside1 tracking");
                        intent = new Intent(FindFriendsActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        Log.i("profile", "profile activity");

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

    private void setUserList() {

            //dobavljanje svih prijatelja iz baze

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ArrayList<FriendResponse>> call = apiService.getUsers(SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername());
            call.enqueue(new Callback() {

                @Override
                public void onResponse(Call call, Response response) {
                    if(response.body() != null){
                        friends = (ArrayList<FriendResponse>) response.body();

                        Log.e("tag", "Dobavili smo prijatelje, ima ih: " + friends.size());

                        ListView list = findViewById(R.id.friends_show);
                        //ovde treba da dobavimo sve korisnike sistema i da ih prikazemo
                        FriendListAdapter adapter = new FriendListAdapter(getApplicationContext(), friends);
                        list.setAdapter(adapter);

                    }

                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.e("tag","Greska prilikom dobavljanja prijatelja!!!");

                }





            });
    }


}
