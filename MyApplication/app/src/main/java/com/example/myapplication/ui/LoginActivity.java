package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GoalAdapter;
import com.example.myapplication.dto.request.UserLogin;
import com.example.myapplication.dto.response.GoalResponse;
import com.example.myapplication.dto.response.UserResponse;
import com.example.myapplication.dto.response.UserSettingsResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Goal;
import com.example.myapplication.settings.DataBaseHelper;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import java.util.ArrayList;
import java.util.Set;

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
                    loadUserSettings(user.getUsername());
                    SaveSharedPreference.setLoggedIn(getApplicationContext(), true, user);
                    loadSQLiteDB();
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

    private void loadSQLiteDB() {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Set<GoalResponse>> call = apiService.getGoalsByUser(SaveSharedPreference.getLoggedObject(getApplicationContext()).getId());
        call.enqueue(new Callback<Set<GoalResponse>>() {
            @Override
            public void onResponse(Call<Set<GoalResponse>> call, Response<Set<GoalResponse>> response) {

                Log.e("tag","Bitmap here!");
                Set<GoalResponse> data = response.body();
                ArrayList<Goal> arrayOfGoal = new ArrayList<Goal>();
                if (data != null){
                    for (GoalResponse goal: data) {
                        Goal goalTemp = new Goal(goal.getTitle(),goal.getDistance(),goal.getDuration(),true,goal.getEnd_time());
                        Integer i = (int) (long) SaveSharedPreference.getLoggedObject(getApplicationContext()).getId();
                        dataBaseHelper.addGoal(goalTemp,i);
                    }
                }
            }

            @Override
            public void onFailure(Call<Set<GoalResponse>> call, Throwable t) {
                Log.e("tag","Bitmap failure: " + t);
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

    private void loadUserSettings(String username) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserSettingsResponse> call = apiService.getUserSettings(username);
        call.enqueue(new Callback<UserSettingsResponse>() {

            @Override
            public void onResponse(Call<UserSettingsResponse> call, Response<UserSettingsResponse> response) {
                Log.e("tag","Settings loaded ");
                SaveSharedPreference.setSettings(getApplicationContext(), response.body());
            }

            @Override
            public void onFailure(Call<UserSettingsResponse> call, Throwable t) {
                Log.e("tag","Settings loading failure: " + t);
                Log.e("tag","Settings loading failure: " + call.getClass());
            }

        });
    }
}
