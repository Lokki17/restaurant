package ru.restuarant.model;

public class User extends NamedEntity{

    private boolean admin;

    private String password;

    public boolean isAdmin(){
        return admin;
    }
}
