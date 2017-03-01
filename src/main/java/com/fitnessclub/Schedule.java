package com.fitnessclub;

import com.fitnessclub.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private List<List<Member>> scheduleList;
    private static Schedule instance = null;

    private Schedule() {
    }

    public static Schedule getInstance() {
        if (instance == null) {
            instance = new Schedule();
        }

        return instance;
    }

    public List<List<Member>> getScheduleList() {
        return scheduleList;
    }


    //TODO: Ne treba mi lista unutar metode, dva fora jedan kroz jednu, drugi kroz drugu listu..
    public List<List<Member>> reservation(Member member, int[] hours) {

        List<Member> members = new ArrayList<>();

        for (int i = 0; i < hours.length; i++){

            if(scheduleList.get(hours[i]).isEmpty()){
                members.add(member);
            }

            scheduleList.add(members);
        }

        return scheduleList;

    }


}
