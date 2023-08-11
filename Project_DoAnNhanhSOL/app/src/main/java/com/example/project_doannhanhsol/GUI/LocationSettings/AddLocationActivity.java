package com.example.project_doannhanhsol.GUI.LocationSettings;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ADDRESS;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATION;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.DIALOG;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IMG_ONBACK;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.LOATDING;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.LOCATION;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.SHARED;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.TEXTVIEWADDRESS;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.TOAST_NOTIFICATION;
import static com.example.project_doannhanhsol.DAO.AccountDAO.PutAccount_Location;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.R;

public class AddLocationActivity extends AppCompatActivity {
    private TextView TextViewCurrentPosition, TextViewAddLocationHome;
    private Button ButtonSaveLocation;
    private ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        inituid();
        onclick();
    }
    private void onclick() {
        IMG_ONBACK(ImageView_OnBack, this);
        TextViewAddLocationHome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TextViewAddLocationHome.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        TextViewAddLocationHome.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(AddLocationActivity.this,GoogleMapActivity.class));
                    }
                }
                return true;
            }
        });
        ButtonSaveLocation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ButtonSaveLocation.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        ButtonSaveLocation.startAnimation(ANIMATIONDOWN);
                        if(!ADDRESS.isEmpty()){
                            LOATDING(DIALOG);
                            PutAccount_Location(ADDRESS);
                            startActivity(new Intent(AddLocationActivity.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(AddLocationActivity.this, TOAST_NOTIFICATION, Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                return true;
            }
        });
    }
    private void inituid( ) {
        ANIMATION(this);
        SHARED(this);
        DIALOG = new Dialog(this);
        TextViewCurrentPosition = findViewById(R.id.TextViewCurrentPosition);
        TEXTVIEWADDRESS = findViewById(R.id.TextViewAddress);
        TextViewAddLocationHome = findViewById(R.id.TextViewAddLocationHome);
        ButtonSaveLocation = findViewById(R.id.ButtonSaveLocation);
        ImageView_OnBack = findViewById(R.id.ImageViewOnBack);
        TextViewCurrentPosition.setText(LOCATION);
        TEXTVIEWADDRESS.setText(ADDRESS);
    }
}