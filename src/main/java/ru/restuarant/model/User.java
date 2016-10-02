package ru.restuarant.model;

public class User extends NamedEntity{

    private Role role;

    private String password;

    public boolean isAdmin(){
        return role == Role.ADMIN;
    }
}
