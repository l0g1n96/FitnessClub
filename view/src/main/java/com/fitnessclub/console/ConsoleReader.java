package com.fitnessclub.console;

import com.fitnessclub.core.dto.FitnessInput;

import java.util.Scanner;

public class ConsoleReader implements InputDataReader {

    @Override
    public FitnessInput read() throws Exception {

        Scanner s = new Scanner(System.in);

        System.out.println("====================");
        System.out.println("Options:");
        System.out.println("1: List of members that are in currently");
        System.out.println("2: List of members that are in today");
        System.out.println("3: Search by lastname");
        System.out.println("4: Registration and Reservation");
        System.out.println("0: Exit");
        System.out.println("====================");
        System.out.println();

        System.out.println("Choose an option by number:");
        int optionNumber = s.nextInt();

        if (optionNumber > 4 || optionNumber < 0) {
            throw new Exception("Option not in range 0 - 4");
        }

        return new FitnessInput(optionNumber);
    }
}
