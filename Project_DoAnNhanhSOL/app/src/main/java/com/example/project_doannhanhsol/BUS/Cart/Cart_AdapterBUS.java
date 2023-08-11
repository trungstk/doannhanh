package com.example.project_doannhanhsol.BUS.Cart;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.Convertmoney;
import static com.example.project_doannhanhsol.DAO.CartDAO.DeleteCart;
import static com.example.project_doannhanhsol.DAO.CartDAO.PutCart;
import static com.example.project_doannhanhsol.DAO.CartDAO.PutCart1;
import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetProductDetail;
import static com.example.project_doannhanhsol.GUI.HomePage.CartFragment.getcart_GetTotalMoney;
import static com.example.project_doannhanhsol.GUI.HomePage.CartFragment.getdata;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.BUS.Product.Product_AdapterBUS;
import com.example.project_doannhanhsol.DTO.Cart;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart_AdapterBUS extends RecyclerView.Adapter<Cart_AdapterBUS.Cart_ViewHodler> {
    List<Cart> carts;
    Activity activity;
    RecyclerView recyclerView;

    public Cart_AdapterBUS(List<Cart> carts, Activity activity, RecyclerView recyclerView) {
        this.carts = carts;
        this.activity = activity;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public Cart_ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new Cart_ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_ViewHodler holder, int position) {
        Cart cart = carts.get(position);
        GetProductDetail(cart.getId_productdetails(), holder.TextView_Promotionalprice, holder.TextView_Price, holder.TextView_Size, holder.ImageView_Product, holder.TextView_NameProduct, activity);
        holder.TextView_Amount.setText(cart.getQuantity() + "");
        final int[] count = {cart.getQuantity()};
        final int[] count1 = {cart.getQuantity()};



        holder.ImageView_MinusQuantity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.ImageView_MinusQuantity.startAnimation(ANIMATIONUP);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        holder.ImageView_MinusQuantity.startAnimation(ANIMATIONDOWN);
                        count1[0] = count1[0] - 1;
                        if (count[0] != 1) {
                            count[0] = count[0] - 1;
                            count1[0] = count[0];
                            holder.TextView_Amount.setText(count[0] + "");
                            PutCart_(cart, holder,count[0]);
                        }
                        if (count1[0] <= 0) {
                            DeleteCart_(activity, cart.getId_Cart());
                        }
                    }
                }
                return true;
            }
        });
        holder.ImageView_AddAmount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.ImageView_AddAmount.startAnimation(ANIMATIONUP);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        holder.ImageView_AddAmount.startAnimation(ANIMATIONDOWN);
                        count[0] = count[0] + 1;
                        holder.TextView_Amount.setText(count[0] + "");
                        PutCart_(cart, holder,count[0]);

                    }
                }
                return true;
            }
        });
        holder.ImageView_Delete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.ImageView_Delete.startAnimation(ANIMATIONUP);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        holder.ImageView_Delete.startAnimation(ANIMATIONDOWN);
                        DeleteCart_(activity, cart.getId_Cart());
                    }
                }
                return true;
            }
        });
    }

    public void PutCart_(Cart cart, Cart_ViewHodler hodler,int count) {
        int price;
        if (hodler.TextView_Promotionalprice.getText().toString().isEmpty()) {
            price = Integer.parseInt(Convertmoney(hodler.TextView_Price.getText().toString()));
        } else {
            price = Integer.parseInt(Convertmoney(hodler.TextView_Promotionalprice.getText().toString()));
        }
        Cart cart_ = new Cart(cart.getId_Cart(),
                cart.getId_Account(), cart.getId_productdetails(),
                count,  price * count, cart.getNotes());
        PutCart1(cart.getId_Cart(), cart_);

    }

    private void DeleteCart_(Activity activity, int Idcart) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng không")
                .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteCart(Idcart);

                    }
                });

        builder.create();
        builder.show();
    }

    @Override
    public int getItemCount() {
        if (carts != null) {
            return carts.size();
        }
        return 0;
    }

    public class Cart_ViewHodler extends RecyclerView.ViewHolder {
        ImageView ImageView_Product, ImageView_Delete, ImageView_MinusQuantity, ImageView_AddAmount;
        TextView TextView_NameProduct, TextView_Size, TextView_Price, TextView_Promotionalprice, TextView_Amount;

        public Cart_ViewHodler(@NonNull View itemView) {
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
