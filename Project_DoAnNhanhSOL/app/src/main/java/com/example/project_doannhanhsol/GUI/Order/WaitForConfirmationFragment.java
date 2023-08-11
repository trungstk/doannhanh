package com.example.project_doannhanhsol.GUI.Order;

import static com.example.project_doannhanhsol.DAO.OrderDAO.Getorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.R;

public class WaitForConfirmationFragment extends Fragment {
    public static RecyclerView RecyclerView_WFconfirmation;
    public static LinearLayout LinearLayout_WFconfirmation;
    public static OrderActivity orderActivity;
    public WaitForConfirmationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wait_for_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iutuid(view);
        getdata_WCF();
    }

    public static void getdata_WCF() {
        Getorder(0,orderActivity,RecyclerView_WFconfirmation,LinearLayout_WFconfirmation);
    }

    private void iutuid(View view) {
        orderActivity= (OrderActivity) getActivity();
        RecyclerView_WFconfirmation=view.findViewById(R.id.RecyclerView_WFconfirmation);
        LinearLayout_WFconfirmation=view.findViewById(R.id.LinearLayout_WFconfirmation);
    }
}