package com.example.project_doannhanhsol.DTO;

public class Category {
    private int Id_Category;
    private  String NameCategory,PictureCategory,JoinDate,Note;

    public Category(int id_Category, String nameCategory, String pictureCategory, String joinDate, String note) {
        Id_Category = id_Category;
        NameCategory = nameCategory;
        PictureCategory = pictureCategory;
        JoinDate = joinDate;
        Note = note;
    }

    public int getId_Category() {
        return Id_Category;
    }

    public String getNameCategory() {
        return NameCategory;
    }

    public String getPictureCategory() {
        return PictureCategory;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public String getNote() {
        return Note;
    }
}
