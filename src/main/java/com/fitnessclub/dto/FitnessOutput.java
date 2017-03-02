package com.fitnessclub.dto;

import java.util.ArrayList;
import java.util.List;

public class FitnessOutput {

    List<Member> memberList = new ArrayList<>();

    public FitnessOutput(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FitnessOutput that = (FitnessOutput) o;

        return memberList != null ? memberList.equals(that.memberList) : that.memberList == null;
    }

    @Override
    public int hashCode() {
        return memberList != null ? memberList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FitnessOutput{" +
                "memberList=" + memberList +
                '}';
    }
}
