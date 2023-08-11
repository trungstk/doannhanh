package com.example.project_doannhanhsol.GUI.LocationSettings;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATION;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.AUTOCOMPLETE_REQUEST_CODE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.CheckLocation;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.GETLATITUDE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.GETLONGITUDE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.LOCATION;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.SHARED;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.TAG_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationSettingsActivity extends AppCompatActivity {
    private TextView TextView_Skip;
    private Button Button_Location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_settings);
        intiuid();
        onclick();
    }
    private void onclick() {
        TextView_Skip.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TextView_Skip.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        TextView_Skip.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(LocationSettingsActivity.this, MainActivity.class));
                        finish();
                    }
                }
                return true;
            }
        });
        Button_Location.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Button_Location.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Button_Location.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(LocationSettingsActivity.this, AddLocationActivity.class));
                    }
                }
                return true;
            }
        });
    }

    private void intiuid() {
        ANIMATION(this);
        SHARED(this);
        TextView_Skip = findViewById(R.id.TextView_Skip);
        Button_Location = findViewById(R.id.Button_Location);
        CheckLocation(this,locationCallback);
    }
    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            for (Location location : locationResult.getLocations()) {
                Geocoder geocoder = new Geocoder(LocationSettingsActivity.this, Locale.getDefault());
                try {
                    GETLATITUDE = location.getLatitude();
                    GETLONGITUDE = location.getLongitude();
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), AUTOCOMPLETE_REQUEST_CODE);
                    LOCATION = addresses.get(0).getAddressLine(0);
                } catch (IOException e) {
                    Log.e(TAG_, e.getMessage());
                }
            }
        }
    };
    @Override
    public void onBackPressed() {

    }
}