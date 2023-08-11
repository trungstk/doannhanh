package com.example.project_doannhanhsol.DTO;

public class Account {
    private int Id_Account;
    private String Name,Email,Password,Numberphone,Address,JoinDate,Picture;


    public Account(int id_Account, String name, String email, String password, String numberphone, String address, String joinDate, String picture) {
        Id_Account = id_Account;
        Name = name;
        Email = email;
        Password = password;
        Numberphone = numberphone;
        Address = address;
        JoinDate = joinDate;
        Picture = picture;
    }

    public int getId() {
        return Id_Account;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getNumberphone() {
        return Numberphone;
    }

    public String getAddress() {
        return Address;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public String getPicture() {
        return Picture;
    }
}
