package com.fitnessclub.fitnessMain;

import com.fitnessclub.console.ConsolePrinter;
import com.fitnessclub.console.ConsoleReader;
import com.fitnessclub.dto.FitnessInput;
import com.fitnessclub.dto.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static FitnessClub fitnessClub = new FitnessClub();

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

    public static void showMembersThatAreCurrentlyInFitnessClub(ResultPrinter resultPrinter) {
        Scheduler scheduler = fitnessClub.getSchedulerMap().get(LocalDate.now());
        if(scheduler == null) {
            System.out.println("No members!");
            return;
        }

        int hour = LocalDateTime.now().getHour();
        resultPrinter.print(scheduler.getScheduledMembers()[hour].toString());
    }

    private static void showMembersThatAreTodayInFitnessClub(ResultPrinter resultPrinter) {
        Scheduler scheduler = fitnessClub.getSchedulerMap().get(LocalDate.now());
        if(scheduler == null) {
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
                    System.out.println("Id: " + memberOptional.get().getId());
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

        Member member = new Member(name, lastName, id);

        System.out.println("=====Reservation=====");

        System.out.println("How many hours you want to reserve? (>0 && <4)");
        int h = s.nextInt();
        int[] hours = new int[3];

        for (int i = 0; i < h; i++) {
            System.out.println("Type the " + (i + 1) + " hour");
            hours[i] = s.nextInt();
        }

        System.out.println("Enter date you want to reserve: (format: YYYY-MM-DD)");
        String date = s.next();

        LocalDate localDate = LocalDate.parse(date);

        System.out.println("=====Finished reservation=====");

        fitnessClub.register(member, localDate, hours);

    }


}
