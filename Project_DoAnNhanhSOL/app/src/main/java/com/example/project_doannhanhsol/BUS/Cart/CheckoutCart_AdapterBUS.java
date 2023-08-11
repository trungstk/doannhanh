package com.example.project_doannhanhsol.BUS.Cart;

import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetProductDetail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.DTO.Cart;
import com.example.project_doannhanhsol.R;

import java.util.List;

public class CheckoutCart_AdapterBUS extends RecyclerView.Adapter<CheckoutCart_AdapterBUS.Order_ViewHodler> {
    List<Cart> carts;
    Activity activity;

    public CheckoutCart_AdapterBUS(List<Cart> carts, Activity activity) {
        this.carts = carts;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Order_ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkoutcart, parent, false);
        return new Order_ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order_ViewHodler holder, int position) {
        Cart cart = carts.get(position);
        GetProductDetail(cart.getId_productdetails(), holder.TextView_Promotionalprice, holder.TextView_Price, holder.TextView_Size, holder.ImageView_Product, holder.TextView_NameProduct, activity);
        holder.TextView_Amount.setText("Số lượng: "+cart.getQuantity());
    }
    @Override
    public int getItemCount() {
        if (carts != null) {
            return carts.size();
        }
        return 0;
    }

    public class Order_ViewHodler extends RecyclerView.ViewHolder {
        ImageView ImageView_Product, ImageView_Delete, ImageView_MinusQuantity, ImageView_AddAmount;
        TextView TextView_NameProduct, TextView_Size, TextView_Price, TextView_Promotionalprice, TextView_Amount;

        public Order_ViewHodler(@NonNull View itemView) {
            super(itemView);
            ImageView_Product = itemView.findViewById(R.id.ImageView_Product);
            ImageView_Delete = itemView.findViewById(R.id.ImageView_Delete);
            ImageView_MinusQuantity = itemView.findViewById(R.id.ImageView_MinusQuantity);
            ImageView_AddAmount = itemView.findViewById(R.id.ImageView_AddAmount);
            TextView_NameProduct = itemView.findViewById(R.id.TextView_NameProduct);
            TextView_Size = itemView.findViewById(R.id.TextView_Size);
            TextView_Price = itemView.findViewById(R.id.TextView_Price);
            TextView_Promotionalprice = itemView.findViewById(R.id.TextView_Promotionalprice);
            TextView_Amount = itemView.findViewById(R.id.TextView_Amount);
        }
    }
}
