package com.fitnessclub.fitnessMain;

import com.fitnessclub.dto.Member;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FitnessClub {

    private static final int BEGINING_HOUR = 8;
    private static final int ENDING_HOUR = 20;

    private Map<LocalDate, Scheduler> schedulerMap;

    public FitnessClub() {
        this.schedulerMap = new HashMap<>();
    }

    Map<LocalDate, Scheduler> getSchedulerMap() {
        return schedulerMap;
    }

    public boolean[] register(Member member, LocalDate date, int[] hours) throws Exception {

        if (member == null || hours.length == 0 || hours.length > 3) {
            throw new IllegalArgumentException("No member");
        }

        for (int hour : hours) {
            if (hour < 0 || hour > 12) {
                throw new IllegalArgumentException("Fitness club is not working!");
            }
        }

        Scheduler scheduler = schedulerMap.computeIfAbsent(date, s -> new Scheduler());

        return scheduler.reserve(member, hours);
    }

    public boolean unregister(Member member, LocalDate date, int[] hours) {

        boolean check;

        if (member == null || hours.length == 0 || hours.length > 3) {
            throw new IllegalArgumentException("No member");
        }

        check = schedulerMap.containsKey(date);

        Scheduler scheduler = schedulerMap.get(date);
        scheduler.deleteReservation(member, hours);

        return check;
    }

    @Override
    public String toString() {
        return "FitnessClub{" +
                "schedulerMap=" + schedulerMap +
                '}';
    }
}
