package com.example.myapplication.ui.fragments;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivitieAdapter;
import com.example.myapplication.dto.response.BitmapDtoResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Activitie;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.SaveSharedPreference;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Set<BitmapDtoResponse>> call = apiService.getActivitiesByUser(SaveSharedPreference.getLoggedObject(getContext()).getId());
        call.enqueue(new Callback<Set<BitmapDtoResponse>>() {
            @Override
            public void onResponse(Call<Set<BitmapDtoResponse>> call, Response<Set<BitmapDtoResponse>> response) {

                Log.e("tag","Bitmap here!");
                Set<BitmapDtoResponse> data = response.body();
                ArrayList<Activitie> arrayOfActivity = new ArrayList<Activitie>();
                if (data != null){
                    for (BitmapDtoResponse activitie: data) {
                        Log.e("tag","Proba : " + activitie.getDateTime());
                        Activitie activity = new Activitie(activitie.getDistance(),activitie.getDuration(),activitie.getDateTime(),decodeBase64(activitie.getEncodedMap()));
                        arrayOfActivity.add(activity);
                    }
                }

                // Create the adapter to convert the array to views
                ActivitieAdapter adapter = new ActivitieAdapter(getActivity(), arrayOfActivity);
                // Attach the adapter to a ListView
                ListView listView = (ListView) view.findViewById(R.id.goals_list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Set<BitmapDtoResponse>> call, Throwable t) {
                Log.e("tag","Bitmap failure: " + t);
            }
        });

        return view;
    }


    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
