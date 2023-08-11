package com.example.project_doannhanhsol.GUI.Account;

import static com.example.project_doannhanhsol.BUS.AccountBUS.Camera;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.DIALOG;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IMG_ONBACK;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.LOATDING;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.TEXTVIEWADDRESS;
import static com.example.project_doannhanhsol.DAO.AccountDAO.GetAccount_edit;
import static com.example.project_doannhanhsol.DAO.AccountDAO.PutAccount_edit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_doannhanhsol.GUI.LocationSettings.AddLocationActivity;
import com.example.project_doannhanhsol.GUI.LocationSettings.GoogleMapActivity;
import com.example.project_doannhanhsol.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EditAccountActivity extends AppCompatActivity {
    private ImageView ImageView_Picture, ImageView_Camera, ImageView_OnBack;
    private EditText EditText_Name, EditText_NumberPhone;
    private TextView TextView_Email;
    private Button Button_Save;
    private String AccountName, AccountNumberPhone, AccountAddress;
    private Uri img_uri;
    private StorageReference reference = FirebaseStorage.getInstance().getReference().child("Image Picture");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        inituid();
        getdata();
        onclick();
    }

    private void onclick() {
        IMG_ONBACK(ImageView_OnBack, this);
        ImageView_Camera.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ImageView_Camera.startAnimation(ANIMATIONUP);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        ImageView_Camera.startAnimation(ANIMATIONDOWN);
                        Camera(EditAccountActivity.this, view);
                    }
                }
                return true;
            }
        });
        Button_Save.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Button_Save.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Button_Save.startAnimation(ANIMATIONDOWN);
                        if (!TEXTVIEWADDRESS.getText().toString().isEmpty()) {
                            AccountName = EditText_Name.getText().toString().trim();
                            AccountNumberPhone = EditText_NumberPhone.getText().toString().trim();
                            AccountAddress = TEXTVIEWADDRESS.getText().toString().trim();
                            LOATDING(DIALOG);
                            PutAccount_edit(AccountName, AccountNumberPhone, AccountAddress, EditAccountActivity.this, DIALOG, img_uri, reference);
                        }

                    }
                }
                return true;
            }
        });
        TEXTVIEWADDRESS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    TEXTVIEWADDRESS.startAnimation(ANIMATIONUP);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        TEXTVIEWADDRESS.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(EditAccountActivity.this, GoogleMapActivity.class));
                    }
                }
                return true;
            }
        });
    }

    private void getdata() {
        GetAccount_edit(EditAccountActivity.this, ImageView_Picture, EditText_Name, TextView_Email, EditText_NumberPhone, TEXTVIEWADDRESS);
        if (EditText_NumberPhone.getText().toString().isEmpty()) {
            EditText_NumberPhone.setHint("Vui lòng thêm số điện thoại!");
        }
        if (TEXTVIEWADDRESS.getText().toString().isEmpty()) {
            TEXTVIEWADDRESS.setHint("Vui lòng thêm địa chỉ!");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            img_uri = data.getData();
            ImageView_Picture.setImageURI(img_uri);
        }

    }

    private void inituid( ) {
        DIALOG = new Dialog(this);
        EditText_Name = findViewById(R.id.EditText_Name);
        EditText_NumberPhone = findViewById(R.id.EditText_NumberPhone);
        TEXTVIEWADDRESS = findViewById(R.id.TextView_Address);
        TextView_Email = findViewById(R.id.TextView_Email);
        Button_Save = findViewById(R.id.Button_Save);
        ImageView_Camera = findViewById(R.id.ImageView_Camera);
        ImageView_Picture = findViewById(R.id.ImageView_Picture);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);

    }
}