package com.example.myapplication.ui.dialogs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.R;
import com.example.myapplication.ui.TrackingActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShareActivityDialog extends AppCompatDialogFragment implements LocationListener {

    private TextView stepsTextView;
    private TextView durationTextView;
    private TextView distanceTextView;
    private MapView mapView;
    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.share_activity_dialog, null);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        findAllViews();
        setMapPermission();
        configureMap();
        showTrackingData();

        builder.setView(view)
                .setTitle(R.string.share_act_header)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.share, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO
                    }
                });
        return builder.create();
    }

    private void findAllViews() {
        mapView = view.findViewById(R.id.share_act_map);
        EditText shareActivityTextEdit = view.findViewById(R.id.share_act_text_edit);
        stepsTextView = view.findViewById(R.id.final_steps_text_view);
        durationTextView = view.findViewById(R.id.final_duration_text_view);
        distanceTextView = view.findViewById(R.id.final_distance_text_view);
    }

    private void setMapPermission() {
        Configuration.getInstance().setUserAgentValue(getActivity().getPackageName());
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    private void configureMap() {
        showTrackingData();
        Marker startMarker = setStartMarkerOnMap();
        setEndMarkerOnMap();

        mapView.getZoomController().activate();
        MapController mapController = (MapController) mapView.getController();
        mapController.setZoom(18);
        mapController.setCenter(startMarker.getPosition());
    }

    private Marker setStartMarkerOnMap() {
        Marker startMarker = TrackingActivity.getStartMarker();
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().add(startMarker);
        return startMarker;
    }

    private void setEndMarkerOnMap() {
        Marker endMarker = TrackingActivity.getEndMarker();
        endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().add(endMarker);
    }

    private void showTrackingData() {
        ArrayList<Object> data = TrackingActivity.getData();
        distanceTextView.setText("Distance: " + formatDistance(data.get(0)));
        durationTextView.setText("Duration: " + formatTime(data.get(1)));
        stepsTextView.setText("Steps: " + data.get(2));
        showRoute((ArrayList<GeoPoint>)data.get(3));
    }

    private void showRoute(ArrayList<GeoPoint> locations) {
        List<Overlay> mapOverlayers = mapView.getOverlays();
        mapOverlayers.add(new RouteOverlay(locations));
    }

    @SuppressLint("DefaultLocale")
    private String formatTime(Object durationInSeconds) {
        long hours = (long)durationInSeconds / 3600;
        long minutes = ((long)durationInSeconds % 3600) / 60;
        long seconds = (long)durationInSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private String formatDistance(Object distance) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format((double)distance);
    }

    @Override
    public void onLocationChanged(Location location) { }

    @Override
    public void onProviderDisabled(String provider) { }

    @Override
    public void onProviderEnabled(String provider) { }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }

}

class RouteOverlay extends Overlay {
    private ArrayList<GeoPoint> locations;

    RouteOverlay(ArrayList<GeoPoint> locations){
        this.locations = locations;
    }

    public void draw(Canvas canvas, MapView mapv, boolean shadow){
        super.draw(canvas, mapv, shadow);

        Paint mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(4);

        for (int i = 0; i < locations.size()-1; i++) {
            Point p1 = new Point();
            Point p2 = new Point();
            Path path = new Path();

            int nextLocation = 1;
            if (locations.get(i).distanceToAsDouble(locations.get(i+1)) > 15) {
                nextLocation = 2;
            }

            Projection projection = mapv.getProjection();
            projection.toPixels(locations.get(i), p1);
            projection.toPixels(locations.get(i + nextLocation), p2);

            path.moveTo(p2.x, p2.y);
            path.lineTo(p1.x,p1.y);

            canvas.drawPath(path, mPaint);
        }
    }
}
