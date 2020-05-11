package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.util.SaveSharedPreference;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button submitBtn;
    TextView linkSingup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        submitBtn = findViewById(R.id.btn_login);
        linkSingup = findViewById(R.id.link_signup);

        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Make form visible
                userLogin(username.getText().toString(), password.getText().toString());
            }
        });

        linkSingup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Make form visible
                singUp();
            }
        });
    }

    /**
     * Login API call
     * TODO: Please modify according to your need it is just an example
     * @param username
     * @param password
     */
    private void userLogin(String username, String password) {

        //potrebno odraditi login

        SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);



    }

    /**
     * SingUp API call
     * TODO: Please modify according to your need it is just an example
     * @param username
     * @param password
     */
    private void singUp() {

        //potrebno odraditi login

        Intent intent = new Intent(getApplicationContext(), SingupActivity.class);
        startActivity(intent);

    }
}
