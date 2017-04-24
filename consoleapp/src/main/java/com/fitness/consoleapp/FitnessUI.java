package com.fitness.consoleapp;

import core.service.FitnessClubServiceImpl;
import dto.FitnessInputDTO;

public class FitnessUI {

    public static void main(String[] args) {

        InputDataReader input = new ConsoleReader();
        ResultPrinter printer = new ConsolePrinter();


        while (true) {
            FitnessInputDTO optionNumber = input.read();

            if (optionNumber.getOptionNumber() == 0) {
                break;
            }
            doWork(optionNumber, printer);
        }
    }

    private static void doWork(FitnessInputDTO input, ResultPrinter printer) {
        //printer is for first and second case

        FitnessClubServiceImpl fitnessClub = new FitnessClubServiceImpl();

        int n = input.getOptionNumber();

        switch (n) {

            case 3:
                System.out.println(fitnessClub.searchForMember());
                break;

            case 4:
                fitnessClub.findLongestAvailableToday();
                break;
        }
    }
}
