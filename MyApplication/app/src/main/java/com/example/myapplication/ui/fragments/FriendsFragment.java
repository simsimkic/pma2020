package com.example.myapplication.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FriendAdapter;
import com.example.myapplication.model.Friend;
import com.example.myapplication.ui.FindFriendsActivity;
import com.example.myapplication.ui.SingupActivity;

import java.util.ArrayList;

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
        Button btn = (Button)getActivity().findViewById(R.id.btn_addFriends);
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

        Friend friend1 = new Friend("Friend 1");
        Friend friend2 = new Friend("Friend 2");
        Friend friend3 = new Friend("Friend 3");
        Friend friend4 = new Friend("Friend 4");
        Friend friend5 = new Friend("Friend 5");
        Friend friend6 = new Friend("Friend 6");
        Friend friend7 = new Friend("Friend 7");
        Friend friend8 = new Friend("Friend 8");
        Friend friend9 = new Friend("Friend 9");
        // Construct the data source
        ArrayList<Friend> arrayOfUsers = new ArrayList<Friend>();
        arrayOfUsers.add(friend1);
        arrayOfUsers.add(friend2);
        arrayOfUsers.add(friend3);
        arrayOfUsers.add(friend4);
        arrayOfUsers.add(friend5);
        arrayOfUsers.add(friend6);
        arrayOfUsers.add(friend7);
        arrayOfUsers.add(friend8);
        arrayOfUsers.add(friend9);
        // Create the adapter to convert the array to views
        FriendAdapter adapter = new FriendAdapter(getActivity(), arrayOfUsers);



        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.fried_list);
        listView.setAdapter(adapter);

        return view;
    }
}
