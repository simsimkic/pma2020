package com.example.myapplication.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FriendAdapter;
import com.example.myapplication.mokap_data.Friends;

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

        FriendAdapter adapter = new FriendAdapter(getActivity(), Friends.getFriends());



        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.fried_list);
        listView.setAdapter(adapter);

        return view;
    }
}
