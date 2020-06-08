package com.example.myapplication.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivitieAdapter;
import com.example.myapplication.model.Activitie;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public ActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        Date date = new Date();
        Activitie activity1 = new Activitie("April goal", 3.0 , 1.0, date.toString());
        Activitie activity2 = new Activitie("May goal", 3.0 , 1.0, date.toString());

        // Construct the data source
        ArrayList<Activitie> arrayOfActivity = new ArrayList<Activitie>();
        arrayOfActivity.add(activity1);
        arrayOfActivity.add(activity2);
        // Create the adapter to convert the array to views
        ActivitieAdapter adapter = new ActivitieAdapter(getActivity(), arrayOfActivity);

        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.goals_list);
        listView.setAdapter(adapter);

        return view;
    }
}
