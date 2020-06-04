package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.dto.request.UserLogin;
import com.example.myapplication.dto.request.UserRequest;
import com.example.myapplication.dto.response.UserResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                if( TextUtils.isEmpty(username.getText())){
                    username.setError( "username is required!" );
                }if( TextUtils.isEmpty(password.getText())){
                    password.setError( "Password is required!" );
                }else{
                    userLogin(username, password);
                }

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
    private void userLogin(EditText username, EditText password) {

        //potrebno odraditi login

        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(username.getText().toString());
        userLogin.setPassword(password.getText().toString());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> call = apiService.loginUser(userLogin);
        call.enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse user = response.body();

                Log.e("tag","PRovera emaila: " + user.getEmail());
                if(user != null){
                    Log.e("tag","Usao sam u login sve okej" + user);
                    SaveSharedPreference.setLoggedIn(getApplicationContext(), true, user);
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }else{
                    username.setError("Username or password is incorect!");
                    password.setError("Username or password is incorect!");
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("tag","Usao sam  u login nista nije okej" + t);
                Log.e("tag","Usao sam  u login nista nije okej" + call.getClass());
                username.setError("Username or password is incorect!");
                password.setError("Username or password is incorect!");
            }

        });
    }

    /**
     * SingUp API call
     * TODO: Please modify according to your need it is just an example
     */
    private void singUp() {

        //potrebno odraditi login

        Intent intent = new Intent(getApplicationContext(), SingupActivity.class);
        startActivity(intent);

    }
}
