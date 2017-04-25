package core.service;

import datamanage.FitnessScheduler;
import dto.MemberDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Kljucni servis neophodan za funkcionisanje programa
 */

public class DefaultFitnessClubService implements FitnessClubService {

    private Map<LocalDate, FitnessScheduler> schedulerMap;

    public DefaultFitnessClubService() {
        this.schedulerMap = new HashMap<>();
    }

    @Override
    public boolean[] register(MemberDTO member, LocalDate date, int[] hours) {

        if (member == null || date == null || hours.length == 0 || hours.length > 3) {
            throw new IllegalArgumentException("Cannot register, input problem");
        }

        FitnessScheduler fitnessScheduler = schedulerMap.computeIfAbsent(date, s -> new FitnessScheduler());

        return fitnessScheduler.addToScheduler(member, hours);
    }

    @Override
    public boolean unregister(MemberDTO member, LocalDate date, int[] hours) {
        boolean check;

        if (member == null || date == null || hours.length > 3 || hours.length == 0) {
            throw new IllegalArgumentException("Wrong input");
        }

        check = schedulerMap.containsKey(date);

        FitnessScheduler fitnessScheduler = schedulerMap.get(date);

        if (fitnessScheduler == null) {
            System.out.println("No FitnessScheduler");
            return false;
        }

        fitnessScheduler.deleteScheduler(member, hours);

        return check;
    }

    /**
     * Resava stavku a
     * izbacuje sve clanove koji su trenutno u fitnes klubu
     *
     * @return Set membera, ako nema nikog vraca se null
     */

    @Override
    public Set<MemberDTO> showMembersInFitnessClubNow() {
        FitnessScheduler fitnessScheduler = schedulerMap.get(LocalDate.now());
        if (fitnessScheduler == null) {
            return null;
        }

        int hour = LocalDateTime.now().getHour();
        return fitnessScheduler.getScheduledMembers(hour);
    }

    /**
     * Resava stavku b
     * izbacuje sve clanove koji su danas u fitnes klubu
     *
     * @return Set membera, ako nema nikog vraca se null
     */

    @Override
    public Set<MemberDTO> showTodaysMembers() {
        FitnessScheduler scheduler = schedulerMap.get(LocalDate.now());
        if (scheduler == null) {
            return null;
        }

        return scheduler.getAllScheduledMembers();
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

        for (Map.Entry<LocalDate, FitnessScheduler> schedulerEntry : schedulerMap.entrySet()) {
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
     * @return Najduzi period danas dostupan ili -1 (!!!) ako danas uopste nema ista
     * slobodno
     */

    @Override
    public void findLongestAvailableToday() {

        Scanner s = new Scanner(System.in);

        System.out.println("Insert your name:");
        String name = s.nextLine();

        System.out.println("Insert your lastname:");
        String lastname = s.nextLine();

        System.out.println("Insert your ID:");
        int id = s.nextInt();

        MemberDTO member = new MemberDTO(name, lastname, id);

        System.out.println();

        System.out.println("How much hours would you like to reserve? (>0 && 4<)");
        int h = s.nextInt();

        if (h < 0 || h > 4) {
            System.out.println("Input error");
            return;
        }

        int[] hours = new int[h];

        for (int i = 0; i < h; i++) {
            System.out.println("Please insert " + (i + 1) + " hour: (08h - 19h)");
            hours[i] = s.nextInt();
        }

        System.out.println("Please insert a date you want to reserve: (format: YYYY-MM-DD)");
        String d = s.next();

        LocalDate date = LocalDate.parse(d);

        System.out.println("=====FINISHED=====");
        System.out.println();

        boolean[] registerHours = register(member, date, hours);

        FitnessScheduler fitnessScheduler = schedulerMap.get(date);

        if (fitnessScheduler == null) {
            System.out.println("No FitnessScheduler!");
            return;
        }

        boolean[] freeSlots = fitnessScheduler.findFreeSlots();

        for (int i = 0; i < registerHours.length; i++) {

            if (registerHours[i]) {
                System.out.println("You cannot book for " + i + " slot, it's full!");

                System.out.println("You can book for ");

                for (int j = 0; j < freeSlots.length; j++) {
                    if (!freeSlots[j]) {
                        System.out.print("" + j + " hour ");
                    }
                }
            }
        }
    }
}
