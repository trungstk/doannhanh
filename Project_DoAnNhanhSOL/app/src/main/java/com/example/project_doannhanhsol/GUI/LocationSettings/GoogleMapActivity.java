package com.example.project_doannhanhsol.GUI.LocationSettings;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ADDRESS;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.AUTOCOMPLETE_REQUEST_CODE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.GETLATITUDE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.GETLONGITUDE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IMG_ONBACK;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.LOCATION;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.TEXTVIEWADDRESS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class GoogleMapActivity extends AppCompatActivity {
    private TextView TextViewLocation;
    private EditText EditTextSearch;
    private Button ButtonSave;
    private LinearLayout LinearLayoutAddress;
    private ImageView ImageViewOnBack;
    private SupportMapFragment fragment_googlemap;
    private GoogleMap googleMap;
    private List<Place.Field> fields;
    private Place place;
    private int Navigation_id;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        inituid();
        onclick();
        getdata();
    }

    private void getdata() {
        fragment_googlemap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap1) {
                googleMap = googleMap1;
                LatLng sydney = new LatLng(GETLATITUDE, GETLONGITUDE);
                googleMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .position(sydney)
                        .title("Vị trí hiện tại"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
            }
        });
    }

    private void onclick() {
        IMG_ONBACK(ImageViewOnBack, this);
        EditTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(GoogleMapActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });
        ButtonSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ButtonSave.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        ButtonSave.startAnimation(ANIMATIONDOWN);
                        ADDRESS = TextViewLocation.getText().toString().trim();
                        TEXTVIEWADDRESS.setText(ADDRESS);
                        onBackPressed();
                    }
                }
                return true;
            }
        });
    }

    private void inituid( ) {
        if (!Places.isInitialized()) {
            Places.initialize(this, "AIzaSyCuC7OMvc8ySWp7aqD2907uJVdqrkszjMk");
        }
        fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        TextViewLocation = findViewById(R.id.TextViewLocation);
        EditTextSearch = findViewById(R.id.EditTextSearch);
        ButtonSave =findViewById(R.id.ButtonSave);
        LinearLayoutAddress =findViewById(R.id.LinearLayoutAddress);
        ImageViewOnBack = findViewById(R.id.ImageViewOnBack);
        fragment_googlemap = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_googlemap);
        EditTextSearch.setFocusable(false);
        TextViewLocation.setText(LOCATION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                place = Autocomplete.getPlaceFromIntent(data);
                EditTextSearch.setText(place.getAddress());
                TextViewLocation.setText(place.getAddress());
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}