package com.example.project_doannhanhsol.GUI.Cart;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IMG_ONBACK;
import static com.example.project_doannhanhsol.DAO.AccountDAO.GetAccount_Order;
import static com.example.project_doannhanhsol.DAO.CartDAO.GetCartorder;
import static com.example.project_doannhanhsol.DAO.CartDAO.GetTotalMoney;
import static com.example.project_doannhanhsol.DAO.OrderDAO.Postorder_;
import static com.example.project_doannhanhsol.GUI.HomePage.CartFragment.getcart_GetTotalMoney;
import static com.example.project_doannhanhsol.GUI.HomePage.CartFragment.getdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_doannhanhsol.GUI.Account.EditAccountActivity;
import com.example.project_doannhanhsol.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CheckoutCartActivity extends AppCompatActivity {
    private ImageView ImageView_OnBack;
    private TextView TextView_Amount, TextView_Name, TextView_Number, TextView_Address, TextView_Edit, TextView_PaymentMethod, TextView_elect, TextView_TotalMoney;
    private RecyclerView RecyclerView_Cart;
    private EditText EditText_Message;
    private Button Button_Pay;
    private BottomSheetDialog bottomSheetDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_cart);
        intuid();
        getdata_();
        onclick();
    }

    private void getdata_() {
        GetAccount_Order(TextView_Name, TextView_Number, TextView_Address);
        GetCartorder(RecyclerView_Cart, CheckoutCartActivity.this, TextView_Amount);
        GetTotalMoney(TextView_TotalMoney);

    }

    private void onclick() {
        IMG_ONBACK(ImageView_OnBack, this);
        TextView_elect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.setContentView(R.layout.bottom_thanhtoan);
                CheckBox checkBox1 = bottomSheetDialog.findViewById(R.id.checkBox1);
                CheckBox checkBox2 = bottomSheetDialog.findViewById(R.id.checkBox2);
                if (checkBox2.getText().toString().equals(TextView_PaymentMethod.getText().toString())) {
                    checkBox2.setChecked(true);
                } else {
                    checkBox1.setChecked(true);
                }
                checkBox2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBox1.setChecked(false);
                        TextView_PaymentMethod.setText(checkBox2.getText().toString());
                    }
                });
                checkBox1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBox2.setChecked(false);
                        TextView_PaymentMethod.setText(checkBox1.getText().toString());
                    }
                });
                bottomSheetDialog.show();

            }
        });
        TextView_Edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TextView_Edit.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        TextView_Edit.startAnimation(ANIMATIONDOWN);
                        startActivity(new Intent(CheckoutCartActivity.this, EditAccountActivity.class));
                    }
                }
                return true;
            }
        });
        Button_Pay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Button_Pay.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Button_Pay.startAnimation(ANIMATIONDOWN);
                        Postorder_(EditText_Message,TextView_PaymentMethod,CheckoutCartActivity.this);
                    }
                }
                return true;
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdata_();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

    }

    private void intuid() {
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        TextView_Amount = findViewById(R.id.TextView_Amount);
        TextView_Name = findViewById(R.id.TextView_Name);
        TextView_Number = findViewById(R.id.TextView_Number);
        TextView_Address = findViewById(R.id.TextView_Address);
        TextView_Edit = findViewById(R.id.TextView_Edit);
        TextView_PaymentMethod = findViewById(R.id.TextView_PaymentMethod);
        TextView_elect = findViewById(R.id.TextView_elect);
        TextView_TotalMoney = findViewById(R.id.TextView_TotalMoney);
        RecyclerView_Cart = findViewById(R.id.RecyclerView_Cart);
        EditText_Message = findViewById(R.id.EditText_Message);
        Button_Pay = findViewById(R.id.Button_Pay);
        swipeRefreshLayout = findViewById(R.id.SwipeRef);
        bottomSheetDialog = new BottomSheetDialog(this);
    }
}