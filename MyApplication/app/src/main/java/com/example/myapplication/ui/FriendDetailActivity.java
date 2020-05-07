package com.example.myapplication.ui;

import android.os.Bundle;

import com.example.myapplication.ui.fragments.ActivityFragment;
import com.example.myapplication.ui.fragments.ActivityInvitationFragment;
import com.example.myapplication.ui.fragments.User_profile_details_1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class FriendDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_36dp);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        TextView tv = findViewById(R.id.name);
        tv.setText(getIntent().getStringExtra("user_name"));

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

}
