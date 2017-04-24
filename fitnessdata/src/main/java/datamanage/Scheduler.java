package datamanage;

import dataprocess.SchedulerData;
import dto.MemberDTO;

import java.util.HashSet;
import java.util.Set;

public class Scheduler implements SchedulerData {

    private static final int WORKING_HOURS = 12;
    private static final int MAX_CAPACITY = 16;
    private static final int MAX_BOOKED_HOURS = 3;
    private static final int BEGINNING_HOUR = 8;
    private static final int ENDING_HOUR = 20;

    private Set<MemberDTO>[] scheduledMembers;

    public Scheduler() {
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

            if (hour < 0 || hour > ENDING_HOUR) {
                throw new IllegalArgumentException("Fitness Club cannot reserve for this hour");
            }

            for (Set<MemberDTO> membersSet : scheduledMembers) {
                if (membersSet.contains(member)) {
                    currentMaxBookedHours--;

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

            if (hour <= 0 || hour > WORKING_HOURS) {
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

    public Set<MemberDTO>[] getScheduledMembers() {
        return scheduledMembers;
    }
}
