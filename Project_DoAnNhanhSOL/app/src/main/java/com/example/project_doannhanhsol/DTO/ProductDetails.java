package com.example.project_doannhanhsol.DTO;

public class ProductDetails {
    private int Id_productdetails,Id_product,Price,Promotionalprice;
    private String Size;

    public ProductDetails(int id_productdetails, int id_product, int price, int promotionalprice, String size) {
        Id_productdetails = id_productdetails;
        Id_product = id_product;
        Price = price;
        Promotionalprice = promotionalprice;
        Size = size;
    }

    public int getId_productdetails() {
        return Id_productdetails;
    }

    public int getId_product() {
        return Id_product;
    }

    public int getPrice() {
        return Price;
    }

    public int getPromotionalprice() {
        return Promotionalprice;
    }

    public String getSize() {
        return Size;
    }
}
