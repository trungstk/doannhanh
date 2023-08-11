package com.example.project_doannhanhsol.GUI.Product;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IMG_ONBACK;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetSearchProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_doannhanhsol.R;

public class SearchProductActivity extends AppCompatActivity {
    private ImageView ImageView_OnBack;
    private EditText EditText_SearchProduct;
    private RecyclerView RecyclerView_SearchProduct;
    private TextView TextView_ERRORSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        inituid();
        onclick();
    }
    private void onclick() {
        IMG_ONBACK(ImageView_OnBack, this);
        EditText_SearchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                GetSearchProduct(SearchProductActivity.this, RecyclerView_SearchProduct, EditText_SearchProduct.getText().toString().trim(), TextView_ERRORSearch);
            }
        });
    }

    private void inituid( ) {
        ImageView_OnBack =findViewById(R.id.ImageView_OnBack);
        EditText_SearchProduct = findViewById(R.id.EditText_SearchProduct);
        RecyclerView_SearchProduct = findViewById(R.id.RecyclerView_SearchProduct);
        TextView_ERRORSearch = findViewById(R.id.TextView_ERRORSearch);

    }
}