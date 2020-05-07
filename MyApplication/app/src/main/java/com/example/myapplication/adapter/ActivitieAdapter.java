package com.example.myapplication.adapter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.myapplication.R;
import com.example.myapplication.model.Activitie;
import com.example.myapplication.model.Friend;
import com.example.myapplication.model.Goal;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

public class ActivitieAdapter extends ArrayAdapter<Activitie> {

    private MapView mMapView;
    private MapController mMapController;

    public ActivitieAdapter(Context context, ArrayList<Activitie> friend){
        super(context, 0, friend);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Activitie activitie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_activitie, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView distance = convertView.findViewById(R.id.distance);
        TextView duration = convertView.findViewById(R.id.duration);
        TextView time = convertView.findViewById(R.id.time);

        mMapView = convertView.findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.getZoomController().activate();
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(13);
        GeoPoint gPt = new GeoPoint(51500000, -150000);
        mMapController.setCenter(gPt);

        name.setText(activitie.getName());
        distance.setText(activitie.getDistance() + " km" );
        duration.setText(activitie.getDuration() + " h");
        time.setText(activitie.getTime().toString());

        return  convertView;
    }
}
