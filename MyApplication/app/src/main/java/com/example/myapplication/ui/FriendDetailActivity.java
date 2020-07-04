package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.adapter.FriendListAdapter;
import com.example.myapplication.dto.request.ActivityInviteRequest;
import com.example.myapplication.dto.request.FriendshipRequest;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Friend;
import com.example.myapplication.ui.fragments.ActivityFragment;
import com.example.myapplication.ui.fragments.GoalsFragment;
import com.example.myapplication.ui.fragments.UserFriendsList;
import com.example.myapplication.ui.fragments.UserGoalsFragment;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.gms.common.api.Status;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mapbox.mapboxsdk.plugins.places.picker.PlacePicker;
import com.mapbox.mapboxsdk.plugins.places.picker.model.PlacePickerOptions;

import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendDetailActivity extends AppCompatActivity {
    final String MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoicG1hMjAyMCIsImEiOiJja2M2OHd2MWMwOTRpMnZueDQ3ejhnbTZlIn0.62gLuTCl6gOrhxzN_EbYng";
    BottomNavigationView bottom_navigation;
    Intent intent;
    Toolbar toolbar;
    TabLayout tab;
    EditText locationEdit;


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
        FriendResponse user = (FriendResponse) getIntent().getSerializableExtra("friend");

        tv.setText(user.getName());
        Button invite_btn = findViewById(R.id.invite_btn);

        ImageView imageView = findViewById(R.id.icon_friend);
        if(user.getFriend()==1){
            imageView.setImageResource(R.drawable.ic_denied);

        }else if(user.getFriend()==0){
            imageView.setImageResource(R.drawable.ic_add_friend);
            invite_btn.setVisibility(View.INVISIBLE);
        }else if(user.getFriend()==2){
            //zahtev je poslat
            imageView.setImageResource(R.drawable.ic_send_request);
            invite_btn.setVisibility(View.INVISIBLE);
        }else{
            //zahtev je primljen
            imageView.setImageResource(R.drawable.ic_accept_request);
            invite_btn.setVisibility(View.INVISIBLE);
        }

        setHandleTabs();
        setInitialTab();


        clickImage(imageView, user);

        clickInviteButton(user.getUsername());


    }

    private void clickImage(ImageView imageView, FriendResponse user) {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getFriend()==0){
                    //moze da mu posalje zahtev, samo mu iskoci obavestenje da je zahtev za prijateljstvo poslato
                    sendFriendshipRequest(user, imageView);

                }else if(user.getFriend()==1){
                    //nista, prijatelji su moze da ga ukloni
                    removeFriends(user, imageView);
//                    Toast.makeText(getApplicationContext(), "Successfully remove friend", Toast.LENGTH_LONG).show();

                }else if (user.getFriend()==2){
                    //zahtev poslat
                    Toast.makeText(getApplicationContext(), "Wait response to frienship request", Toast.LENGTH_LONG).show();
                }else{
                    //primljen yahtev, otvara se popup za prihavatanje prijateljstva
                    drawingPopup(v, user, imageView);


                }

            }
        });
//        this.recreate();
//        finish();
//        startActivity(getIntent());
    }

    private void drawingPopup(View v, FriendResponse user, ImageView imageView) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.accept_friendship_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.detail_layout);

        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);


        TextView text = popupView.findViewById(R.id.message);
        text.setText(user.getName() + " send you a friend request");

        Button acceptButton = popupView.findViewById(R.id.accept_button);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obratimo se api-ju da prihvati zahtev za prijateljstvo
                acceptFriendship(user, imageView);
                popupWindow.dismiss();

            }
        });

        Button declineButton =popupView.findViewById(R.id.decline_button);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obratimo se api-ju da prihvati zahtev za prijateljstvo
                declineFriends(user,imageView);
                popupWindow.dismiss();

            }
        });

    }

    private void acceptFriendship(FriendResponse user, ImageView imageView) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        FriendshipRequest accept = new FriendshipRequest(user.getUsername(), SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername(), true );
        Call<ResponseBody> call = apiService.acceptOrDeclineFriendshipRequest(accept);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){


                    Log.e("tag", "Prihvatili ste zahtev za prijateljstvo");
                    Toast.makeText(getApplicationContext(), "Successfully accept friendship request", Toast.LENGTH_LONG).show();
                    imageView.setImageResource(R.drawable.ic_group);
                    Button invite = findViewById(R.id.invite_btn);
                    invite.setVisibility(View.VISIBLE);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeFriends(user, imageView);
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag","Greska prilikom prihvatanja zahteva za prijateljstvo!!!");

            }





        });
    }

    private void  removeFriends(FriendResponse user, ImageView imageView){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        FriendshipRequest decline = new FriendshipRequest(user.getUsername(), SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername(), false );
        Call<ResponseBody> call = apiService.deleteFriends(decline);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){


                    Log.e("tag", "Obrisali ste  prijatelja");

                    Toast.makeText(getApplicationContext(), "Successfully remove friend", Toast.LENGTH_LONG).show();
                    imageView.setImageResource(R.drawable.ic_add_friend);
                    Button invite = findViewById(R.id.invite_btn);
                    invite.setVisibility(View.INVISIBLE);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendFriendshipRequest(user, imageView);
                        }
                    });



                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag","Greska prilikom brisanja prijatelja!!!");

            }





        });
    }

    private void declineFriends(FriendResponse user, ImageView imageView) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        FriendshipRequest decline = new FriendshipRequest(user.getUsername(), SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername(), false );
        Call<ResponseBody> call = apiService.acceptOrDeclineFriendshipRequest(decline);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){


                    Log.e("tag", "Niste prihvatili zahtev za  prijateljstvo");

                        Toast.makeText(getApplicationContext(), "Successfully decline friendship request", Toast.LENGTH_LONG).show();

                    imageView.setImageResource(R.drawable.ic_add_friend);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendFriendshipRequest(user, imageView);
                        }
                    });


                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag","Greska prilikom odbijanja zahteva za prijateljstvo!!!");

            }





        });
    }


    private void sendFriendshipRequest(FriendResponse requestee, ImageView imageView) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        FriendshipRequest fr = new FriendshipRequest(SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername(), requestee.getUsername());
        Call<ResponseBody> call = apiService.sendRequest(fr);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){


                    Log.e("tag", "Uspesno je poslat zahtev za prijateljstvo");
                    Toast.makeText(getApplicationContext(), "Successfully send friendship request", Toast.LENGTH_LONG).show();
                    imageView.setImageResource(R.drawable.ic_send_request);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Wait response to frienship request", Toast.LENGTH_LONG).show();

                        }
                    });

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag","Greska prilikom slanja zahteva za prijateljstvo!!!");

            }





        });
    }

    private void clickInviteButton(String username) {
        Button invite_btn = findViewById(R.id.invite_btn);
        invite_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
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

                EditText editText = popupView.findViewById(R.id.date);
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                editText.setText(localDateTime.toLocalDate().format(formatter));

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                EditText editText1  = popupView.findViewById(R.id.time);
                editText1.setText(localDateTime.format(timeFormatter));


                ImageView remove = popupView.findViewById(R.id.remove);
                remove.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  popupWindow.dismiss();
                                              }
                                          }
                );


                //klik na edit field za unos datuma
                inputDate(popupView);
                inputTime(popupView);
                inputLocation(popupView);
                clickSendButton(popupView, username, popupWindow);

            }
        });
    }

    private void inputLocation(View popupView) {
        //initialize places
        //Set EditText non focusable
        locationEdit = popupView.findViewById(R.id.location);
        locationEdit.setFocusable(false);

        locationEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SelectLocationActivity.class);
                startActivityForResult(intent, 100);

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            //when success
            //initialize place
            String location = data.getStringExtra("location");
//            Toast.makeText(getApplicationContext(), location, Toast.LENGTH_LONG).show();
            locationEdit.setText(location);


        }else{

            Toast.makeText(getApplicationContext(), "Not found place", Toast.LENGTH_LONG).show();
        }
    }

    private void clickSendButton(View popupView, String requestee, PopupWindow popupWindow) {
        //obracamo se back-u
        Button send = popupView.findViewById(R.id.send_btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                String login_user = SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername();
                EditText l_edit = popupView.findViewById(R.id.location);
                String location = l_edit.getText().toString();
                EditText editText = popupView.findViewById(R.id.time);
                String time = editText.getText().toString();
                EditText dateEdit = popupView.findViewById(R.id.date);
                String date = dateEdit.getText().toString();
                if(location.equals("")){
                    Toast.makeText(getApplicationContext(), "Please input location!", Toast.LENGTH_LONG);
                }else{
//                Toast.makeText(getApplicationContext(), date + " " + time, Toast.LENGTH_LONG).show();
                String timestamp = date + " " + time;
                ActivityInviteRequest invite = new ActivityInviteRequest(timestamp, login_user, requestee, location);
                Call<ActivityInviteRequest> call = apiService.sendInvite(invite);
                call.enqueue(new Callback() {

                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.body() != null){


                            Log.e("tag", "Uspesno je poslat zahtev za grupno trcanje");
                            Toast.makeText(getApplicationContext(), "Successfully send activity request", Toast.LENGTH_LONG).show();
                            popupWindow.dismiss();

                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("tag","Greska prilikom slanja zahteva za grupno trcanje!!!");

                    }





                });

            }}
        });

    }


    private void inputTime(View popupView) {
        EditText editText = popupView.findViewById(R.id.time);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(popupView.getContext(), R.style.datepicker, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editText.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                dialog.show();
            }
        });
    }

    private void inputDate(View popupView) {

        EditText editText = popupView.findViewById(R.id.date);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //date picker dialog
                DatePickerDialog picker = new DatePickerDialog(popupView.getContext(), R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthText = "";

                        if((month+ 1) < 10){
                            monthText = "0" + (month+1);
                        }else {
                            monthText = Integer.toString(month+1);
                        }
                        editText.setText((dayOfMonth<10? ("0" + dayOfMonth) : dayOfMonth) + "." + monthText + "." + year +".");
                    }
                }, year, month, day);
                picker.show();
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
