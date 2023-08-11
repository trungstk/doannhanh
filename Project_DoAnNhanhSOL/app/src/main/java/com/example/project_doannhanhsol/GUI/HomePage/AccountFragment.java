package com.example.project_doannhanhsol.GUI.HomePage;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.MExitTheApp;
import static com.example.project_doannhanhsol.DAO.AccountDAO.GetAccount;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_doannhanhsol.GUI.Account.EditAccountActivity;
import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.GUI.Order.OrderActivity;
import com.example.project_doannhanhsol.R;


public class AccountFragment extends Fragment {

    private ImageView ImageView_Picture, ImageView_EditAccount;
    private TextView TextView_Name, TextView_Email, TextView_Spport, TextView_SendFeedback, TextView_Logout,TextView_Order;
    private SwipeRefreshLayout SwipeRefreshLayoutAccount;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inituid(view);
        getdata();
        onclick();
    }

    private void onclick() {
        ImageView_EditAccount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ImageView_EditAccount.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        ImageView_EditAccount.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(getActivity(), EditAccountActivity.class));
                    }
                }
                return true;
            }
        });
        TextView_Order.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TextView_Order.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        TextView_Order.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(getActivity(), OrderActivity.class));
                    }
                }
                return true;
            }
        });
        SwipeRefreshLayoutAccount.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdata();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefreshLayoutAccount.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void getdata() {
        GetAccount(AccountFragment.this, ImageView_Picture, TextView_Name, TextView_Email);
    }

    private void inituid(View view) {
        ImageView_Picture = view.findViewById(R.id.ImageView_Picture);
        TextView_Name = view.findViewById(R.id.TextView_Name);
        TextView_Email = view.findViewById(R.id.TextView_Email);
        TextView_Spport = view.findViewById(R.id.TextView_Spport);
        ImageView_EditAccount = view.findViewById(R.id.ImageView_EditAccount);
        TextView_SendFeedback = view.findViewById(R.id.TextView_SendFeedback);
        TextView_Logout = view.findViewById(R.id.TextView_Logout);
        TextView_Order = view.findViewById(R.id.TextView_Order);
        SwipeRefreshLayoutAccount = view.findViewById(R.id.SwipeRefreshLayoutAccount);
        MainActivity mainActivity = (MainActivity) getActivity();
        MExitTheApp(mainActivity, AccountFragment.this);
    }
}