package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.dto.request.EditUserRequest;
import com.example.myapplication.dto.request.UserRequest;
import com.example.myapplication.dto.response.UserResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    Button save;
    Toolbar toolbar;
    EditText name,address,email, date_of_birth,biography,height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.input_name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        date_of_birth = findViewById(R.id.date_of_birth);
        biography = findViewById(R.id.biography);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);



        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set LoggedIn status to false

                EditUserRequest editUserRequest = new EditUserRequest();
                editUserRequest.setUsername(SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername());
                editUserRequest.setName(name.getText().toString().equals("") ? "" : name.getText().toString() );
                editUserRequest.setAddress(address.getText().toString().equals("") ? "" : address.getText().toString() );
                editUserRequest.setEmail(email.getText().toString().equals("") ? "" : email.getText().toString());
                editUserRequest.setBiography(biography.getText().toString().equals("") ? "" : biography.getText().toString());
                editUserRequest.setHeight(height.getText().toString().equals("") ? "" : height.getText().toString()+"cm");
                editUserRequest.setWeight(weight.getText().toString().equals("") ? "" : weight.getText().toString()+"kg");
                //dodati za date...s


                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<UserResponse> call = apiService.editUser(editUserRequest);
                call.enqueue(new Callback<UserResponse>() {

                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse user = response.body();
                        Log.e("tag","Usao sam sve okej" + user);
                        SaveSharedPreference.setLoggedIn(getApplicationContext(), true, user);
                        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.e("tag","Usao sam nista nije okej" + t);
                        Log.e("tag","Usao sam nista nije okej" + call.getClass());
                    }

                });


            }
        });

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_36dp);
        toolbar.setTitle("Edit Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

    }
}
