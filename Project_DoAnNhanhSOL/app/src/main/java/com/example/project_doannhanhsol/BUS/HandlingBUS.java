package com.example.project_doannhanhsol.BUS;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project_doannhanhsol.DTO.Account;
import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.R;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlingBUS {
    public static String SP_DATA = "SP_DATA";
    public static String SP_EMAIL = "SP_EMAIL";
    public static String SP_PASSWORD = "SP_PASSWORD";
    public static String TAG_ = "TAB";
    public static SharedPreferences SHAREDPREFERENCES;
    public static Dialog DIALOG;
    public static Animation ANIMATIONUP;
    public static Animation ANIMATIONDOWN;
    public static double GETLATITUDE = 0, GETLONGITUDE = 0;
    public static String LOCATION = "";
    public static String ADDRESS = "";
    public static int IDCATEGORY;
    public static int IDPRODUCT;
    public static String NAMECATEGORY;
    public static int AUTOCOMPLETE_REQUEST_CODE = 1;
    public static String ERROR_CALL_API_CATEGORY = "ERROR_CALL_API_CATEGORY";
    public static int IDACCOUNT = 0;
    public static SharedPreferences.Editor EDITOR;
    public static String ERROR_CALL_API_ACCOUNT = "ERROR_CALL_API_ACCOUNT";
    public static String TOASTSINGUPSUCCESS = "Đăng ký thành công";
    public static String TOASTREGISTRATIONFAILE = "Đăng ký thất bại";
    public static String TOASTACCOUNTEXISTS = "Tài khoản tồn tại";
    public static String ERROR_CALL_API_PRODUCT = "ERROR_CALL_API_PRODUCT";
    public static String ERROR_CALL_API_PRODUCTDETAILS = "ERROR_CALL_API_PRODUCTDETAILS";
    public static String ERROR_EMAIL_ISEMPTY = "Email không được để trống!";
    public static String ERROR_EMAIL_FORMAT = "Nhập đúng định dạng Email!";
    public static String ERROR_PASSWORD_ISEMPTY = "Mật khẩu không được để trống!";
    public static String ERROR_PASSWORD_SAMEPASSWORD = "Mật khẩu không trùng khớp!";
    public static String ERROR_PASSWORD_LENGTH = "Vui lòng nhập mật khầu có độ dài hơn 5";
    public static int Position_frame = 0;
    public static long BACKPREESRDTIME;
    public static String TEXT_TB = "Nhấn lần nữa để thoát !";
    public static String EMAIL, PASSWORD, PASSWORD_, NULL_ = "";
    public static String[] NAME;
    public static Account ACCOUNT;
    public static String TOAST_NOTIFICATION = "Vui lòng thêm đia chỉ nhà !";
    public static NumberFormat CURRENCYVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    public static TextView TEXTVIEWADDRESS;


    public static LocationRequest locationRequest;
    public static FusedLocationProviderClient fusedLocationProviderClient;

    public static void CheckLocation(Activity activity, LocationCallback locationCallback) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationSettingsRequest request = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
            SettingsClient client = LocationServices.getSettingsClient(activity);
            Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
            locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
                @SuppressLint("MissingPermission")
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
                }
            });
            locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(activity, AUTOCOMPLETE_REQUEST_CODE);
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, AUTOCOMPLETE_REQUEST_CODE);
            }

        }
    }

    public static void Notify(Activity activity, String string) {
        Notification notification = new NotificationCompat.Builder(activity, "myCH").setContentTitle("Thông báo")
                .setContentText(string).setSmallIcon(R.drawable.ic_address_location1)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(string))
                .setColor(activity.getResources().getColor(R.color.color_orange)).build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(activity);
        notificationManager.notify((int) new Date().getTime(), notification);
    }

    public static String Convertmoney(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        return number;
    }

    public static void MExitTheApp(MainActivity mainActivity, Fragment fragment) {
        mainActivity.getOnBackPressedDispatcher().addCallback(fragment, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (BACKPREESRDTIME + 2000 > System.currentTimeMillis()) {
                    mainActivity.finish();
                } else {
                    Toast.makeText(mainActivity, TEXT_TB, Toast.LENGTH_SHORT).show();
                }
                BACKPREESRDTIME = System.currentTimeMillis();
            }
        });
    }

    public static void SETDIALOG(Dialog dialog, int layout1) {
        dialog.setContentView(layout1);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams w = window.getAttributes();
        w.gravity = Gravity.CENTER;
        window.setAttributes(w);
        dialog.setCancelable(false);
        dialog.getWindow().setWindowAnimations(R.style.animtordialog);
    }

    public static void LOATDING(Dialog dialog) {
        SETDIALOG(dialog, R.layout.loading);
        dialog.show();
    }

    public static void SUCCESSFUL(Dialog dialog) {
        SETDIALOG(dialog, R.layout.successful);
        dialog.show();
    }

    public static void ANIMATION(Context context) {
        ANIMATIONUP = AnimationUtils.loadAnimation(context, R.anim.up);
        ANIMATIONDOWN = AnimationUtils.loadAnimation(context, R.anim.down);
    }

    public static void SHARED(Context context) {
        SHAREDPREFERENCES = context.getSharedPreferences(SP_DATA, MODE_PRIVATE);
    }

    public static void IMG_ONBACK(ImageView ImageView_OnBack, Activity activity) {
        ImageView_OnBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ImageView_OnBack.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        ImageView_OnBack.startAnimation(ANIMATIONDOWN);
                        activity.onBackPressed();
                    }
                }
                return true;
            }
        });
    }

}
