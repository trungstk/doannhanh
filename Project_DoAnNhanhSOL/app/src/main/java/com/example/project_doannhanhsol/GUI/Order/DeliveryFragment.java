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


public class DeliveryFragment extends Fragment {

    private RecyclerView RecyclerView_Delivery;
    private LinearLayout LinearLayout_Delivery;

    public DeliveryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iutuid(view);
        getdata();
    }

    private void getdata() {
        Getorder(1,getActivity(),RecyclerView_Delivery,LinearLayout_Delivery);
    }

    private void iutuid(View view) {
        RecyclerView_Delivery=view.findViewById(R.id.RecyclerView_Delivery);
        LinearLayout_Delivery=view.findViewById(R.id.LinearLayout_Delivery);
    }
}