package ru.restaurant.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.restaurant.model.User;
import ru.restaurant.to.UserTo;
import ru.restaurant.util.EntityUtil;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User{
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    //private static Integer id = 100002;

    public AuthorizedUser(User user) {
        super(user.getName(), user.getPassword(), user.getRoles());
        this.userTo = new UserTo(user);
        //super(user.getName(), user.getPassword(), true, true, true, true, user.getRoles());
        //this.userTo = EntityUtil.asTo(user);
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int getId() {
        return get().userTo.getId();
    }
}
