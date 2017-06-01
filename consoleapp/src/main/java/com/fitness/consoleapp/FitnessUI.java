package com.fitness.consoleapp;

import core.service.DefaultFitnessClubService;
import dto.FitnessInputDTO;
import dto.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Set;

public class FitnessUI {

    private static ApplicationContext context = new AnnotationConfigApplicationContext(JavaSpringConfig.class);

    private static DefaultFitnessClubService fitnessClub = (DefaultFitnessClubService) context.getBean("fitness");
    private static InputDataReader input = (InputDataReader) context.getBean("dataReader");
    private static ResultPrinter printer = (ResultPrinter) context.getBean("print");

    public static void main(String[] args) {

        while (true) {
            printer.printMenu();
            FitnessInputDTO optionNumber = input.readFitnessInputDTO();

            if (optionNumber == null || optionNumber.getOptionNumber() == 0) {
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
                if (memberDTO == null) {
                    break;
                }

                int numHour = input.readNumHour();
                if (numHour == -1) {
                    break;
                }
                int[] hours = input.readHours(numHour);
                if (hours == null) {
                    break;
                }
                LocalDate date = input.readDate();
                if (date == null) {
                    break;
                }

                boolean[] registerHours = fitnessClub.register(memberDTO, date, hours);
                boolean[] freeSlots = fitnessClub.findLongestAvailableToday(date);

                printer.printFreeTime(registerHours, freeSlots);
                break;
        }
    }
}
