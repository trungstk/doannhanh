package com.example.project_doannhanhsol.BUS.Love;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDPRODUCT;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProduct;
import static com.example.project_doannhanhsol.DAO.ProductDAO.GetProduct1;
import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetProductDetails;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.project_doannhanhsol.BUS.Product.Product_AdapterBUS;
import com.example.project_doannhanhsol.DTO.Love;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.GUI.Product.ProductDetailsActivity;
import com.example.project_doannhanhsol.R;

import java.util.List;

public class LoveAdapterBUS extends RecyclerView.Adapter<LoveAdapterBUS.LoveViewHolder> {
    List<Love> loves;
    Activity activity;

    public LoveAdapterBUS(List<Love> loves, Activity activity) {
        this.loves = loves;
        this.activity = activity;
    }

    @NonNull
    @Override
    public LoveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new LoveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoveViewHolder holder, int position) {
        Love love=loves.get(position);
        GetProduct1(activity,love.getId_product(), holder.ImageView_Product, holder.TextView_NameProduct, holder.TextView_Content, holder.TextView_Scale, holder.TextView_View,holder.TextView_IDCategory);
        GetProductDetails(love.getId_product(), holder.TextView_Price, holder.TextView_Promotionalprice);

        holder.LinearLayout_Product.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.LinearLayout_Product.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        holder.LinearLayout_Product.startAnimation(ANIMATIONDOWN);
                        IDPRODUCT = love.getId_product();
                        IDCATEGORY= Integer.parseInt(holder.TextView_IDCategory.getText().toString());
                        activity.startActivity(new Intent(activity, ProductDetailsActivity.class));
                    }
                }
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        if (loves != null) {
            return loves.size();
        }
        return 0;
    }

    public class LoveViewHolder extends RecyclerView.ViewHolder {
        LinearLayout LinearLayout_Product;
        ImageView ImageView_Product;
        TextView TextView_NameProduct, TextView_Content, TextView_Price, TextView_Promotionalprice, TextView_Scale, TextView_View,TextView_IDCategory;

        public LoveViewHolder(@NonNull View itemView) {
            super(itemView);
            LinearLayout_Product = itemView.findViewById(R.id.LinearLayout_Product);
            ImageView_Product = itemView.findViewById(R.id.ImageView_Product);
            TextView_NameProduct = itemView.findViewById(R.id.TextView_NameProduct);
            TextView_Content = itemView.findViewById(R.id.TextView_Content);
            TextView_Price = itemView.findViewById(R.id.TextView_Price);
            TextView_Promotionalprice = itemView.findViewById(R.id.TextView_Promotionalprice);
            TextView_Scale = itemView.findViewById(R.id.TextView_Scale);
            TextView_View = itemView.findViewById(R.id.TextView_View);
            TextView_IDCategory = itemView.findViewById(R.id.TextView_IDCategory);
        }
    }
}
