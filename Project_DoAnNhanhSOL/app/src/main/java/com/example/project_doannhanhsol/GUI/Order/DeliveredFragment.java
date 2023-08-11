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


public class DeliveredFragment extends Fragment {

    private RecyclerView RecyclerView_Delivered;
    private LinearLayout LinearLayout_Delivered;
    public DeliveredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivered, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iutuid(view);
        getdata();
    }

    private void getdata() {
        Getorder(2,getActivity(),RecyclerView_Delivered,LinearLayout_Delivered);
    }

    private void iutuid(View view) {
        RecyclerView_Delivered=view.findViewById(R.id.RecyclerView_Delivered);
        LinearLayout_Delivered=view.findViewById(R.id.LinearLayout_Delivered);
    }
}