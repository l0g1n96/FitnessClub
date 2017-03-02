package com.fitnessclub;

import com.fitnessclub.dto.Member;

import java.util.HashSet;
import java.util.Set;

public class Scheduler {

    private static final int WORKING_HOURS = 12;
    private static final int MAX_CAPACITY = 16;

    private Set<Member>[] scheduledMembers;
    private static Scheduler instance;

    private Scheduler() {
        constructArrayOfLists();
    }

    public static Scheduler getInstance() {
        if (instance == null) {
            instance = new Scheduler();
        }

        return instance;
    }

    public Set<Member>[] getScheduledMembers() {
        return scheduledMembers;
    }

    public void reserve(Member member, int[] hours) {
        if (member == null || hours.length <= 0 || hours.length > 3) {
            throw new IllegalArgumentException("No members, or you tried to reserve more than 3 hours");
        }

        for (int hour : hours) {
            if (hour < 0 || hour > WORKING_HOURS) {
                throw new IllegalArgumentException("Fitness club is not working!");
            }

            if (scheduledMembers[hour].size() < MAX_CAPACITY) {
                scheduledMembers[hour].add(member);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void constructArrayOfLists() {
        scheduledMembers = new Set[WORKING_HOURS];
        for (int i = 0; i < scheduledMembers.length; i++) {
            scheduledMembers[i] = new HashSet<>();
        }
    }

    public void deleteReservation(Member member, int hour[]) {

        if (member == null || hour.length > 3 || hour.length <= 0) {
            throw new IllegalArgumentException("Member not initialized or reservation is bigger than 3 hours");
        }

        for (int hours : hour) {
            if (hours < 0 || hours > WORKING_HOURS) {
                throw new IllegalArgumentException("Fitness club does not have reservation for hour > 12 or hour < 0");
            }

            scheduledMembers[hours].remove(member);
        }
    }
}
