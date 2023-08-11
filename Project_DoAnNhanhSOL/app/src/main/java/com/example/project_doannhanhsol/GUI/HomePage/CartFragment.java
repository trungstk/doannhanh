package com.example.project_doannhanhsol.GUI.HomePage;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.MExitTheApp;
import static com.example.project_doannhanhsol.DAO.CartDAO.GetCartAccpunt;
import static com.example.project_doannhanhsol.DAO.CartDAO.GetTotalMoney;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProductCategory;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProductLike;
import static com.example.project_doannhanhsol.GUI.MainActivity.chipNavigationBar;

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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_doannhanhsol.GUI.Cart.CheckoutCartActivity;
import com.example.project_doannhanhsol.GUI.Login.LoginActivity;
import com.example.project_doannhanhsol.GUI.Login.RegistrationActivity;
import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.GUI.Product.ProductDetailsActivity;
import com.example.project_doannhanhsol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {
    public static TextView TextView_Amount;
    public static TextView TextView_TotalMoney;
    public static LinearLayout LinearLayout_BuyProduct, LinearLayout_Cart;
    private Button Button_BuyProduct, Button_Order;
    private static RecyclerView RecyclerView_Cart;
    private RecyclerView RecyclerView_ProductLike;
    public static MainActivity mainActivity;
    private SwipeRefreshLayout SwipeRefreshLayoutCart;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inituid(view);
        getdata();
        getcart_GetTotalMoney();
        onclick();
    }

    public static void getdata() {
        GetCartAccpunt(RecyclerView_Cart, mainActivity, LinearLayout_BuyProduct, LinearLayout_Cart, TextView_Amount);
    }

    public static void getcart_GetTotalMoney() {
        GetTotalMoney(TextView_TotalMoney);
    }

    private void onclick() {
        Button_BuyProduct.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Button_BuyProduct.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Button_BuyProduct.startAnimation(ANIMATIONDOWN);
                        chipNavigationBar.setItemSelected(R.id.menu_HomePage, true);
                    }
                }
                return true;
            }
        });
        Button_Order.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Button_Order.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Button_Order.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(getActivity(), CheckoutCartActivity.class));
                    }
                }
                return true;
            }
        });
        SwipeRefreshLayoutCart.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdata();
                getcart_GetTotalMoney();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefreshLayoutCart.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private void inituid(View view) {
        mainActivity = (MainActivity) getActivity();
        TextView_Amount = view.findViewById(R.id.TextView_Amount);
        TextView_TotalMoney = view.findViewById(R.id.TextView_TotalMoney);
        LinearLayout_BuyProduct = view.findViewById(R.id.LinearLayout_BuyProduct);
        LinearLayout_Cart = view.findViewById(R.id.LinearLayout_Cart);
        Button_BuyProduct = view.findViewById(R.id.Button_BuyProduct);
        Button_Order = view.findViewById(R.id.Button_Order);
        RecyclerView_Cart = view.findViewById(R.id.RecyclerView_Cart);
        SwipeRefreshLayoutCart = view.findViewById(R.id.SwipeRefreshLayoutCart);
        RecyclerView_ProductLike = view.findViewById(R.id.RecyclerView_ProductLike);
        GetProductLike(getActivity(), RecyclerView_ProductLike);
        MExitTheApp(mainActivity, CartFragment.this);
    }


}