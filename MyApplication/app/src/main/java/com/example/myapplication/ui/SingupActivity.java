package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dto.request.UserRequest;
import com.example.myapplication.dto.response.UserResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.util.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingupActivity extends AppCompatActivity {

    EditText name,address,email, username,password,confirmPassword;
    Button submitBtn;
    TextView linkSingin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        address = findViewById(R.id.input_address);
        name = findViewById(R.id.input_name);
        email = findViewById(R.id.input_email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.input_password);
        confirmPassword = findViewById(R.id.input_confirmPassword);
        submitBtn = findViewById(R.id.btn_singUp);
        linkSingin = findViewById(R.id.link_login);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Make form visible
                if( TextUtils.isEmpty(username.getText())){
                    username.setError( "username is required!" );
                }if( TextUtils.isEmpty(password.getText())){
                    password.setError( "Password is required!" );
                }if( TextUtils.isEmpty(confirmPassword.getText())){
                    confirmPassword.setError( "Confirm password is required!" );
                }if( !TextUtils.equals(password.getText(),confirmPassword.getText()) ){
                    confirmPassword.setError( "Passwords do not match!" );
                    password.setError( "Passwords do not match!" );
                }else{
                    UserRequest userinfo = new UserRequest();
                    userinfo.setName(name.getText().toString());
                    userinfo.setAddress(address.getText().toString());
                    userinfo.setEmail(email.getText().toString());
                    userinfo.setUsername(username.getText().toString());
                    userinfo.setPassword(password.getText().toString());

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<UserResponse> call = apiService.registerUser(userinfo);
                    call.enqueue(new Callback<UserResponse>() {

                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            UserResponse user = response.body();
                            Log.e("tag","Usao sam sve okej" + user);

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            login();
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Log.e("tag","Usao sam nista nije okej" + t);
                            Log.e("tag","Usao sam nista nije okej" + call.getClass());
                        }

                    });



                }
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
     */
    private void login() {

        //potrebno odraditi login

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);



    }
}
