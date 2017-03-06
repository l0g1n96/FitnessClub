package com.fitnessclub;

import com.fitnessclub.dto.Member;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FitnessClub {

    private Map<LocalDate, Scheduler> schedulerMap;

    public FitnessClub() {
        this.schedulerMap = new HashMap<>();
    }

    Map<LocalDate, Scheduler> getSchedulerMap() {
        return schedulerMap;
    }

    /*
    boolean register(Member member, LocalDate date, int[] hours) {

        boolean check;

        //Scheduler scheduler = schedulerMap.computeIfAbsent(date, d -> new Scheduler());
        check = schedulerMap.containsKey(date);

        Scheduler scheduler = schedulerMap.get(date);
        scheduler.reserve(member, hours);

        return check;
    }
    */

    boolean[] register(Member member, LocalDate date, int[] hours){

        boolean[] freeTime = new boolean[12];



        return freeTime;
    }

    boolean unregister(Member member, LocalDate date, int[] hours) {

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
