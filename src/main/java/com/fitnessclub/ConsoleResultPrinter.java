package com.fitnessclub;

import com.fitnessclub.dto.FitnessOutput;
import com.fitnessclub.dto.Member;

public class ConsoleResultPrinter implements ResultPrinter{

    @Override
    public void print(FitnessOutput fitnessOutput) {

        for(Member m : fitnessOutput.getMemberList()){
            System.out.println(m.toString());
        }
    }
}
