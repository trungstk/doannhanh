package com.example.project_doannhanhsol.GUI.HomePage;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.MExitTheApp;
import static com.example.project_doannhanhsol.DAO.LoveDAO.checklove;
import static com.example.project_doannhanhsol.GUI.MainActivity.chipNavigationBar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.R;


public class LoveFragment extends Fragment {
    private RecyclerView RecyclerView_Love;
    private LinearLayout LinearLayout_NoLove;
    private Button Button_addLove;
    private SwipeRefreshLayout SwipeRefreshLayoutLove;
    public LoveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_love, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intuid(view);
        onclick();
        getdata();
    }

    private void getdata() {
        checklove(getActivity(),RecyclerView_Love,LinearLayout_NoLove);
    }

    private void onclick() {
        Button_addLove.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Button_addLove.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Button_addLove.startAnimation(ANIMATIONDOWN);
                        chipNavigationBar.setItemSelected(R.id.menu_HomePage, true);
                    }
                }
                return true;
            }
        });
        SwipeRefreshLayoutLove.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdata();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefreshLayoutLove.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private void intuid(View view) {
        MainActivity mainActivity= (MainActivity) getActivity();
        RecyclerView_Love=view.findViewById(R.id.RecyclerView_Love);
        LinearLayout_NoLove=view.findViewById(R.id.LinearLayout_NoLove);
        Button_addLove=view.findViewById(R.id.Button_addLove);
        MExitTheApp(mainActivity, LoveFragment.this);
        SwipeRefreshLayoutLove=view.findViewById(R.id.SwipeRefreshLayoutLove);
    }

}