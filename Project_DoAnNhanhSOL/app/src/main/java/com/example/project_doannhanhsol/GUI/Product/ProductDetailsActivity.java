package com.example.project_doannhanhsol.GUI.Product;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.CURRENCYVN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.DIALOG;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDACCOUNT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDPRODUCT;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IMG_ONBACK;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.SUCCESSFUL;
import static com.example.project_doannhanhsol.DAO.CartDAO.PostCart;
import static com.example.project_doannhanhsol.DAO.LoveDAO.Checklove;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProduct;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProductCategory;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProductLike;
import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.get_Size;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.project_doannhanhsol.BUS.ProductDetails.Get_Money;
import com.example.project_doannhanhsol.DTO.Cart;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.R;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ImageView ImageView_OnBack, ImageView_ProductLove, ImageView_ProductPicture, ImageView_MinusQuantity, ImageView_AddAmount;
    private TextView TextView_NameProduct, TextView_Scale, TextView_View, TextView_Price, TextView_Promotionalprice, TextView_Amount, TextView_Content;
    private RecyclerView RecyclerView_Size, RecyclerView_SimilarProduct, RecyclerView_ProductLike;
    private Button Button_addCart;
    private Get_Money get_money;
    private int Id_productdetails_;
    private int Total_money;
    private int Amount = 1;
    private List<Product> list = new ArrayList<>();
    private android.widget.ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        inituid();
        getdata();
        onclick();
    }

    private void onclick() {
        IMG_ONBACK(ImageView_OnBack, this);
        ImageView_MinusQuantity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ImageView_MinusQuantity.startAnimation(ANIMATIONUP);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        ImageView_MinusQuantity.startAnimation(ANIMATIONDOWN);
                        if (Amount != 1) {
                            Amount = Amount - 1;
                            TextView_Amount.setText(Amount + "");
                        }
                    }
                }
                return true;
            }
        });
        ImageView_AddAmount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ImageView_AddAmount.startAnimation(ANIMATIONUP);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        ImageView_AddAmount.startAnimation(ANIMATIONDOWN);
                        Amount = Amount + 1;
                        TextView_Amount.setText(Amount + "");
                    }
                }
                return true;
            }
        });
        Button_addCart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Button_addCart.startAnimation(ANIMATIONUP);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        Button_addCart.startAnimation(ANIMATIONDOWN);
                        Cart cart=new Cart(0,IDACCOUNT,Id_productdetails_,Amount,Amount*Total_money,"");
                        PostCart(cart);
                        SUCCESSFUL(DIALOG);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                DIALOG.dismiss();
                                getdata();
                            }
                        },2000);
                    }
                }
                return true;
            }
        });

    }

    private void getdata() {
        GetProductCategory(ProductDetailsActivity.this, RecyclerView_SimilarProduct, 1, IDCATEGORY, list, ProgressBar);
        GetProduct(ProductDetailsActivity.this, IDPRODUCT,
                ImageView_ProductPicture, TextView_NameProduct, TextView_Content, TextView_Scale, TextView_View);
        get_money = new Get_Money() {
            @Override
            public void Get_Money(int Id_productdetails, int Price, int Promotionalprice) {
                Id_productdetails_ = Id_productdetails;
                if (Promotionalprice == 0) {
                    TextView_Price.setText(CURRENCYVN.format(Price));
                    TextView_Promotionalprice.setVisibility(View.INVISIBLE);
                    Total_money = Price;
                } else {
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(CURRENCYVN.format(Price));
                    StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                    spannableStringBuilder.setSpan(strikethroughSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    TextView_Price.setText(spannableStringBuilder);
                    TextView_Promotionalprice.setText(CURRENCYVN.format(Promotionalprice));
                    Total_money = Promotionalprice;
                }
            }
        };

        get_Size(ProductDetailsActivity.this, RecyclerView_Size, IDPRODUCT, get_money);
        GetProductLike(ProductDetailsActivity.this, RecyclerView_ProductLike);
        Checklove(ProductDetailsActivity.this, ImageView_ProductLove);
    }

    private void inituid() {
        DIALOG=new Dialog(this);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        ImageView_ProductLove = findViewById(R.id.ImageView_ProductLove);
        ImageView_ProductPicture = findViewById(R.id.ImageView_ProductPicture);
        ImageView_MinusQuantity = findViewById(R.id.ImageView_MinusQuantity);
        ImageView_AddAmount = findViewById(R.id.ImageView_AddAmount);
        TextView_NameProduct = findViewById(R.id.TextView_NameProduct);
        TextView_Scale = findViewById(R.id.TextView_Scale);
        TextView_View = findViewById(R.id.TextView_View);
        TextView_Price = findViewById(R.id.TextView_Price);
        TextView_Promotionalprice = findViewById(R.id.TextView_Promotionalprice);
        TextView_Amount = findViewById(R.id.TextView_Amount);
        TextView_Content = findViewById(R.id.TextView_Content);
        RecyclerView_Size = findViewById(R.id.RecyclerView_Size);
        RecyclerView_SimilarProduct = findViewById(R.id.RecyclerView_SimilarProduct);
        RecyclerView_ProductLike = findViewById(R.id.RecyclerView_ProductLike);
        Button_addCart = findViewById(R.id.Button_addCart);
        ProgressBar = findViewById(R.id.ProgressBar);

    }
}