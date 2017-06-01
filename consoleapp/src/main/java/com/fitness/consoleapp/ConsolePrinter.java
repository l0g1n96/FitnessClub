package com.fitness.consoleapp;

import dto.MemberDTO;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Objects;

@Component
public class ConsolePrinter implements ResultPrinter {
    private PrintStream printStream;

    public ConsolePrinter(PrintStream printStream) {
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
        printStream.println("=========================================");
        printStream.println("Options:");
        printStream.println("1: List of members that are in currently");
        printStream.println("2: List of members that are in today");
        printStream.println("3: Search by lastname");
        printStream.println("4: Registration and Reservation");
        printStream.println("0: Exit");
        printStream.println("=========================================");
        printStream.println();
    }

    @Override
    public void printMembers(Collection<MemberDTO> members) {
        Objects.requireNonNull(members, "members == null");

        for (MemberDTO m : members) {
            printMember(m);
        }

        if (members.isEmpty()) {
            printStream.println("There are no members!");
            printStream.println();
        }
    }

    @Override
    public void printMember(MemberDTO member) {
        if (member != null) {
            printStream.println(member);
        } else {
            printStream.println("Member not found!");
        }
        printStream.println();
    }
}
