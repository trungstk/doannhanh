package com.example.project_doannhanhsol.GUI.Login;

import static com.example.project_doannhanhsol.BUS.AccountBUS.CHECKFORMATEMAIL;
import static com.example.project_doannhanhsol.BUS.AccountBUS.CHECKISEMPTY;
import static com.example.project_doannhanhsol.BUS.AccountBUS.CHECKSPACCOUNT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.*;
import static com.example.project_doannhanhsol.DAO.AccountDAO.LOGINACCOUNT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_doannhanhsol.R;

public class LoginActivity extends AppCompatActivity {
    private EditText EditTextEmail, EditTextPassword;
    private Button ButtonLogin;
    private TextView TextViewRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intiuid();
        onclick();
    }
    private void onclick() {
        ButtonLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ButtonLogin.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        ButtonLogin.startAnimation(ANIMATIONDOWN);
                        Login_();
                    }
                }
                return true;
            }
        });
        TextViewRegistration.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TextViewRegistration.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        TextViewRegistration.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                    }
                }
                return true;
            }
        });
    }
    private void Login_() {
        if (CHECKISEMPTY(EditTextEmail, ERROR_PASSWORD_ISEMPTY) || CHECKISEMPTY(EditTextPassword, ERROR_PASSWORD_ISEMPTY)
                || CHECKFORMATEMAIL(EditTextEmail, ERROR_EMAIL_FORMAT)
        ) {
            return;
        }
        EMAIL=EditTextEmail.getText().toString().trim();
        PASSWORD=EditTextPassword.getText().toString().trim();
        LOATDING(DIALOG);
        LOGINACCOUNT(EMAIL, PASSWORD, this, DIALOG);
    }
    private void intiuid( ) {
        ANIMATION(this);
        SHARED(this);
        DIALOG=new Dialog(this);
        EditTextEmail = findViewById(R.id.EditTextEmail);
        EditTextPassword = findViewById(R.id.EditTextPassword);
        ButtonLogin = findViewById(R.id.ButtonLogin);
        TextViewRegistration = findViewById(R.id.TextViewRegistration);
        EMAIL = SHAREDPREFERENCES.getString(SP_EMAIL, "");
        PASSWORD = SHAREDPREFERENCES.getString(SP_PASSWORD, "");
        if (CHECKSPACCOUNT(EMAIL, PASSWORD)) {
            LOATDING(DIALOG);
            LOGINACCOUNT(EMAIL, PASSWORD, this, DIALOG);
        }
    }

    @Override
    public void onBackPressed() {
        if (BACKPREESRDTIME + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, TEXT_TB, Toast.LENGTH_SHORT).show();
        }
        BACKPREESRDTIME = System.currentTimeMillis();
    }
}