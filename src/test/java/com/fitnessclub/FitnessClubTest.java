package com.fitnessclub;

import com.fitnessclub.dto.Member;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class FitnessClubTest {

    @Test(expected = IllegalArgumentException.class)
    public void registerForAfter12Hours() {

        Member m = new Member("Marko", "Devic", 12345);
        FitnessClub fitnessClub = new FitnessClub();
        fitnessClub.register(m, LocalDate.now(), new int[]{13});

    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForNoMember() {

        Member m = null;
        FitnessClub fitnessClub = new FitnessClub();
        fitnessClub.register(m, LocalDate.now(), new int[]{1, 2, 3});

    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForEmptyHours() {

        Member m = new Member("Marko", "Devic", 12345);
        FitnessClub fitnessClub = new FitnessClub();
        Date d = new Date();
        fitnessClub.register(m, LocalDate.now(), new int[]{});

    }

    @Test(expected = IllegalArgumentException.class)
    public void registerTestForMoreThanThreeHours() {

        Member m = new Member("Marko", "Devic", 12345);
        FitnessClub fitnessClub = new FitnessClub();
        Date d = new Date();
        fitnessClub.register(m, LocalDate.now(), new int[]{1,2,3,4});

    }

    @Test(expected = IllegalArgumentException.class)
    public void unRegisterForNoMember() {

        Member m = null;
        FitnessClub fitnessClub = new FitnessClub();
        Date d = new Date();
        fitnessClub.unregister(m, LocalDate.now(), new int[]{1,2,3});

    }

    @Test(expected = IllegalArgumentException.class)
    public void unRegisterForHoursBiggerThan12() {

        Member m = new Member("Marko", "Devic", 1234);
        FitnessClub fitnessClub = new FitnessClub();
        Date d = new Date();
        fitnessClub.unregister(m, LocalDate.now(), new int[]{});

    }

}