package ru.restaurant.util;

import java.time.LocalTime;

public class TimeUtil {
    public static final LocalTime STOP_TIME = LocalTime.of(11, 0);
    public static final LocalTime LANCH_TIME = LocalTime.of(15, 0);
//    public static final LocalTime LANCH_TIME = LocalTime.of(23, 0);

    public static boolean checkTime(LocalTime localTime){
        return localTime.isAfter(STOP_TIME);
    }

    public static boolean checkLaunchTime(LocalTime localTime){
        return localTime.isAfter(LANCH_TIME);
    }
}
