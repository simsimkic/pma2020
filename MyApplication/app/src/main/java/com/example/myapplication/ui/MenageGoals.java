package com.example.myapplication.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.myapplication.dto.request.SaveGoalRequest;
import com.example.myapplication.dto.response.GoalResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenageGoals extends AppCompatActivity {

    Button save;
    Toolbar toolbar;
    EditText name, distance, duration, end_time;
    final Calendar myCalendar = Calendar.getInstance();

    private void updateLabel() {
        String myFormat = "dd.MM.yyyy. HH:mm"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        end_time.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menage_goals);

        name = findViewById(R.id.input_name);
        distance = findViewById(R.id.distance);
        duration = findViewById(R.id.duration);
        end_time = findViewById(R.id.end_time);


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        end_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datepicker = new DatePickerDialog(MenageGoals.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datepicker.show();
                datepicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FFC107"));
                datepicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF5722"));

            }
        });



        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set LoggedIn status to false

                SaveGoalRequest saveGoalRequest = new SaveGoalRequest();
                saveGoalRequest.setUser_id(SaveSharedPreference.getLoggedObject(getApplicationContext()).getId());
                saveGoalRequest.setTitle(name.getText().toString().equals("") ? "" : name.getText().toString() );
                saveGoalRequest.setDistance(Double.parseDouble(distance.getText().toString()));
                saveGoalRequest.setDuration(Double.parseDouble(duration.getText().toString()));
                saveGoalRequest.setEnd_time(end_time.getText().toString());
                String myFormat = "dd.MM.yyyy. HH:mm"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                saveGoalRequest.setTimestampe(sdf.format(new Date()));

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<GoalResponse> call = apiService.saveGoal(saveGoalRequest);
                call.enqueue(new Callback<GoalResponse>() {

                    @Override
                    public void onResponse(Call<GoalResponse> call, Response<GoalResponse> response) {
                        GoalResponse user = response.body();
                        Log.e("tag","Usao sam sve okej" + user);
                        Intent intent = new Intent(MenageGoals.this, ProfileActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<GoalResponse> call, Throwable t) {
                        Log.e("tag","Usao sam nista nije okej" + t);
                        Log.e("tag","Usao sam nista nije okej" + call.getClass());
                    }

                });


            }
        });

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_36dp);
        toolbar.setTitle("Create Goals");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                Intent intent = new Intent(MenageGoals.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

    }
}
