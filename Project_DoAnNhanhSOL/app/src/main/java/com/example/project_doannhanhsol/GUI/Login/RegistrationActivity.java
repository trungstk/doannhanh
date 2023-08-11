
package com.example.project_doannhanhsol.GUI.Login;

import static com.example.project_doannhanhsol.BUS.AccountBUS.CHECKFORMATEMAIL;
import static com.example.project_doannhanhsol.BUS.AccountBUS.CHECKISEMPTY;
import static com.example.project_doannhanhsol.BUS.AccountBUS.CHECK_PASSWORDLENGTH;
import static com.example.project_doannhanhsol.BUS.AccountBUS.CHECK_SAMEPASSWORD;
import static com.example.project_doannhanhsol.DAO.AccountDAO.REGISTRATIONACCOUNT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_doannhanhsol.DTO.Account;
import com.example.project_doannhanhsol.R;

public class RegistrationActivity extends AppCompatActivity {
    private EditText EditTextEmail, EditTextPassword, EditTextPassword_1;
    private Button Button_Registration;
    private TextView TextViewLogin;
    private ImageView ImageView_OnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        intiuid();
        onClick();
    }

    public void OnBack(EditText editText, Activity context) {
        context.onBackPressed();
    }

    private void onClick() {
        IMG_ONBACK(ImageView_OnBack, this);
        TextViewLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TextViewLogin.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        TextViewLogin.startAnimation(ANIMATIONDOWN);

                    }
                }
                return true;
            }
        });
        Button_Registration.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Button_Registration.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Button_Registration.startAnimation(ANIMATIONDOWN);
                        Registrations();
                    }
                }
                return true;
            }
        });

    }

    private void Registrations() {
        EMAIL = EditTextEmail.getText().toString().trim();
        PASSWORD = EditTextPassword.getText().toString().trim();
        PASSWORD_ = EditTextPassword_1.getText().toString().trim();
        if (CHECKISEMPTY(EditTextEmail, ERROR_PASSWORD_ISEMPTY) || CHECKISEMPTY(EditTextPassword, ERROR_PASSWORD_ISEMPTY) || CHECKISEMPTY(EditTextPassword_1, ERROR_PASSWORD_ISEMPTY)
                || CHECKFORMATEMAIL(EditTextEmail, ERROR_EMAIL_FORMAT) || CHECK_PASSWORDLENGTH(EditTextPassword, ERROR_PASSWORD_LENGTH)
                || CHECK_PASSWORDLENGTH(EditTextPassword_1, ERROR_PASSWORD_LENGTH) || CHECK_SAMEPASSWORD(EditTextPassword, EditTextPassword_1, ERROR_PASSWORD_SAMEPASSWORD)
        ) {
            return;
        }

        LOATDING(DIALOG);
        NAME = EMAIL.split("@");
        ACCOUNT = new Account(0, NAME[0], EMAIL, PASSWORD, NULL_, NULL_, NULL_, NULL_);
        REGISTRATIONACCOUNT(ACCOUNT, this, DIALOG);
    }

    private void intiuid() {
        ANIMATION(this);
        DIALOG = new Dialog(this);
        EditTextEmail = findViewById(R.id.EditTextEmail);
        EditTextPassword = findViewById(R.id.EditTextPassword);
        EditTextPassword_1 = findViewById(R.id.EditTextPassword_1);
        Button_Registration = findViewById(R.id.Button_Registration);
        TextViewLogin = findViewById(R.id.TextViewLogin);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
    }
}