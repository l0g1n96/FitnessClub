package com.fitness.service;

import com.fitness.common.Scheduler;
import com.fitness.common.dto.MemberDTO;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FitnessScheduler implements Scheduler {

    private static final int WORKING_HOURS = 12;
    private static final int MAX_CAPACITY = 16;
    private static final int MAX_BOOKED_HOURS = 3;
    public static final int BEGINNING_HOUR = 8;
    public static final int ENDING_HOUR = BEGINNING_HOUR + WORKING_HOURS;

    private Set<MemberDTO>[] scheduledMembers;

    public FitnessScheduler() {
        scheduledMembers = new Set[WORKING_HOURS];
        for (int i = 0; i < scheduledMembers.length; i++) {
            scheduledMembers[i] = new HashSet<>();
        }
    }

    @Override
    public boolean[] addToScheduler(MemberDTO member, int[] hours) {
        boolean[] freeTime = new boolean[WORKING_HOURS];
        int currentMaxBookedHours = MAX_BOOKED_HOURS;

        if (member == null || hours.length == 0 || hours.length > currentMaxBookedHours) {
            throw new IllegalArgumentException("Cannot be null");
        }

        for (int hour : hours) {

            if (hour < BEGINNING_HOUR || hour > ENDING_HOUR) {
                throw new IllegalArgumentException("Fitness Club cannot reserve for this hour");
            }

            for (Set<MemberDTO> membersSet : scheduledMembers) {
                if (membersSet.contains(member)) {
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

    @Override
    public void deleteScheduler(MemberDTO member, int[] hours) {
        if (member == null || hours.length == 0 || hours.length > MAX_BOOKED_HOURS) {
            throw new IllegalArgumentException("Cannot delete for this hour, or you didn't insert a member");
        }

        for (int hour : hours) {

            if (hour <= BEGINNING_HOUR || hour > ENDING_HOUR) {
                throw new IllegalArgumentException("Hour is wrong");
            }

            if (scheduledMembers[hour - BEGINNING_HOUR].contains(member)) {
                scheduledMembers[hour - BEGINNING_HOUR].remove(member);
            }
        }
    }

    @Override
    public boolean[] findFreeSlots() {
        boolean[] freeTime = new boolean[WORKING_HOURS];

        for (int i = 0; i < scheduledMembers.length; i++) {
            freeTime[i] = scheduledMembers[i].size() >= 16;
        }

        return freeTime;
    }

    @Override
    public Set<MemberDTO> getScheduledMembers(int hour) {
        if (hour <= BEGINNING_HOUR || hour > ENDING_HOUR) {
            throw new IllegalArgumentException("Hour is wrong");
        }

        return scheduledMembers[hour - BEGINNING_HOUR];
    }

    @Override
    public Set<MemberDTO> getAllScheduledMembers() {
        Set<MemberDTO> members = new HashSet<>();
        for (Set<MemberDTO> memberSet : scheduledMembers) {
            members.addAll(memberSet);
        }
        return members;
    }

    @Override
    public MemberDTO getMember(String lastname) {
        for (Set<MemberDTO> memberSet : scheduledMembers) {
            Optional<MemberDTO> memberOptional = memberSet.stream()
                    .filter(m -> m.getLastname().toLowerCase().equals(lastname.toLowerCase())).findFirst();

            if (memberOptional.isPresent()) {
                return memberOptional.get();
            }
        }
        return null;
    }
}