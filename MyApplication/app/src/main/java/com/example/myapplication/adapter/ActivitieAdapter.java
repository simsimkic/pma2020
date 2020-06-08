package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.dto.response.BitmapDtoResponse;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.model.Activitie;
import com.example.myapplication.ui.dialogs.ConfirmActivityDelete;
import com.example.myapplication.util.ApiClient;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitieAdapter extends ArrayAdapter<Activitie> {

    private MapView mMapView;
    private MapController mMapController;
    private ImageButton deleteActButton;

    public ActivitieAdapter(Context context, ArrayList<Activitie> friend){
        super(context, 0, friend);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Activitie activitie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_activitie, parent, false);
        }

        deleteActButton = convertView.findViewById(R.id.delete_act_button);
        configureDeleteActButton();

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

        // SAMO ZA TESTIRANJE///////////////////
        ImageView imageView = convertView.findViewById(R.id.map_image);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BitmapDtoResponse> call = apiService.proba();
        call.enqueue(new Callback<BitmapDtoResponse>() {
            @Override
            public void onResponse(Call<BitmapDtoResponse> call, Response<BitmapDtoResponse> response) {
                Log.e("tag","Bitmap here!");
                if (response.body().getEncodedMap() != null) {
                    String encodedMap = response.body().getEncodedMap();
                    Bitmap bitmap = decodeBase64(encodedMap);
                    imageView.setImageBitmap(bitmap);
                }
            }
            @Override
            public void onFailure(Call<BitmapDtoResponse> call, Throwable t) {
                Log.e("tag","Bitmap failure: " + t);
            }
        });
        /////////////////////////////////////////

        return  convertView;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    private void configureDeleteActButton() {
        deleteActButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConfirmActivityDelete confirmActivityDelete = new ConfirmActivityDelete();
                FragmentManager fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                confirmActivityDelete.show(fragmentManager, "Confirm delete dialog");
            }
        });
    }
}
