package com.example.project_doannhanhsol.DTO;

public class Love {
    private int Id_love,Id_Account,Id_product;
    private String Notes;

    public Love(int id_love, int id_Account, int id_product, String notes) {
        Id_love = id_love;
        Id_Account = id_Account;
        Id_product = id_product;
        Notes = notes;
    }

    public int getId_love() {
        return Id_love;
    }

    public int getId_Account() {
        return Id_Account;
    }

    public int getId_product() {
        return Id_product;
    }

    public String getNotes() {
        return Notes;
    }
}
