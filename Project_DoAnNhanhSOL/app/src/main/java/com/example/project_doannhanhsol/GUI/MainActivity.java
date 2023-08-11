package com.example.project_doannhanhsol.GUI;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.AUTOCOMPLETE_REQUEST_CODE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.BACKPREESRDTIME;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.CheckLocation;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.GETLATITUDE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.GETLONGITUDE;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDACCOUNT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.LOCATION;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.TAG_;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.TEXT_TB;
import static com.example.project_doannhanhsol.DAO.CartDAO.GetCartAmount;
import static com.example.project_doannhanhsol.DAO.NotifyDAO.GetNotifies;
import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetDetailNotify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.Navigation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Notify;
import com.example.project_doannhanhsol.GUI.LocationSettings.LocationSettingsActivity;
import com.example.project_doannhanhsol.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static ChipNavigationBar chipNavigationBar;
    public Handler handler = new Handler();
    public Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        chipNavigationBar = findViewById(R.id.menu);
        chipNavigationBar.setItemSelected(R.id.menu_HomePage, true);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.menu_HomePage:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.trangChuFragment);
                        break;
                    case R.id.menu_Notification:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.notifyFragment);
                        break;
                    case R.id.menu_Love:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.loveFragment);
                        break;
                    case R.id.menu_Cart:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.cartFragment);
                        break;
                    case R.id.menu_Account:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.accountFragment);
                        break;
                }
            }
        });
        CheckLocation(this, locationCallback);
        GetCartAmount();
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("myCH", "My Name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My Name");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                GetCartAmount();
                GetNotifies(MainActivity.this);
                handler.postDelayed(runnable, 1000);
            }
        }, 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            for (Location location : locationResult.getLocations()) {
                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
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

}