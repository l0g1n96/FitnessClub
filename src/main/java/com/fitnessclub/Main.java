package com.fitnessclub;

import com.fitnessclub.dto.FitnessInput;
import com.fitnessclub.dto.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class Main {

    private static FitnessClub fitnessClub = new FitnessClub();

    public static void main(String[] args) throws Exception {

        InputDataReader consoleReader = new ConsoleReader();

        ResultPrinter resultPrinter = new ConsolePrinter();

        FitnessClub fitnessClub = new FitnessClub();

        FitnessInput inputData = consoleReader.read();
        doWork(inputData, resultPrinter);

    }

    static void doWork(FitnessInput fitnessInput, ResultPrinter resultPrinter) {
        int n = fitnessInput.getOptionNumber();
        Member m = new Member();

        switch (n) {
            case 1:
                showMembersThatAreCurrentlyInFitnessClub(resultPrinter);
                break;
            case 2:
                showMembersThatAreTodayInFitnessClub(resultPrinter);
                break;
            case 3:
                searchFitnessClubByMember(m);
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

    private static int searchFitnessClubByMember(Member member) {
        int n = 0;
        Scheduler scheduler = new Scheduler();

        for (Set<Member> m : scheduler.getScheduledMembers()) {
            if (m.contains(member)) {
                n += member.getId();
            }
        }

        return n;
    }

}
