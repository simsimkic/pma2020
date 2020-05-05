package com.example.myapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FriendListAdapter;
import com.example.myapplication.model.Friend;
import com.example.myapplication.mokap_data.Friends;
import com.example.myapplication.ui.FriendDetailActivity;

public class FriendShowFragment extends ListFragment {
    public static FriendShowFragment newInstance() {
        return new FriendShowFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_friends_show, vg, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Friend friends = Friends.getFriends().get(position);

        /*
         * Ako nasoj aktivnosti zelimo da posaljemo nekakve podatke
         * za to ne treba da koristimo konstruktor. Treba da iskoristimo
         * identicnu strategiju koju smo koristili kda smo radili sa
         * fragmentima! Koristicemo Bundle za slanje podataka. Tacnije
         * intent ce formirati Bundle za nas, ali mi treba da pozovemo
         * odgovarajucu putExtra metodu.
         * */
        Intent intent = new Intent(getActivity(), FriendDetailActivity.class);
        intent.putExtra("name", friends.getName());
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();

        //Dodaje se adapter
        FriendListAdapter adapter = new FriendListAdapter(getActivity(), Friends.getFriends());
        setListAdapter(adapter);
    }

}
