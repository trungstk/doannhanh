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

import com.example.project_doannhanhsol.R;


public class CancelledFragment extends Fragment {
    private static RecyclerView RecyclerView_Cancelled;
    private static LinearLayout LinearLayout_Cancelled;
    private static OrderActivity orderActivity;
    public CancelledFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cancelled, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iutuid(view);
        getdata_CF();
    }

    public static void getdata_CF() {
        Getorder(3,orderActivity,RecyclerView_Cancelled,LinearLayout_Cancelled);
    }

    private void iutuid(View view) {
        orderActivity= (OrderActivity) getActivity();
        RecyclerView_Cancelled=view.findViewById(R.id.RecyclerView_Cancelled);
        LinearLayout_Cancelled=view.findViewById(R.id.LinearLayout_Cancelled);
    }
}