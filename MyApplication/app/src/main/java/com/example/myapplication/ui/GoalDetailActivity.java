package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.dto.request.SaveGoalRequest;
import com.example.myapplication.dto.response.GoalResponse;
import com.example.myapplication.dto.response.UserResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Goal;
import com.example.myapplication.settings.DataBaseHelper;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GoalDetailActivity extends AppCompatActivity {

    EditText name, distance, duration, end_time;
    Button delete,update,archived;
    final Calendar myCalendar = Calendar.getInstance();

    private void updateLabel() {
        String myFormat = "dd.MM.yyyy. HH:mm"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        end_time.setText(sdf.format(myCalendar.getTime()));
    }
    private void loadSQLiteDB() {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        dataBaseHelper.clear();
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
                        Goal goalTemp = new Goal(goal.getTitle(),goal.getDistance(),goal.getDuration(),goal.getArchived(),goal.getEnd_time());
                        String i = SaveSharedPreference.getLoggedObject(getApplicationContext()).getUsername();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);
        GoalResponse goal = (GoalResponse) getIntent().getSerializableExtra("goal");

        name = findViewById(R.id.input_name);
        distance = findViewById(R.id.distance);
        duration = findViewById(R.id.duration);
        end_time = findViewById(R.id.end_time);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        archived = findViewById(R.id.archived);


        name.setText(goal.getTitle());
        distance.setText(String.valueOf(goal.getDistance()));
        duration.setText(String.valueOf(goal.getDuration()));
        end_time.setText(goal.getEnd_time());

        if(goal.getArchived()){
            name.setFocusable(false);
            distance.setFocusable(false);
            duration.setFocusable(false);
            end_time.setFocusable(false);
            name.setFocusable(false);
            delete.setEnabled(false);
            update.setEnabled(false);
            archived.setEnabled(false);
        }else{
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

            end_time.setFocusable(false);
            end_time.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DatePickerDialog datepicker = new DatePickerDialog(GoalDetailActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH));
                    datepicker.show();
                    datepicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FFC107"));
                    datepicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF5722"));

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    loadSQLiteDB();
                    Call<Boolean> call = apiService.deleteGoal(goal.getId());
                    call.enqueue(new Callback<Boolean>() {

                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Intent intent = new Intent(GoalDetailActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.e("tag","Usao sam nista nije okej kod goalsa" + goal.getId());
                            Log.e("tag","Usao sam nista nije okej kod golasa" + call.getClass());
                        }

                    });
                }
            });
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SaveGoalRequest goalRequest = new SaveGoalRequest();

                    String myFormat = "dd.MM.yyyy. HH:mm"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    loadSQLiteDB();
                    goalRequest.setTimestampe(sdf.format(new Date()));
                    goalRequest.setEnd_time(end_time.getText().toString());
                    goalRequest.setTitle(name.getText().toString());
                    goalRequest.setDistance(Double.parseDouble(distance.getText().toString()));
                    goalRequest.setDuration(Double.parseDouble(duration.getText().toString()));
                    goalRequest.setArchived(goal.getArchived());
                    goalRequest.setId(goal.getId());
                    goalRequest.setUser_id(SaveSharedPreference.getLoggedObject(getApplicationContext()).getId());
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<GoalResponse> call = apiService.updateGoal(goalRequest);
                    call.enqueue(new Callback<GoalResponse>() {

                        @Override
                        public void onResponse(Call<GoalResponse> call, Response<GoalResponse> response) {
                            Intent intent = new Intent(GoalDetailActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<GoalResponse> call, Throwable t) {
                            Log.e("tag","Usao update sam nista nije okej kod goalsa" + goal.getId());
                            Log.e("tag","Usao update sam nista nije okej kod golasa" + call.getClass());
                        }

                    });

                }
            });
            archived.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SaveGoalRequest goalRequest = new SaveGoalRequest();

                    String myFormat = "dd.MM.yyyy. HH:mm"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    loadSQLiteDB();
                    goalRequest.setTimestampe(sdf.format(new Date()));
                    goalRequest.setEnd_time(end_time.getText().toString());
                    goalRequest.setTitle(name.getText().toString());
                    goalRequest.setDistance(Double.parseDouble(distance.getText().toString()));
                    goalRequest.setDuration(Double.parseDouble(duration.getText().toString()));
                    goalRequest.setArchived(true);
                    goalRequest.setId(goal.getId());
                    goalRequest.setUser_id(SaveSharedPreference.getLoggedObject(getApplicationContext()).getId());

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<GoalResponse> call = apiService.updateGoal(goalRequest);
                    call.enqueue(new Callback<GoalResponse>() {

                        @Override
                        public void onResponse(Call<GoalResponse> call, Response<GoalResponse> response) {
                            Intent intent = new Intent(GoalDetailActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<GoalResponse> call, Throwable t) {
                            Log.e("tag","Usao update sam nista nije okej kod goalsa" + goal.getId());
                            Log.e("tag","Usao update sam nista nije okej kod golasa" + call.getClass());
                        }

                    });
                }
            });
        }




    }
}
