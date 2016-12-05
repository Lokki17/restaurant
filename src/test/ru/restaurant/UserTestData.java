package ru.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.restaurant.matcher.ModelMatcher;
import ru.restaurant.model.Role;
import ru.restaurant.model.User;
import ru.restaurant.util.PasswordUtil;

import java.util.Objects;

import static ru.restaurant.model.BaseEntity.START_SEQ;

public class UserTestData {
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_USER_ID = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;

    public static final User USER = new User(USER_ID, "User1", "password", Role.ROLE_USER);
    public static final User ADMIN_USER = new User(ADMIN_USER_ID, "User2", "password", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin", Role.ROLE_ADMIN);

    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (comparePassword(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }

    public static User getUpdated(){
        return new User(USER_ID, "UserUpdated", "password", Role.ROLE_USER);
    }

    public static User getCreated(){
        return new User("UserCreated", "password", Role.ROLE_USER);
    }
}
