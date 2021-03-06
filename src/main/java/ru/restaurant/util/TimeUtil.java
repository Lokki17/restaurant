package ru.restaurant.util;

import ru.restaurant.util.exception.WrongTimeException;

import java.time.LocalTime;
import java.util.Properties;

public class TimeUtil {

    private TimeUtil() {
    }

    public static final LocalTime STOP_TIME = LocalTime.of(21, 0);
    public static final LocalTime LUNCH_TIME = LocalTime.of(22, 0);

    public static void checkTime(LocalTime localTime) {
        if (localTime.isAfter(STOP_TIME)) {
            throw new WrongTimeException("You have made your choice today");
        }
    }

    public static void checkLaunchTime(LocalTime localTime) {
        if (localTime.isAfter(LUNCH_TIME)) {
            throw new WrongTimeException("Launch time is gone");
        }
    }
}
