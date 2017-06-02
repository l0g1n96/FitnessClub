package com.fitness.view;

import com.fitness.common.dto.FitnessInputDTO;
import com.fitness.common.dto.MemberDTO;
import com.fitness.common.service.FitnessClubService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class FitnessUI {
    private final FitnessClubService fitnessClub;
    private final InputDataReader input;
    private final ResultPrinter printer;

    public FitnessUI(FitnessClubService fitnessClub, InputDataReader input, ResultPrinter printer) {
        this.fitnessClub = fitnessClub;
        this.input = input;
        this.printer = printer;
    }

    public void run() {
        while (true) {
            printer.printMenu();
            FitnessInputDTO optionNumber = input.readFitnessInputDTO();

            if (optionNumber == null || optionNumber.getOptionNumber() == 0) {
                break;
            }

            doWork(optionNumber);
        }
    }

    private void doWork(FitnessInputDTO fitnessInputDTO) {

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
