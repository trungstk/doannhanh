package com.example.project_doannhanhsol.GUI.HomePage;

import static com.example.project_doannhanhsol.DAO.NotifyDAO.GetNotify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_doannhanhsol.R;


public class NotifyFragment extends Fragment {
    private RecyclerView RecyclerView_Notify;
    public NotifyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notify, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView_Notify=view.findViewById(R.id.RecyclerView_Notify);
        GetNotify(getActivity(),RecyclerView_Notify);
    }
}