package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.util.SaveSharedPreference;

public class SingupActivity extends AppCompatActivity {

    Button submitBtn;
    TextView linkSingin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        submitBtn = findViewById(R.id.btn_singUp);
        linkSingin = findViewById(R.id.link_login);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Make form visible
                login();
            }
        });

        linkSingin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Make form visible
                login();
            }
        });
    }

    /**
     * Login API call
     * TODO: Please modify according to your need it is just an example
     * @param username
     * @param password
     */
    private void login() {

        //potrebno odraditi login

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);



    }
}
