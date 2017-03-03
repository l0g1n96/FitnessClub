package com.fitnessclub;

import com.fitnessclub.dto.Member;

import java.util.Set;

public interface ResultPrinter {

    void print(Set<Member> members);

}
