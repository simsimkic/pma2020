package com.example.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FriendListAdapter;
import com.example.myapplication.model.Friend;
import com.example.myapplication.mokap_data.Friends;
import com.example.myapplication.ui.FriendDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowFriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowFriendsFragment extends Fragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShowFriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowFriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowFriendsFragment newInstance(String param1, String param2) {
        ShowFriendsFragment fragment = new ShowFriendsFragment();
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
//        ListView list = getActivity().findViewById(R.id.friends_show);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity().getApplicationContext(), "You click on some friend " + position,
//                        Toast.LENGTH_LONG).show();
//            }
//        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_show_friends, container, false);
        ListView list = view.findViewById(R.id.friends_show);
        FriendListAdapter adapter = new FriendListAdapter(getActivity().getApplicationContext(), Friends.getFriends());
        list.setAdapter(adapter);



        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Friend friend = Friends.getFriends().get(position);
        Toast.makeText(getActivity(), "Click on the friend " + friend.getName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), FriendDetailActivity.class);
        intent.putExtra("name", friend.getName());
        startActivity(intent);
    }
}
