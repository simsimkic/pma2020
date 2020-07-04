package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.places.picker.PlacePicker;
import com.mapbox.mapboxsdk.plugins.places.picker.model.PlacePickerOptions;

public class SelectLocationActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 5678;
    private TextView selectedLocationTextView;
    final String MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoicG1hMjAyMCIsImEiOiJja2M2OHd2MWMwOTRpMnZueDQ3ejhnbTZlIn0.62gLuTCl6gOrhxzN_EbYng";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mapbox access token is configured here. This needs to be called either in your application
        // object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, MAPBOX_ACCESS_TOKEN );
        setContentView(R.layout.activity_select_location);
        selectedLocationTextView = findViewById(R.id.selected_location_info_textview);
        goToPickerActivity();
    }

    private void goToPickerActivity() {
        startActivityForResult(
                new PlacePicker.IntentBuilder()
                        .accessToken(MAPBOX_ACCESS_TOKEN)
                        .placeOptions(PlacePickerOptions.builder()
                                .statingCameraPosition(new CameraPosition.Builder()
                                        .target(new LatLng(45.267136, 19.833549)).zoom(16).build())
                                .build())
                        .build(this), REQUEST_CODE);
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            // Show the button and set the OnClickListener()
            Button goToPickerActivityButton = findViewById(R.id.go_to_picker_button);
            goToPickerActivityButton.setVisibility(View.VISIBLE);
            goToPickerActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   goToPickerActivity();

                }

            });
        } else if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
// Retrieve the information from the selected location's CarmenFeature
            CarmenFeature carmenFeature = PlacePicker.getPlace(data);

// Set the TextView text to the entire CarmenFeature. The CarmenFeature
// also be parsed through to grab and display certain information such as
// its placeName, text, or coordinates.
            if (carmenFeature != null) {
                selectedLocationTextView.setText(String.format(
                        getString(R.string.selected_place_info), carmenFeature.toJson()));
                Intent intent =new Intent();
//                Toast.makeText(getApplicationContext(),  carmenFeature.toString(), Toast.LENGTH_LONG).show();
                intent.putExtra("location", carmenFeature.placeName());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }



}
