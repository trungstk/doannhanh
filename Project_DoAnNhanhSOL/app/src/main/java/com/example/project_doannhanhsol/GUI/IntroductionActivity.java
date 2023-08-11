package com.example.project_doannhanhsol.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_doannhanhsol.GUI.Login.LoginActivity;
import com.example.project_doannhanhsol.R;

public class IntroductionActivity extends AppCompatActivity {
    private ImageView ImageView_Logo;
    private Animation anim_ZOOM;
    private TextView TextView_Name, TextView_Solgan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        intiuid();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroductionActivity.this, LoginActivity.class));
                finish();
            }

        }, 2000);
    }
    private void intiuid( ) {
        anim_ZOOM = AnimationUtils.loadAnimation(IntroductionActivity.this, R.anim.zoom_in);
        ImageView_Logo = findViewById(R.id.ImageView_Logo);
        TextView_Name = findViewById(R.id.TextView_Name);
        TextView_Solgan = findViewById(R.id.TextView_Solgan);
        ImageView_Logo.startAnimation(anim_ZOOM);
        TextView_Name.startAnimation(anim_ZOOM);
        TextView_Solgan.startAnimation(anim_ZOOM);
    }

    @Override
    public void onBackPressed() {

    }
}