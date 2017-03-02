package com.fitnessclub.dto;

import com.fitnessclub.DataHandler;
import com.fitnessclub.Scheduler;

import java.util.*;

public class FC implements DataHandler {

    private Map<Date, Scheduler> schedulerMap = new HashMap<>();

    public FC(Map<Date, Scheduler> schedulerMap) {
        this.schedulerMap = schedulerMap;
    }

    public Map<Date, Scheduler> getSchedulerMap() {
        return schedulerMap;
    }

    public void setSchedulerMap(Map<Date, Scheduler> schedulerMap) {
        this.schedulerMap = schedulerMap;
    }

    @Override
    public FitnessOutput doWork(FitnessInput fitnessInput) {
        int n = fitnessInput.getOptionNumber();

        if (n == 1) {
            optionOne(schedulerMap);
        }

        FitnessOutput output = new FitnessOutput();
    }

    public List<Member> optionOne(Map<Date, Scheduler> map) {

        List<Member> members = new ArrayList<>();



        return members;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FC that = (FC) o;

        return schedulerMap != null ? schedulerMap.equals(that.schedulerMap) : that.schedulerMap == null;
    }

    @Override
    public int hashCode() {
        return schedulerMap != null ? schedulerMap.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FC{" +
                "schedulerMap=" + schedulerMap +
                '}';
    }


}
