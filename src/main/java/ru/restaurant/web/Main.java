package ru.restaurant.web;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.restaurant.web.restuarant.RestaurantRestController;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml");
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            appCtx.refresh();
            RestaurantRestController rest = appCtx.getBean(RestaurantRestController.class);
            rest.getAll();
            System.out.println();
        }
    }
}
