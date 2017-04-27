package com.fitness.consoleapp;

import core.service.DefaultFitnessClubService;
import dto.FitnessInputDTO;
import dto.MemberDTO;

import java.time.LocalDate;
import java.util.Set;

public class FitnessUI {

    private static DefaultFitnessClubService fitnessClub = new DefaultFitnessClubService();

    private static InputDataReader input = new ConsoleReader(System.in);
    private static ResultPrinter printer = new ConsolePrinter(System.out);

    public static void main(String[] args) {

        while (true) {
            printer.printMenu();
            FitnessInputDTO optionNumber = input.readFitnessInputDTO();

            if (optionNumber.getOptionNumber() == 0) {
                break;
            }

            doWork(optionNumber);
        }
    }

    private static void doWork(FitnessInputDTO fitnessInputDTO) {

        int n = fitnessInputDTO.getOptionNumber();

        switch (n) {

            case 1:
                Set<MemberDTO> members = fitnessClub.showMembersInFitnessClubNow();
                printer.printMembers(members);
                break;

            case 2:
                Set<MemberDTO> memberDTOs = fitnessClub.showTodaysMembers();
                printer.printMembers(memberDTOs);
                break;

            case 3:
                String lastname = input.readLastname();
                MemberDTO member = fitnessClub.searchForMember(lastname);
                printer.printMember(member);
                break;

            case 4:
                MemberDTO memberDTO = input.readMemberDto();
                int numHour = input.readNumHour();
                int[] hours = input.readHours(numHour);
                LocalDate date = input.readDate();

                boolean[] registerHours = fitnessClub.register(memberDTO, date, hours);
                boolean[] freeSlots = fitnessClub.findLongestAvailableToday(date);

                printer.printFreeTime(registerHours, freeSlots);
                break;
        }
    }
}
