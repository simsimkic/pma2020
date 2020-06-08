package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FeedActivityAdapter;
import com.example.myapplication.adapter.FriendListAdapter;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.dto.response.PostResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.mokap_data.Activities;
import com.example.myapplication.mokap_data.Friends;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    Intent intent;
    MapView openMapView;
    MapController mapController;
    protected LocationManager locationManager;
    GeoPoint currentLocation;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                        intent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        Log.i("tracking", "tracking inside1 tracking");
                        intent = new Intent(HomeActivity.this, TrackingActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        Log.i("profile", "profile activity");
                        intent = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.activities:
                        Log.i("activities", "activities inside1 activities");
                        intent = new Intent(HomeActivity.this, GroupActivitiesActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        ImageView notification_icon = findViewById(R.id.notification_icon);
        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });


        showPost();

    }

    private void showPost() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<PostResponse>> call = apiService.getAllPosts(SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername());
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){
                    ArrayList<PostResponse>posts = (ArrayList<PostResponse>) response.body();

                    Log.e("tag", "Dobavili smo postove, ima ih: " + posts.size());

                    ListView list = findViewById(R.id.feed_activities);
                    FeedActivityAdapter adapter = new FeedActivityAdapter(getApplicationContext(), posts);
                    list.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag", t.getMessage());
                Log.e("tag","Greska prilikom dobavljanja postova!!!");

            }





        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Feed");
        toolbar.setTitleTextColor(Color.WHITE);

        //preuzeti sve postove


    }
}
