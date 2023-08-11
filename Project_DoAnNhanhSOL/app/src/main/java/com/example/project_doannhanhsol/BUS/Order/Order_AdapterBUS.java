package com.example.project_doannhanhsol.BUS.Order;

import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONDOWN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.ANIMATIONUP;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.CURRENCYVN;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDCATEGORY;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.IDPRODUCT;
import static com.example.project_doannhanhsol.DAO.CartDAO.DeleteCart;
import static com.example.project_doannhanhsol.DAO.NotifyDAO.PutNotify;
import static com.example.project_doannhanhsol.DAO.OrderDAO.BuyBackProducts;
import static com.example.project_doannhanhsol.DAO.OrderDAO.Putorder_;
import static com.example.project_doannhanhsol.DAO.ProductDetailsDAO.GetProductDetail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Order;
import com.example.project_doannhanhsol.DTO.Product;
import com.example.project_doannhanhsol.DTO.ProductDetails;
import com.example.project_doannhanhsol.GUI.Product.ProductDetailsActivity;
import com.example.project_doannhanhsol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_AdapterBUS extends RecyclerView.Adapter<Order_AdapterBUS.Order_ViewHolder> {
    List<Order> orders;
    Activity activity;
    public Order_AdapterBUS(List<Order> orders, Activity activity) {
        this.orders = orders;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Order_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new Order_ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull Order_ViewHolder holder, int position) {
        Order order = orders.get(position);
        GetProductDetail(order.getId_productdetails(), holder.TextView_Promotionalprice, holder.TextView_Price, holder.TextView_Size, holder.ImageView_Product, holder.TextView_NameProduct, activity);
        holder.TextView_Amount.setText("Số lượng: " + order.getQuantity());
        holder.TextView_TotalMoney.setText("Tổng tiền: " + CURRENCYVN.format(order.getTotalMoney()));
        holder.TextView_Datetime.setText(order.getDateTime());
        switch (order.getStatus()) {
            case 0:
                holder.Button_CancelOrder.setText("Hủy đơn hàng");
                CanelOrder(order,holder,3);
                break;
            case 1:
                holder.Button_CancelOrder.setVisibility(View.GONE);
                break;
            case 2:
                holder.Button_CancelOrder.setText("Mua lại sản phẩm");
                BuyBackProduct(holder,order);
                break;
            case 3:
                holder.Button_CancelOrder.setText("Mua lại sản phẩm");
                BuyBackProduct(holder,order);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void CanelOrder(Order order, Order_ViewHolder holder,int status) {
        Order order1=new Order(order.getId_Order(),order.getId_Account(),order.getId_productdetails(),order.getQuantity(),order.getTotalMoney(),status,order.getMessage(),order.getPaymentMethod(),java.time.LocalDateTime.now() + "",order.getNotes());
        holder.Button_CancelOrder.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.Button_CancelOrder.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        holder.Button_CancelOrder.startAnimation(ANIMATIONDOWN);
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("Bạn có muốn hủy đơn này không")
                                .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                })
                                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Putorder_(order.getId_Order(),order1);
                                    }

                                });

                        builder.create();
                        builder.show();
                    }
                }
                return true;
            }
        });
    }
    public void BuyBackProduct(Order_ViewHolder holder,Order order){
        holder.Button_CancelOrder.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.Button_CancelOrder.startAnimation(ANIMATIONUP);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        holder.Button_CancelOrder.startAnimation(ANIMATIONDOWN);
                        BuyBackProducts(order,activity);
                    }
                }
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        if (orders != null) {
            return orders.size();
        }
        return 0;
    }

    public class Order_ViewHolder extends RecyclerView.ViewHolder {
        ImageView ImageView_Product;
        TextView TextView_NameProduct, TextView_Size, TextView_Price, TextView_Promotionalprice, TextView_Amount, TextView_TotalMoney, TextView_Datetime;
        Button Button_CancelOrder;

        public Order_ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView_Product = itemView.findViewById(R.id.ImageView_Product);
            TextView_NameProduct = itemView.findViewById(R.id.TextView_NameProduct);
            TextView_Size = itemView.findViewById(R.id.TextView_Size);
            TextView_Price = itemView.findViewById(R.id.TextView_Price);
            TextView_Promotionalprice = itemView.findViewById(R.id.TextView_Promotionalprice);
            TextView_Amount = itemView.findViewById(R.id.TextView_Amount);
            TextView_TotalMoney = itemView.findViewById(R.id.TextView_TotalMoney);
            TextView_Datetime = itemView.findViewById(R.id.TextView_Datetime);
            Button_CancelOrder = itemView.findViewById(R.id.Button_CancelOrder);
        }
    }
}
