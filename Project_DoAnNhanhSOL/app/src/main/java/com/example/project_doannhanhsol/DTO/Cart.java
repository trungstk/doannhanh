package com.example.project_doannhanhsol.DTO;

public class Cart {
    private int Id_Cart,Id_Account,Id_productdetails,Quantity,TotalMoney;
    private String Notes;

    public Cart(int id_Cart, int id_Account, int id_productdetails, int quantity, int totalMoney, String notes) {
        Id_Cart = id_Cart;
        Id_Account = id_Account;
        Id_productdetails = id_productdetails;
        Quantity = quantity;
        TotalMoney = totalMoney;
        Notes = notes;
    }

    public int getId_Cart() {
        return Id_Cart;
    }

    public int getId_Account() {
        return Id_Account;
    }

    public int getId_productdetails() {
        return Id_productdetails;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getTotalMoney() {
        return TotalMoney;
    }
    public String getNotes() {
        return Notes;
    }
}
