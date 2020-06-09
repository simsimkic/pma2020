package com.example.myapplication.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FriendAdapter;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Friend;
import com.example.myapplication.ui.FindFriendsActivity;
import com.example.myapplication.ui.FriendDetailActivity;
import com.example.myapplication.ui.ProfileActivity;
import com.example.myapplication.ui.SingupActivity;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
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
                Intent intent = new Intent(getActivity().getApplicationContext(), FindFriendsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        //dobavljanje svih prijatelja iz baze

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<FriendResponse>> call = apiService.getFriends(SaveSharedPreference.getLoggedObject(getActivity().getApplicationContext()).getUsername());
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                if(response.body() != null){
                    ArrayList<FriendResponse> friends = (ArrayList<FriendResponse>) response.body();
                    ArrayList<FriendResponse> friends1 = friends;
                    Log.e("tag", "Dobavili smo prijatelje, ima ih: " + friends.size());

                    ListView listView = (ListView) view.findViewById(R.id.fried_list);
                    // Create the adapter to convert the array to views
                    FriendAdapter adapter = new FriendAdapter(getActivity(), friends1);
                    // Attach the adapter to a ListView

                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            FriendResponse friend = friends.get(position);
//                Toast.makeText(getApplicationContext(), "You click on friend " + friend.getName(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), FriendDetailActivity.class);

                            intent.putExtra("friend", friend);

                            startActivity(intent);
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("tag","Greska prilikom dobavljanja prijatelja!!!");

            }





        });




        return view;
    }
}
