package com.example.project_doannhanhsol.DTO;

public class Product {
    private int Id_product,Id_danhmuc,sales,views;
    private String NameProduct,Content,Imagelinks,JoinDate;

    public Product(int id_product, int id_danhmuc, int sales, int views, String nameProduct, String content, String imagelinks, String joinDate) {
        Id_product = id_product;
        Id_danhmuc = id_danhmuc;
        this.sales = sales;
        this.views = views;
        NameProduct = nameProduct;
        Content = content;
        Imagelinks = imagelinks;
        JoinDate = joinDate;
    }

    public int getId_product() {
        return Id_product;
    }

    public int getId_danhmuc() {
        return Id_danhmuc;
    }

    public int getSales() {
        return sales;
    }

    public int getViews() {
        return views;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public String getContent() {
        return Content;
    }

    public String getImagelinks() {
        return Imagelinks;
    }

    public String getJoinDate() {
        return JoinDate;
    }
}
