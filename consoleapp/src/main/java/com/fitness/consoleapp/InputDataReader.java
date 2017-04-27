package com.fitness.consoleapp;

import dto.FitnessInputDTO;
import dto.MemberDTO;

import java.time.LocalDate;

public interface InputDataReader {

    FitnessInputDTO readFitnessInputDTO();

    MemberDTO readMemberDto();

    int readNumHour();

    int[] readHours(int numHour);

    LocalDate readDate();

    String readLastname();

}
