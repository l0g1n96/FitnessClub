package com.fitnessclub;

import java.util.HashMap;
import java.util.Map;

public class Schedule {

    private Map<Integer[], String[]> scheduleMap = new HashMap<>();
    private static Schedule instance = null;

    private Schedule() {
    }

    public static Schedule getInstance() {
        if (instance == null) {
            instance = new Schedule();
        }

        return instance;
    }

    public Map<Integer[], String[]> getScheduleMap() {
        return scheduleMap;
    }
}
