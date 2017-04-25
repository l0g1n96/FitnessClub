package com.fitness.consoleapp;

import core.service.DefaultFitnessClubService;
import dto.FitnessInputDTO;

public class FitnessUI {

    private static DefaultFitnessClubService fitnessClub = new DefaultFitnessClubService();

    public static void main(String[] args) {

        InputDataReader input = new ConsoleReader();

        while (true) {
            FitnessInputDTO optionNumber = input.read();

            if (optionNumber.getOptionNumber() == 0) {
                break;
            }

            doWork(optionNumber);
        }
    }

    private static void doWork(FitnessInputDTO input) {

        int n = input.getOptionNumber();

        switch (n) {

            case 1:
                System.out.println(fitnessClub.showMembersInFitnessClubNow());
                break;

            case 2:
                System.out.println(fitnessClub.showTodaysMembers());
                break;

            case 3:
                System.out.println(fitnessClub.searchForMember());
                break;

            case 4:
                fitnessClub.findLongestAvailableToday();
                break;
        }
    }
}
