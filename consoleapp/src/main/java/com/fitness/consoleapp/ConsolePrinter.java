package com.fitness.consoleapp;

import dto.MemberDTO;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Objects;

public class ConsolePrinter implements ResultPrinter {
    private PrintStream printStream;

    ConsolePrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void printFreeTime(boolean[] registerHours, boolean[] freeSlots) {
        for (int i = 0; i < registerHours.length; i++) {
            if (registerHours[i]) {
                printStream.println("You cannot book for " + i + " slot, it's full!");
                printStream.println("You can book for ");
                for (int j = 0; j < freeSlots.length; j++) {
                    if (!freeSlots[j]) {
                        printStream.print("" + j + " hour ");
                    }
                }
            }
        }
    }

    @Override
    public void printMenu() {
        System.out.println("=========================================");
        System.out.println("Options:");
        System.out.println("1: List of members that are in currently");
        System.out.println("2: List of members that are in today");
        System.out.println("3: Search by lastname");
        System.out.println("4: Registration and Reservation");
        System.out.println("0: Exit");
        System.out.println("=========================================");
        System.out.println();
    }

    @Override
    public void printMembers(Collection<MemberDTO> members) {
        Objects.requireNonNull(members, "members == null");

        for(MemberDTO m : members) {
            printMember(m);
        }
    }

    @Override
    public void printMember(MemberDTO member) {
        printStream.println(member);
    }


}
