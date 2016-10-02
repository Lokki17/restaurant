package ru.restuarant.web;

public class AuthorizedUser {
    private static Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        AuthorizedUser.id = id;
    }
}
