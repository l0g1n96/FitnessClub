package com.fitness.common.service;

import com.fitness.common.dto.MemberDTO;

import java.util.Set;

public interface Scheduler {

    boolean[] addToScheduler(MemberDTO member, int[] hours);

    void deleteScheduler(MemberDTO member, int[] hours);

    boolean[] findFreeSlots();

    Set<MemberDTO> getScheduledMembers(int hour);

    MemberDTO getMember(String lastname);

    Set<MemberDTO> getAllScheduledMembers();
}
