package ru.restuarant.model;

public class User extends BaseEntity{

    private boolean admin;

    private String password;

    public boolean isAdmin(){
        return admin;
    }
}
