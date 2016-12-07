package ru.restaurant.util;

import ru.restaurant.model.*;
import ru.restaurant.util.exception.NotFoundException;

import java.util.Objects;

public class EntityUtil {

    private EntityUtil() {
    }

    public static void checkForNull(BaseEntity entity, String message){
        if (entity == null){
            throw new NotFoundException(message);
        }
    }
}
