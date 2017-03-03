package com.fitnessclub;

import com.fitnessclub.dto.Member;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FitnessClub {

    Map<LocalDate, Scheduler> schedulerMap;

    public FitnessClub() {
        this.schedulerMap = new HashMap<>();
    }

    public Map<LocalDate, Scheduler> getSchedulerMap() {
        return schedulerMap;
    }

    public void register(Member member, LocalDate date, int[] hours) {
        Scheduler scheduler = schedulerMap.computeIfAbsent(date, d -> new Scheduler());
        scheduler.reserve(member, hours);
    }

    public void unregister(Member member, LocalDate date, int[] hours) {

        if (member == null || hours.length == 0 || hours.length > 3) {
            throw new IllegalArgumentException("No member");
        }

        if (!schedulerMap.containsKey(date)) {
            return;
        }

        Scheduler scheduler = schedulerMap.get(date);
        scheduler.deleteReservation(member, hours);
    }

    @Override
    public String toString() {
        return "FitnessClub{" +
                "schedulerMap=" + schedulerMap +
                '}';
    }
}
