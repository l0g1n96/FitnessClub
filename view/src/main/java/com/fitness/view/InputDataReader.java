package com.fitness.view;

import com.fitness.common.dto.FitnessInputDTO;
import com.fitness.common.dto.MemberDTO;

import java.time.LocalDate;

public interface InputDataReader {

    FitnessInputDTO readFitnessInputDTO();

    MemberDTO readMemberDto();

    int readNumHour();

    int[] readHours(int numHour);

    LocalDate readDate();

    String readLastname();
}
