package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.dialogs.ShareActivityDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TrackingActivity extends AppCompatActivity implements LocationListener, SensorEventListener {

    private Intent intent;
    private BottomNavigationView bottomNavigationView;
    private MapView mapView;
    private Button startButton;
    private Button stopButton;
    private TextView stepsTextView;
    private TextView durationTextView;
    private TextView distanceTextView;
    private LocationManager locationManager;
    private MapController mapController;
    private GeoPoint currentLocation;
    private Marker currentLocationMarker;
    private Marker trackingLocationMarker;
    private static Marker startMarker;
    private static Marker endMarker;
    boolean trackingStarted = false;
    private float initialSteps = -1;
    private float calculatedSteps;
    private static float finalSteps;
    private Timer timer = new Timer();
    private long durationInSeconds;
    private static long finalDuration;
    private double distance;
    private static double finalDistance;
    private int userHeight = 170;
    private double userStepLength = userHeight *  0.413;
    private static ArrayList<GeoPoint> allLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        findAllViews();
        setBottomNavigation();
        setMapPermission();
        configureMap();
        configureStartButton();
        configureStopButton();
    }

    private void findAllViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);
        mapView = findViewById(R.id.tracking_map);
        stepsTextView = findViewById(R.id.steps_text_view);
        durationTextView = findViewById(R.id.duration_text_view);
        distanceTextView = findViewById(R.id.distance_text_view);
    }

    private void setBottomNavigation() {
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(TrackingActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.tracking:
                        return true;
                    case R.id.profile:
                        intent = new Intent(TrackingActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.activities:
                        intent = new Intent(TrackingActivity.this, GroupActivitiesActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

    private void setMapPermission() {
        Configuration.getInstance().setUserAgentValue(getPackageName());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        if (locationManager != null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3*1000, 0, this);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3*1000, 0, this);
        }
    }

    private void configureMap() {
        mapView.getZoomController().activate();
        mapController = (MapController) mapView.getController();
        mapController.setZoom(18);
        mapController.setCenter(currentLocation);

        getLastKnownLocation();
        initializeMarkers();
        setCurrentLocationMarkerOnMap();
    }

    private void getLastKnownLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                currentLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
            }
        } else {
            currentLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
        }
    }

    private void initializeMarkers() {
        trackingLocationMarker = new Marker(mapView);
        startMarker = new Marker(mapView);
        endMarker = new Marker(mapView);
        currentLocationMarker = new Marker(mapView);
    }

    private void setCurrentLocationMarkerOnMap() {
        currentLocationMarker.setPosition(currentLocation);
        currentLocationMarker.setIcon(this.getResources().getDrawable(R.drawable.ic_my_location_orange_24dp));
        currentLocationMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().add(currentLocationMarker);
    }

    private void configureStartButton() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(TrackingActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(TrackingActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                trackingStarted = true;
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                setTimer();
                setTimerForSavingLocations();
                setStepCounterSensor();
                removeMarkerFromMap(currentLocationMarker);
                setTrackingLocationMarkerOnMap();
                setStartMarker();
            }
        });
    }

    private void setTimer() {
        durationInSeconds = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        durationTextView.setText(formatTime(durationInSeconds));
                        durationInSeconds++;
                    }
                });
            }
        }, 0, 1000);
    }

    private void setTimerForSavingLocations() {
        allLocations.clear();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        allLocations.add(currentLocation);
                    }
                });
            }
        }, 0, 3000);
    }

    @SuppressLint("DefaultLocale")
    private String formatTime(long durationInSeconds) {
        long hours = durationInSeconds / 3600;
        long minutes = (durationInSeconds % 3600) / 60;
        long seconds = durationInSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void setStepCounterSensor() {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor;
        if (sensorManager != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    private void removeMarkerFromMap(Marker marker) {
        mapView.getOverlays().remove(marker);
    }

    private void setStartMarker() {
        startMarker.setPosition(currentLocation);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(this.getResources().getDrawable(R.drawable.ic_location_on_green_24dp));
    }

    private void configureStopButton() {
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allLocations.add(currentLocation);
                finalSteps = calculatedSteps;
                initialSteps = -1;
                finalDistance = distance;
                distance = 0;
                finalDuration = durationInSeconds;
                durationInSeconds = 0;
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                resetTrackingInfo();
                openShareActivityDialog();
                resetTimer();
            }
        });
    }

    private void resetTimer() {
        timer.cancel();
        timer = new Timer();
    }

    private void resetTrackingInfo() {
        trackingStarted = false;
        durationTextView.setText("0");
        stepsTextView.setText("0");
        distanceTextView.setText("0");
        removeMarkerFromMap(trackingLocationMarker);
    }

    private void openShareActivityDialog() {
        ShareActivityDialog shareActivityDialog = new ShareActivityDialog();
        shareActivityDialog.show(getSupportFragmentManager(), "Share dialog");
    }

    public static Marker getStartMarker() {
        return startMarker;
    }

    public static Marker getEndMarker() {
        return endMarker;
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
        mapController.setCenter(currentLocation);
        if (trackingStarted) {
            removeMarkerFromMap(trackingLocationMarker);
            setTrackingLocationMarkerOnMap();
            setEndMarker();
        } else {
            removeMarkerFromMap(currentLocationMarker);
            setCurrentLocationMarkerOnMap();
        }

    }

    private void setTrackingLocationMarkerOnMap() {
        trackingLocationMarker.setPosition(currentLocation);
        trackingLocationMarker.setIcon(this.getResources().getDrawable(R.drawable.ic_location_on_orange_24dp));
        trackingLocationMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().add(trackingLocationMarker);
    }

    private void setEndMarker() {
        endMarker.setPosition(currentLocation);
        endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        endMarker.setIcon(this.getResources().getDrawable(R.drawable.ic_location_on_red_24dp));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (trackingStarted) {
            if (initialSteps == -1) {
                initialSteps = event.values[0];
                calculatedSteps = 0;
            } else {
                calculatedSteps = event.values[0] - initialSteps;
                distance = (calculatedSteps * userStepLength) / 100000;
            }
            distanceTextView.setText(formatDistance(distance));
            stepsTextView.setText(String.valueOf((int)calculatedSteps));
        }
    }

    private String formatDistance(double distance) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(distance);
    }

    public static ArrayList getData() {
        ArrayList data = new ArrayList();
        data.add(finalDistance);
        data.add(finalDuration);
        data.add((int)finalSteps);
        data.add(allLocations);
        return data;
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

}
