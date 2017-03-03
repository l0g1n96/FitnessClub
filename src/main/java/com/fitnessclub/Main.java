package com.fitnessclub;

import com.fitnessclub.dto.FitnessInput;
import com.fitnessclub.dto.Member;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class Main {
    private static FitnessClub fitnessClub = new FitnessClub();

    public static void main(String[] args) throws Exception {

        InputDataReader consoleReader = new ConsoleReader();
        ResultPrinter resultPrinter = new ConsoleResultPrinter();

        FitnessClub fitnessClub = new FitnessClub();
        
        FitnessInput inputData = consoleReader.read();
        doWork(inputData, resultPrinter);

    }

    public static void doWork(FitnessInput fitnessInput, ResultPrinter resultPrinter) throws Exception {
        int n = fitnessInput.getOptionNumber();
        Member m = new Member();

        if (n == 1) {
            optionOne(resultPrinter);

        } else if (n == 2) {
            optionTwo(resultPrinter);

        } else {
            optionThree(m);
        }
    }

    //optionOne shows Map of members that are in the moment in the Fitness Club
    private static void optionOne(ResultPrinter resultPrinter) {

        Scheduler scheduler = fitnessClub.getSchedulerMap().get(Calendar.getInstance().getTime());

        for (int i = 0; i < scheduler.getScheduledMembers().length; i++) {
            resultPrinter.print(scheduler.getScheduledMembers()[i]);
        }
    }

    //optionTwo shows Map of members that are today in the Fitness Club
    private static void optionTwo(ResultPrinter resultPrinter) {
        Date d = new Date();
        Scheduler scheduler = fitnessClub.getSchedulerMap().get(d.getDate());

        for (Set<Member> set : scheduler.getScheduledMembers()) {
            resultPrinter.print(set);
        }
    }

    //optionThree shows ID of searched Member (if there is one)
    private static int optionThree(Member member) {
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
