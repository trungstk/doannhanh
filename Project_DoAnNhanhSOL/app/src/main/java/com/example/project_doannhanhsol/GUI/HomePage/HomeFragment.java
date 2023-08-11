package com.example.project_doannhanhsol.GUI.HomePage;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.MExitTheApp;
import static com.example.project_doannhanhsol.DAO.CategoryDAO.getCategories;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProductDiscount;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProductPopular;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProducts;

import android.content.Intent;
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
import android.widget.TextView;

import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.GUI.Product.AllProductActivity;
import com.example.project_doannhanhsol.GUI.Product.SearchProductActivity;
import com.example.project_doannhanhsol.R;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView_DiscountFood, recyclerView_Category, recyclerView_PopularFood, recyclerView_Product;
    private TextView TextView_Search, TextView_AllProduct;
    private SwipeRefreshLayout SwipeRefreshLayoutTrangChu;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inituid(view);
        getdata();
        onclick();
    }

    private void onclick() {
        TextView_AllProduct.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TextView_AllProduct.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        TextView_AllProduct.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(getActivity(), AllProductActivity.class));
                    }
                }
                return true;
            }
        });
        TextView_Search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TextView_Search.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        TextView_Search.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(getActivity(), SearchProductActivity.class));
                    }
                }
                return true;
            }
        });
        SwipeRefreshLayoutTrangChu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdata();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefreshLayoutTrangChu.setRefreshing(false);
                    }
                },1000);
            }
        });
    }


    private void getdata() {
        getCategories(getActivity(), recyclerView_Category);
        GetProductDiscount(getActivity(), recyclerView_DiscountFood);
        GetProductPopular(getActivity(), recyclerView_PopularFood);
        GetProducts(getActivity(), recyclerView_Product);
    }

    private void inituid(View view) {
        recyclerView_DiscountFood = view.findViewById(R.id.recyclerView_DiscountFood);
        recyclerView_Category = view.findViewById(R.id.recyclerView_Category);
        recyclerView_PopularFood = view.findViewById(R.id.recyclerView_PopularFood);
        recyclerView_Product = view.findViewById(R.id.recyclerView_Product);
        TextView_Search = view.findViewById(R.id.TextView_Search);
        TextView_AllProduct = view.findViewById(R.id.TextView_AllProduct);
        SwipeRefreshLayoutTrangChu = view.findViewById(R.id.SwipeRefreshLayoutTrangChu);
        MainActivity mainActivity= (MainActivity) getActivity();
        MExitTheApp(mainActivity, HomeFragment.this);
    }
}