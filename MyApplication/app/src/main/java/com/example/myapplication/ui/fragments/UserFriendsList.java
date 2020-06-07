package com.example.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FriendAdapter;
import com.example.myapplication.adapter.UserFriendListAdapter;
import com.example.myapplication.dto.response.FriendResponse;
import com.example.myapplication.dto.response.UserResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.mokap_data.Friends;
import com.example.myapplication.ui.EditProfileActivity;
import com.example.myapplication.ui.ProfileActivity;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFriendsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFriendsList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ArrayList<FriendResponse> friends;

    public UserFriendsList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFriendsList.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFriendsList newInstance(String param1, String param2) {
        UserFriendsList fragment = new UserFriendsList();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_friends_list, container, false);

        //dobavljanje svih prijatelja iz baze

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<FriendResponse>> call = apiService.getFriends("pera");
        call.enqueue(new Callback<ArrayList<FriendResponse>>() {

            @Override
            public void onResponse(Call<ArrayList<FriendResponse>> call, Response<ArrayList<FriendResponse>> response) {
                friends = response.body();
               Log.e("tag", "OK dobavljanje prijatelja: "  + friends.size());

            }

            @Override
            public void onFailure(Call<ArrayList<FriendResponse>> call, Throwable t) {
                Log.e("tag","Usao sam nista nije okej" + t);
                Log.e("tag","Usao sam nista nije okej" + call.getClass());
            }



        });
        UserFriendListAdapter adapter = new UserFriendListAdapter(getActivity(),friends);



        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.fried_list);
        listView.setAdapter(adapter);

        return view;
    }
}
