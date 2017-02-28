package com.fitnessclub;

import com.fitnessclub.dto.FitnessInput;

import java.util.Scanner;

public class ConsoleReader implements InputDataReader {

    @Override
    public FitnessInput read() {

        Scanner s = new Scanner(System.in);

        System.out.println("====================");
        System.out.println("Options:");
        System.out.println("1: List of members that are in currently");
        System.out.println("2: List of members that are in today");
        System.out.println("3: Search by lastname");
        System.out.println("====================");

        System.out.println("Choose an option by number:");
        int optionNumber = s.nextInt();

        FitnessInput fitnessInput = new FitnessInput(optionNumber);

        return fitnessInput;
    }
}
