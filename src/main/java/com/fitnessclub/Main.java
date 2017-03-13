package com.fitnessclub;

import com.fitnessclub.dto.FitnessInput;
import com.fitnessclub.dto.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static FitnessClub fitnessClub = new FitnessClub();

    public static void main(String[] args) throws Exception {

        InputDataReader consoleReader = new ConsoleReader();
        ResultPrinter resultPrinter = new ConsolePrinter();


        FitnessInput inputData = consoleReader.read();
        doWork(inputData, resultPrinter);

    }

    private static void doWork(FitnessInput fitnessInput, ResultPrinter resultPrinter) {
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
        }
    }

    private static void showMembersThatAreCurrentlyInFitnessClub(ResultPrinter resultPrinter) {
        Scheduler scheduler = fitnessClub.getSchedulerMap().get(LocalDate.now());
        int hour = LocalDateTime.now().getHour();
        resultPrinter.print(scheduler.getScheduledMembers()[hour].toString());
    }

    private static void showMembersThatAreTodayInFitnessClub(ResultPrinter resultPrinter) {
        Scheduler scheduler = fitnessClub.getSchedulerMap().get(LocalDate.now());
        resultPrinter.print(scheduler.toString());
    }

    private static int searchFitnessClubByMember() {

        int n = 0;
        Scanner s = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();

        System.out.println("Type a lastname you want to search");
        String lastName = s.nextLine();

        for (Set<Member> memberSet : scheduler.getScheduledMembers()) {
            for (Member member : memberSet) {
                if (member.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                    n = member.getId();
                    break;
                }
            }
        }

        System.out.println(n);
        return n;
    }

}
