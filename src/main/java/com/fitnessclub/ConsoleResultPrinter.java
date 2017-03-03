package com.fitnessclub;

import com.fitnessclub.dto.Member;

import java.util.Set;

public class ConsoleResultPrinter implements ResultPrinter {

    @Override
    public void print(Set<Member> members) {

        for(Member m : members){
            System.out.println(m.toString());
        }

    }
}
