package com.example.project_doannhanhsol.BUS;

import android.app.Activity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.example.project_doannhanhsol.DTO.Account;
import com.example.project_doannhanhsol.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class AccountBUS {
    public static boolean CHECKSPACCOUNT(String Email, String Password) {
        if (Email.isEmpty() && Password.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean CHECKISEMPTY(EditText editText, String ErrorMessage) {
        String string = editText.getText().toString().trim();
        if (string.isEmpty()) {
            editText.setError(ErrorMessage);
            return true;
        }
        return false;
    }

    public static boolean CHECKFORMATEMAIL(EditText editText, String ErrorMessage) {
        String email = editText.getText().toString().trim();
        if (!email.contains("@gmail.com")) {
            editText.setError(ErrorMessage);
            return true;
        }
        return false;
    }

    public static boolean CHECK_PASSWORDLENGTH(EditText Password, String ErrorMessage_length) {
        String password = Password.getText().toString().trim();
        if (password.length() < 6) {
            Password.setError(ErrorMessage_length);
            return true;
        }
        return false;
    }

    public static boolean CHECK_SAMEPASSWORD(EditText Password, EditText Password_, String ErrorMessage_SAMEPASSWORD) {
        String password = Password.getText().toString().trim();
        String password_ = Password_.getText().toString().trim();
        if (!password_.equals(password)) {
            Password_.setError(ErrorMessage_SAMEPASSWORD);
            return true;
        }
        return false;
    }

    public static boolean CheckSPAccount(String Email, String Password) {
        if (Email.isEmpty() && Password.isEmpty()) {
            return false;
        }
        return true;
    }
    public static void Camera(Activity activity, View view){
        PopupMenu popupMenu = new PopupMenu(activity, view);
        popupMenu.inflate(R.menu.menu_camera);
        popupMenu.setGravity(Gravity.CENTER);
        popupMenu.setForceShowIcon(true);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_camera:
                        ImagePicker.with(activity)
                                .crop(16f, 16f)
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .cameraOnly()
                                .start();
                        return true;
                    case R.id.menu_chon_anh:
                        ImagePicker.with(activity)
                                .crop(16f, 16f)
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .galleryOnly()
                                .start();
                        return true;

                }
                return false;
            }
        });
        popupMenu.show();
    }
    public static Account AddAccount(int id,String Name,String Email,String Password,String NumberPhone,String Address,String JoinDate,String Picture){
        Account account=new Account(id,Name,Email,Password,NumberPhone,Address,JoinDate,Picture);
        return account;
    }
}
