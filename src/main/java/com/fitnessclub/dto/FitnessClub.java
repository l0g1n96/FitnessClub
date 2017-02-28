package com.fitnessclub.dto;

import java.util.ArrayList;
import java.util.List;

public class FitnessClub {

    private List<Member> memberList = new ArrayList<>();
    private int time;

    public FitnessClub(List<Member> memberList, int time) {
        this.memberList = memberList;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FitnessClub that = (FitnessClub) o;

        if (time != that.time) return false;
        return memberList != null ? memberList.equals(that.memberList) : that.memberList == null;
    }

    @Override
    public int hashCode() {
        int result = memberList != null ? memberList.hashCode() : 0;
        result = 31 * result + time;
        return result;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "FitnessClub{" +
                "memberList=" + memberList +
                ", time=" + time +
                '}';
    }
}
