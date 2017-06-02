package com.fitness.service;

import com.fitness.common.Scheduler;
import com.fitness.common.dto.MemberDTO;
import com.fitness.common.service.FitnessClubService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Kljucni servis neophodan za funkcionisanje programa
 */

public class DefaultFitnessClubService implements FitnessClubService {

    private Map<LocalDate, Scheduler> schedulerMap;

    public DefaultFitnessClubService() {
        this.schedulerMap = new HashMap<>();
    }

    @Override
    public boolean[] register(MemberDTO member, LocalDate date, int[] hours) {
        if (member == null || date == null || hours.length == 0 || hours.length > 3) {
            throw new IllegalArgumentException("Cannot register, input problem");
        }

        Scheduler scheduler = schedulerMap.computeIfAbsent(date, ld -> new FitnessScheduler());
        return scheduler.addToScheduler(member, hours);
    }

    @Override
    public boolean unregister(MemberDTO member, LocalDate date, int[] hours) {
        if (member == null || date == null || hours.length > 3 || hours.length == 0) {
            throw new IllegalArgumentException("Wrong input");
        }

        Scheduler scheduler = schedulerMap.get(date);
        if (scheduler == null) {
            System.out.println("No Scheduler");
            return false;
        }

        scheduler.deleteScheduler(member, hours);
        return true;
    }

    /**
     * Resava stavku a
     * izbacuje sve clanove koji su trenutno u fitnes klubu
     *
     * @return Set membera, ako nema nikog vraca se null
     */
    @Override
    public Set<MemberDTO> showMembersInFitnessClubNow() {
        Scheduler scheduler = schedulerMap.computeIfAbsent(LocalDate.now(), ld -> new FitnessScheduler());

        int hour = LocalDateTime.now().getHour();
        return scheduler.getScheduledMembers(hour);
    }

    /**
     * Resava stavku b
     * izbacuje sve clanove koji su danas u fitnes klubu
     *
     * @return Set membera, ako nema nikog vraca se null
     */
    @Override
    public Set<MemberDTO> showTodaysMembers() {
        Scheduler scheduler = schedulerMap.computeIfAbsent(LocalDate.now(), ld -> new FitnessScheduler());
        return scheduler.getAllScheduledMembers();
    }

    /**
     * Resava stavku c
     * da se pretrazuje Fitness Club po prezimenu member-a
     *
     * @return Member-a sa datim prezimenom, ako ne postoji vrati null
     */
    @Override
    public MemberDTO searchForMember(String lastname) {
        for (Map.Entry<LocalDate, Scheduler> schedulerEntry : schedulerMap.entrySet()) {
            MemberDTO member = schedulerEntry.getValue().getMember(lastname);
            if (member != null) {
                return member;
            }
        }

        return null;
    }

    /**
     * Resava stavku d1
     * da kada clan pozove i hoce da zakaze da odredi da li uopste
     * danas moze da dodje (da li ima slobodnih perioda) i koliko max moze da ostane.
     *
     * @return Najduzi period danas dostupan ili null (!!!) ako danas uopste nema ista
     * slobodno
     */
    @Override
    public boolean[] findLongestAvailableToday(LocalDate date) {
        Scheduler scheduler = schedulerMap.computeIfAbsent(date, ld -> new FitnessScheduler());
        return scheduler.findFreeSlots();
    }

}
