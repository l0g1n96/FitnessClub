package com.fitness.consoleapp;

import datamanage.FitnessScheduler;
import dto.FitnessInputDTO;
import dto.MemberDTO;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleReader implements InputDataReader {
    private Scanner scanner;
    private PrintStream printStream;

    public ConsoleReader(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    @Override
    public FitnessInputDTO readFitnessInputDTO() {
        printStream.println("Choose an option by number:");

        if (scanner.hasNextInt()) {
            int optionNumber = scanner.nextInt();
            if (optionNumber < 0 || optionNumber > 5) {
                return null;
            }
            return new FitnessInputDTO(optionNumber);
        } else {
            String error = scanner.next();
            printStream.println("\"" + error + "\" cannot be a option number!");
            printStream.println();
            return null;
        }
    }

    @Override
    public MemberDTO readMemberDto() {
        printStream.println("Insert your name:");
        String name = scanner.next();

        printStream.println("Insert your lastname:");
        String lastname = scanner.next();

        printStream.println("Insert your ID:");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            return new MemberDTO(name, lastname, id);
        } else {
            String error = scanner.next();
            printStream.println("\"" + error + "\" cannot be a member ID!");
            printStream.println();
            return null;
        }
    }

    @Override
    public int readNumHour() {

        printStream.println("How much hours would you like to reserve? (>0 && 4<)");
        if (scanner.hasNextInt()) {
            int h = scanner.nextInt();

            if (h <= 0 || h > 4) {
                printStream.println("Input error");
                return -1;
            }

            return h;
        }

        return -1;
    }

    @Override
    public int[] readHours(int numHour) {

        int[] hours = new int[numHour];

        for (int i = 0; i < hours.length; i++) {
            printStream.println("Please insert " + (i + 1) + ". hour: (" + FitnessScheduler.BEGINNING_HOUR + "h - " + FitnessScheduler.ENDING_HOUR + "h)");
            if (scanner.hasNextInt()) {
                hours[i] = scanner.nextInt();

                if (hours[i] < FitnessScheduler.BEGINNING_HOUR || hours[i] > FitnessScheduler.ENDING_HOUR) {
                    printStream.println("Cannot reserve for this hour");
                    return null;
                }
            }
        }

        return hours;
    }

    @Override
    public LocalDate readDate() {

        printStream.println("Please insert a date you want to reserve: (format: YYYY-MM-DD)");
        String d = scanner.next();

        try {
            return LocalDate.parse(d);
        } catch (DateTimeParseException dte) {
            printStream.println("Wrong input date!");
            printStream.println();
            return null;
        }
    }

    @Override
    public String readLastname() {
        printStream.println("Insert a lastname of member you want to search:");
        return scanner.next();
    }
}
