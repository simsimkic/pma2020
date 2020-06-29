package com.example.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivitieAdapter;
import com.example.myapplication.adapter.FriendAdapter;
import com.example.myapplication.adapter.GoalAdapter;
import com.example.myapplication.dto.response.BitmapDtoResponse;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.dto.response.GoalResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Activitie;
import com.example.myapplication.model.Friend;
import com.example.myapplication.model.Goal;
import com.example.myapplication.ui.FindFriendsActivity;
import com.example.myapplication.ui.FriendDetailActivity;
import com.example.myapplication.ui.GoalDetailActivity;
import com.example.myapplication.ui.MenageGoals;
import com.example.myapplication.ui.ProfileActivity;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoalsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GoalsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoalsFragment newInstance(String param1, String param2) {
        GoalsFragment fragment = new GoalsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onStart() {

        super.onStart();
        FloatingActionButton btn = (FloatingActionButton) getActivity().findViewById(R.id.floating_action_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MenageGoals.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goals, container, false);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Set<GoalResponse>> call = apiService.getGoalsByUser(SaveSharedPreference.getLoggedObject(getContext()).getId());
        call.enqueue(new Callback<Set<GoalResponse>>() {
            @Override
            public void onResponse(Call<Set<GoalResponse>> call, Response<Set<GoalResponse>> response) {

                Log.e("tag","Bitmap here!");
                Set<GoalResponse> data = response.body();
                ArrayList<Goal> arrayOfGoal = new ArrayList<Goal>();
                ArrayList<GoalResponse> goalReponses = new ArrayList<GoalResponse>();
                if (data != null){
                    for (GoalResponse goal: data) {
                        Log.e("tag","Proba : " + goal.getTimestamp());
                        Goal activity = new Goal(goal.getTitle(),goal.getDistance(),goal.getDuration(),true,goal.getEnd_time());
                        arrayOfGoal.add(activity);
                        goalReponses.add(goal);
                    }
                }


                // Create the adapter to convert the array to views
                GoalAdapter adapter = new GoalAdapter(getActivity(), arrayOfGoal);
                // Attach the adapter to a ListView
                ListView listView = (ListView) view.findViewById(R.id.goals_list);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        GoalResponse goal = goalReponses.get(position);
                        Intent intent = new Intent(getActivity(), GoalDetailActivity.class);

                        intent.putExtra("goal", goal);

                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<Set<GoalResponse>> call, Throwable t) {
                Log.e("tag","Bitmap failure: " + t);
            }
        });

        return view;
    }
}
