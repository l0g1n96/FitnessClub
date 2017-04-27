package com.fitness.consoleapp;

import dto.FitnessInputDTO;
import dto.MemberDTO;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleReader implements InputDataReader {
    private Scanner scanner;

    public ConsoleReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public FitnessInputDTO readFitnessInputDTO() {
        System.out.println("Choose an option by number:");
        int optionNumber = scanner.nextInt();

        if (optionNumber > 4 || optionNumber < 0) {
            throw new IllegalArgumentException("Option number not in range");
        }

        return new FitnessInputDTO(optionNumber);
    }

    @Override
    public MemberDTO readMemberDto() {

        System.out.println("Insert your name:");
        String name = scanner.next();

        System.out.println("Insert your lastname:");
        String lastname = scanner.next();

        System.out.println("Insert your ID:");
        int id = scanner.nextInt();

        return new MemberDTO(name, lastname, id);
    }

    @Override
    public int readNumHour() {

        System.out.println("How much hours would you like to reserve? (>0 && 4<)");
        int h = scanner.nextInt();

        if (h < 0 || h > 4) {
            System.out.println("Input error");
            return 0;
        }

        return h;
    }

    @Override
    public int[] readHours(int numHour) {

        int[] hours = new int[numHour];

        for (int i = 0; i < hours.length; i++) {
            System.out.println("Please insert " + (i + 1) + " hour: (08h - 19h)");
            hours[i] = scanner.nextInt();
        }

        return hours;
    }

    @Override
    public LocalDate readDate() {

        System.out.println("Please insert a date you want to reserve: (format: YYYY-MM-DD)");
        String d = scanner.next();

        return LocalDate.parse(d);
    }

    @Override
    public String readLastname() {
        System.out.println("Insert a lastname of member you want to search:");
        return scanner.next();
    }

}
