package core.service;

import datamanage.Scheduler;
import dto.MemberDTO;

import java.time.LocalDate;
import java.util.*;

/**
 * Kljucni servis neophodan za funkcionisanje programa
 */

public class FitnessClubServiceImpl implements FitnessClubService {

    private Map<LocalDate, Scheduler> schedulerMap;

    public FitnessClubServiceImpl() {
        this.schedulerMap = new HashMap<>();
    }

    @Override
    public boolean[] register(MemberDTO member, LocalDate date, int[] hours) {

        if (member == null || date == null || hours.length == 0 || hours.length > 3) {
            throw new IllegalArgumentException("Cannot register, input problem");
        }

        Scheduler scheduler = schedulerMap.computeIfAbsent(date, s -> new Scheduler());

        return scheduler.addToScheduler(member, hours);
    }

    @Override
    public boolean unregister(MemberDTO member, LocalDate date, int[] hours) {
        boolean check;

        if (member == null || date == null || hours.length > 3 || hours.length == 0) {
            throw new IllegalArgumentException("Wrong input");
        }

        check = schedulerMap.containsKey(date);

        Scheduler scheduler = schedulerMap.get(date);

        if (scheduler == null) {
            System.out.println("No Scheduler");
            return false;
        }

        scheduler.deleteScheduler(member, hours);

        return check;
    }

    /**
     * Resava stavku c
     * da se pretrazuje Fitness Club po prezimenu member-a
     *
     * @return Member-a sa datim prezimenom, ako ne postoji vrati null
     */

    @Override
    public MemberDTO searchForMember() {

        Scanner s = new Scanner(System.in);

        System.out.println("Insert a lastname of member you want to search:");
        String lastname = s.nextLine();

        for(Map.Entry<LocalDate, Scheduler> schedulerEntry : schedulerMap.entrySet()) {
            for(Set<MemberDTO> memberSet : schedulerEntry.getValue().getScheduledMembers()) {
                Optional<MemberDTO> memberOptional = memberSet.stream()
                        .filter(m -> m.getLastname().toLowerCase().equals(lastname.toLowerCase())).findFirst();

                if(memberOptional.isPresent()) {
                    String name = memberOptional.get().getName();
                    int id = memberOptional.get().getId();

                    return new MemberDTO(name, lastname, id);
                }
            }
        }

        return null;
    }


    /**
     * Resava stavku d1
     * da kada clan pozove i hoce da zakaze da odredi da li uopste
     * danas moze da dodje (da li ima slobodnih perioda) i koliko max moze da ostane.
     *
     * @return Najduzi period danas dostupan ili -1 (!!!) ako danas uopste nema ista
     * slobodno
     */

    @Override
    public int findLongestAvailableToday() {

        int longestAvailable = 0;

        Scheduler scheduler = schedulerMap.get(LocalDate.now());
        Set<MemberDTO>[] scheduledMembers = scheduler.getScheduledMembers();

        for (int i = 0; i < scheduledMembers.length; i++) {
            if (scheduledMembers[i].size() < 16 && scheduledMembers[i + 1].size() < 16) {
                longestAvailable++;
            }
        }

        if (longestAvailable != 0) {
            return longestAvailable;
        }

        return -1;
    }

    public Map<LocalDate, Scheduler> getSchedulerMap() {
        return schedulerMap;
    }
}
