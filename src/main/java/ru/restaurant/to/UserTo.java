package ru.restaurant.to;

import ru.restaurant.model.User;

public class UserTo {
    private Integer id;
    private String userName;
    private String password;

    public UserTo(User user) {
        this.id = user.getId();
        this.userName = user.getName();
        this.password = user.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
