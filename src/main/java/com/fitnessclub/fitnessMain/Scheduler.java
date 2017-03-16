package com.fitnessclub.fitnessMain;

import com.fitnessclub.dto.Member;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Scheduler {

    private static final int WORKING_HOURS = 12;
    private static final int MAX_CAPACITY = 16;
    private static final int MAX_BOOKED_HOURS = 3;
    private static final int BEGINNING_HOUR = 8;
    private static final int ENDING_HOUR = 20;

    private Set<Member>[] scheduledMembers;

    public Scheduler() {
        constructArrayOfLists();
    }

    public Set<Member>[] getScheduledMembers() {
        return scheduledMembers;
    }

    public boolean[] reserve(Member member, int[] hours) {

        boolean[] freeTime = new boolean[WORKING_HOURS];
        int currentMaxBookedHours = MAX_BOOKED_HOURS;

        if (member == null || hours.length == 0 || hours.length > currentMaxBookedHours) {
            throw new IllegalArgumentException("No members, or you tried to reserve more than 3 hours");
        }

        for (int hour : hours) {
            if (hour < 0 || hour > ENDING_HOUR) {
                throw new IllegalArgumentException("Fitness club is not working!");
            }

            for (Set<Member> memberSet : scheduledMembers) {
                if (memberSet.contains(member)) {
                    --currentMaxBookedHours;
                    if (currentMaxBookedHours < 1) {
                        return freeTime;
                    }
                }
            }

            if (scheduledMembers[hour - BEGINNING_HOUR].size() < MAX_CAPACITY) {
                scheduledMembers[hour - BEGINNING_HOUR].add(member);
            }

            if (scheduledMembers[hour - BEGINNING_HOUR].size() == 16) {
                freeTime[hour - BEGINNING_HOUR] = true;
            }
        }

        return freeTime;
    }

    public boolean[] findFreeSlots() {

        boolean[] freeTime = new boolean[WORKING_HOURS];

        for (int i = 0; i < scheduledMembers.length; i++) {
            freeTime[i] = scheduledMembers[i].size() < MAX_CAPACITY;
        }

        return freeTime;
    }

    public void deleteReservation(Member member, int[] hours) {

        if (member == null || hours.length == 0 || hours.length > 3) {
            throw new IllegalArgumentException("Member not initialized or reservation is bigger than 3 hours");
        }

        for (int hour : hours) {
            if (hour < 0 || hour > WORKING_HOURS) {
                throw new IllegalArgumentException("Fitness club does not have reservation for hour > 12 or hour < 0");
            }

            scheduledMembers[hour].remove(member);
        }
    }

    @SuppressWarnings("unchecked")
    private void constructArrayOfLists() {
        scheduledMembers = new Set[WORKING_HOURS];
        for (int i = 0; i < scheduledMembers.length; i++) {
            scheduledMembers[i] = new HashSet<>();
        }
    }

    @Override
    public String toString() {
        return "Scheduler{" +
                "scheduledMembers=" + Arrays.toString(scheduledMembers) +
                '}';
    }
}
