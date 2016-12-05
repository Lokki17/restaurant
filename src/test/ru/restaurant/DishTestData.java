package ru.restaurant;

import ru.restaurant.matcher.ModelMatcher;
import ru.restaurant.model.Dish;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

import static ru.restaurant.RestaurantTestData.RESTAURANT_1;
import static ru.restaurant.RestaurantTestData.RESTAURANT_2;
import static ru.restaurant.RestaurantTestData.RESTAURANT_3;
import static ru.restaurant.RestaurantTestData.RESTAURANT_4;
import static ru.restaurant.model.BaseEntity.START_SEQ;

public class DishTestData {

    public static final Integer DISH_ID = START_SEQ + 7;

    public static final Dish DISH_1 = new Dish(DISH_ID, LocalDate.now(), "Борщ", BigInteger.valueOf(15), RESTAURANT_1);
    public static final Dish DISH_2 = new Dish(DISH_ID + 1, LocalDate.now(), "Суп", BigInteger.valueOf(15), RESTAURANT_1);
    public static final Dish DISH_3 = new Dish(DISH_ID + 2, LocalDate.now(), "Котлеты", BigInteger.valueOf(15), RESTAURANT_2);
    public static final Dish DISH_4 = new Dish(DISH_ID + 3, LocalDate.now(), "Шницель", BigInteger.valueOf(15), RESTAURANT_2);
    public static final Dish DISH_5 = new Dish(DISH_ID + 4, LocalDate.now(), "Пицца", BigInteger.valueOf(15), RESTAURANT_3);
    public static final Dish DISH_6 = new Dish(DISH_ID + 5, LocalDate.now(), "Сосиски", BigInteger.valueOf(15), RESTAURANT_3);
    public static final Dish DISH_7 = new Dish(DISH_ID + 6, LocalDate.now(), "Просто еда", BigInteger.valueOf(15), RESTAURANT_4);
    public static final Dish DISH_8 = new Dish(DISH_ID + 7, LocalDate.now(), "Колбаса", BigInteger.valueOf(15), RESTAURANT_4);
    public static final Dish DISH_9 = new Dish(DISH_ID + 8, LocalDate.now(), "Вареники", BigInteger.valueOf(15), RESTAURANT_4);

    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class,
            (expected, actual) -> expected == actual ||
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getPrice(), actual.getPrice())
                            && Objects.equals(expected.getDate(), actual.getDate()));

    public static Dish getUpdated(){
        return new Dish(DISH_ID, DISH_1.getDate(), "Вкусный борщ", DISH_1.getPrice(), DISH_1.getRestaurant());
    }

    public static Dish getCreated(){
        return new Dish(DISH_1.getDate(), "Вкусный борщ с помпушками", DISH_1.getPrice(), DISH_1.getRestaurant());
    }
}
