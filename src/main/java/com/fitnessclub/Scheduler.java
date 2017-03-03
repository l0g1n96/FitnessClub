package com.fitnessclub;

import com.fitnessclub.dto.Member;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Scheduler {

    private static final int WORKING_HOURS = 12;
    private static final int MAX_CAPACITY = 16;

    private Set<Member>[] scheduledMembers;

    public Scheduler() {
        constructArrayOfLists();
    }

    public Set<Member>[] getScheduledMembers() {
        return scheduledMembers;
    }

    public void searchMembers(Member member) {

        for (Set<Member> scheduledMember : scheduledMembers) {
            if (scheduledMember.contains(member)) {
                System.out.println("Member founded - ID:");
                System.out.println(member.getId());
            } else {
                System.out.println("Not found");
                break;
            }
        }
    }

    void reserve(Member member, int[] hours) {

        if (member == null || hours.length == 0 || hours.length > 3) {
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

    void deleteReservation(Member member, int[] hours) {

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
