package com.fitness.view;

import com.fitness.common.dto.MemberDTO;

import java.util.Collection;

public interface ResultPrinter {

    void printFreeTime(boolean[] registered, boolean[] freeTime);

    void printMenu();

    void printMembers(Collection<MemberDTO> members);

    void printMember(MemberDTO member);
}
