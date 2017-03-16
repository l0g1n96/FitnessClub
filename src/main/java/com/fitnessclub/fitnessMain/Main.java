package com.fitnessclub.fitnessMain;

import com.fitnessclub.console.ConsolePrinter;
import com.fitnessclub.console.ConsoleReader;
import com.fitnessclub.dto.FitnessInput;
import com.fitnessclub.dto.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static FitnessClub fitnessClub = new FitnessClub();

    public static void main(String[] args) throws Exception {

        InputDataReader consoleReader = new ConsoleReader();
        ResultPrinter resultPrinter = new ConsolePrinter();

        while (true) {
            FitnessInput inputData = consoleReader.read();
            if (inputData.getOptionNumber() == 0) {
                break;
            }

            doWork(inputData, resultPrinter);
        }
    }

    private static void doWork(FitnessInput fitnessInput, ResultPrinter resultPrinter) throws Exception {

        int n = fitnessInput.getOptionNumber();

        switch (n) {
            case 1:
                showMembersThatAreCurrentlyInFitnessClub(resultPrinter);
                break;
            case 2:
                showMembersThatAreTodayInFitnessClub(resultPrinter);
                break;
            case 3:
                searchFitnessClubByMember();
                break;
            case 4:
                reservationForCaseFour();
                break;
        }
    }

    private static void showMembersThatAreCurrentlyInFitnessClub(ResultPrinter resultPrinter) {
        Scheduler scheduler = fitnessClub.getSchedulerMap().get(LocalDate.now());

        if (scheduler == null) {
            System.out.println("No members!");
            return;
        }

        int hour = LocalDateTime.now().getHour();

        if (hour >= 13 || hour <= 24) {
            hour -= 12;
        }

        resultPrinter.print(scheduler.getScheduledMembers()[hour].toString());
    }

    private static void showMembersThatAreTodayInFitnessClub(ResultPrinter resultPrinter) {
        Scheduler scheduler = fitnessClub.getSchedulerMap().get(LocalDate.now());

        if (scheduler == null) {
            System.out.println("No members!");
            return;
        }

        resultPrinter.print(scheduler.toString());
    }

    private static void searchFitnessClubByMember() {
        Scanner s = new Scanner(System.in);
        System.out.println("Type a lastname you want to search");
        String lastName = s.nextLine();

        Map<LocalDate, Scheduler> schedulerMap = fitnessClub.getSchedulerMap();
        for (Map.Entry<LocalDate, Scheduler> schedulerElement : schedulerMap.entrySet()) {
            for (Set<Member> memberSet : schedulerElement.getValue().getScheduledMembers()) {
                Optional<Member> memberOptional = memberSet.stream()
                        .filter(m -> m.getLastName().toLowerCase().equals(lastName.toLowerCase()))
                        .findFirst();

                if (memberOptional.isPresent()) {
                    System.out.println("ID: " + memberOptional.get().getId());
                    return;
                }
            }
        }

        System.out.println("No such member!");
    }

    private static void reservationForCaseFour() throws Exception {

        Scanner s = new Scanner(System.in);

        System.out.println("=====Registration=====");
        System.out.println("Enter your name: ");
        String name = s.nextLine();
        System.out.println("Enter your lastname: ");
        String lastName = s.nextLine();
        System.out.println("Enter your id");
        int id = s.nextInt();
        System.out.println("=====Finished=====");
        System.out.println();

        Member member = new Member(name, lastName, id);

        System.out.println();
        System.out.println("=====Reservation=====");

        System.out.println("How many hours you want to reserve? (>0 && <4)");
        int h = s.nextInt();

        if (h < 0 || h > 3) {
            System.out.println("Must be 1-3h max");
            return;
        }

        int[] hours = new int[h];

        for (int i = 0; i < h; i++) {
            System.out.println("Type the " + (i + 1) + " hour: (08 - 19)");
            hours[i] = s.nextInt();
        }

        System.out.println("Enter date you want to reserve: (format: YYYY-MM-DD)");
        String date = s.next();

        LocalDate localDate = LocalDate.parse(date);

        System.out.println("=====Finished reservation=====");
        System.out.println();

        boolean[] registeredHours = fitnessClub.register(member, localDate, hours);

        Scheduler scheduler = fitnessClub.getSchedulerMap().get(localDate);

        if (scheduler == null) {
            System.out.println("No members for this date");
            return;
        }

        boolean[] actual = scheduler.findFreeSlots();

        for (int i = 0; i < registeredHours.length; i++) {
            if (registeredHours[i]) {
                System.out.println("You can't book fitness club for " + hours[i] + " hour");

                System.out.println("You can reserve for: ");

                for (int j = 0; j < actual.length; j++) {
                    if (actual[i]) {
                        System.out.print(i + " ");
                    }
                }
            }
        }
    }
}