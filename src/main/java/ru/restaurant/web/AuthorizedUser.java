package ru.restaurant.web;

public class AuthorizedUser {
    private static Integer id;

    public static Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        AuthorizedUser.id = id;
    }
}
