package com.example.project_doannhanhsol.GUI.Product;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.IMG_ONBACK;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProductsPage;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetSearchProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.R;

import java.util.ArrayList;
import java.util.List;

public class AllProductActivity extends AppCompatActivity {
    private ImageView ImageView_OnBack;
    private EditText EditText_SearchProduct;
    private androidx.core.widget.NestedScrollView NestedScrollView;
    private RecyclerView RecyclerView_Product;
    private android.widget.ProgressBar ProgressBar;
    private TextView TextView_ERRORSearch;
    private List<Product> products=new ArrayList<>();
    private int PageNumber=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);
        inituid();
        getdata();
        onclick();
    }
    private void onclick() {
        IMG_ONBACK(ImageView_OnBack, this);
        NestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    ProgressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PageNumber++;
                            getdata();
                        }
                    },2000);

                }
            }
        });
        EditText_SearchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (EditText_SearchProduct.getText().toString().isEmpty()){
                    getdata();
                }else{
                    GetSearchProduct(AllProductActivity.this,RecyclerView_Product,EditText_SearchProduct.getText().toString().trim(),TextView_ERRORSearch);
                }
            }
        });
    }

    private void getdata() {
        GetProductsPage(AllProductActivity.this,RecyclerView_Product,PageNumber,products,ProgressBar);
    }

    private void inituid( ) {
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        EditText_SearchProduct=findViewById(R.id.EditText_SearchProduct);
        NestedScrollView=findViewById(R.id.NestedScrollView);
        RecyclerView_Product=findViewById(R.id.RecyclerView_Product);
        ProgressBar=findViewById(R.id.ProgressBar);
        TextView_ERRORSearch=findViewById(R.id.TextView_ERRORSearch);
    }
}