package com.fitnessclub.model;

import com.fitnessclub.core.dto.Member;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class FitnessClubTest {

    @Test(expected = IllegalArgumentException.class)
    public void registerForAfter12Hours() throws Exception {

        Member m = new Member("Marko", "Devic", 12345);
        FitnessClub fitnessClub = new FitnessClub();
        fitnessClub.register(m, LocalDate.now(), new int[]{21});

    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForNoMember() throws Exception {

        Member m = null;
        FitnessClub fitnessClub = new FitnessClub();
        fitnessClub.register(m, LocalDate.now(), new int[]{1, 2, 3});

    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForEmptyHours() throws Exception {

        Member m = new Member("Marko", "Devic", 12345);
        FitnessClub fitnessClub = new FitnessClub();
        Date d = new Date();
        fitnessClub.register(m, LocalDate.now(), new int[]{});

    }

    @Test(expected = IllegalArgumentException.class)
    public void registerTestForMoreThanThreeHours() throws Exception {

        Member m = new Member("Marko", "Devic", 12345);
        FitnessClub fitnessClub = new FitnessClub();
        Date d = new Date();
        fitnessClub.register(m, LocalDate.now(), new int[]{1, 2, 3, 4});

    }

    @Test(expected = IllegalArgumentException.class)
    public void unRegisterForNoMember() {

        Member m = null;
        FitnessClub fitnessClub = new FitnessClub();
        Date d = new Date();
        fitnessClub.unregister(m, LocalDate.now(), new int[]{1, 2, 3});

    }

    @Test(expected = IllegalArgumentException.class)
    public void unRegisterForHoursBiggerThan12() {

        Member m = new Member("Marko", "Devic", 1234);
        FitnessClub fitnessClub = new FitnessClub();
        Date d = new Date();
        fitnessClub.unregister(m, LocalDate.now(), new int[]{});

    }

}