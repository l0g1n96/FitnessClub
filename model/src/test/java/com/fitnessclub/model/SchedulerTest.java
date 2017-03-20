package com.fitnessclub.model;

import com.fitnessclub.core.dto.Member;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class SchedulerTest {

    @Test(expected = IllegalArgumentException.class)
    public void reservationTestNull() {
        Member m = null;
        Scheduler scheduler = new Scheduler();
        scheduler.reserve(m, new int[]{1, 2});
    }

    @Test
    public void reservationForOneMember() {
        Member m = new Member("Marko", "Devic", 123);
        Scheduler scheduler = new Scheduler();
        scheduler.reserve(m, new int[]{9});

        Set<Member>[] set = scheduler.getScheduledMembers();

        Assert.assertTrue(set[1].contains(m));
    }

    @Test
    public void reservationForTwoMembers() {
        Member marko = new Member("Marko", "Devic", 121);
        Member laza = new Member("Laza", "Lazic", 120);
        Scheduler scheduler = new Scheduler();
        scheduler.reserve(marko, new int[]{9});
        scheduler.reserve(laza, new int[]{9});

        Set<Member>[] set = scheduler.getScheduledMembers();

        Assert.assertTrue(set[1].contains(marko) && set[1].contains(laza));
    }

    @Test(expected = IllegalArgumentException.class)
    public void reservationTestForArrayNull() {
        Member m = new Member("Marko", "Devic", 12345);
        Scheduler scheduler = new Scheduler();
        scheduler.reserve(m, new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void reservationTestForArrayBiggerThan3() {
        Member m = new Member("Marko", "Devic", 12345);
        Scheduler scheduler = new Scheduler();
        scheduler.reserve(m, new int[]{1, 2, 3, 4, 5});
    }

    @Test(expected = IllegalArgumentException.class)
    public void reservationTestForArrayBiggerThan12() {
        Member m = new Member("Marko", "Devic", 12345);
        Scheduler scheduler = new Scheduler();
        scheduler.reserve(m, new int[]{21, 7});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservationTest() {
        Member m = new Member("Marko", "Devic", 123);
        Scheduler scheduler = new Scheduler();
        scheduler.deleteReservation(m, new int[]{1, 2, 3, 4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservationForNoMember() {
        Member m = null;
        Scheduler scheduler = new Scheduler();
        scheduler.deleteReservation(m, new int[]{1, 2, 3, 4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservationForEmptyArray() {
        Member m = new Member("Marko", "Devic", 123);
        Scheduler scheduler = new Scheduler();
        scheduler.deleteReservation(m, new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservationForHourBiggerThan12() {
        Member m = new Member("Marko", "Devic", 123);
        Scheduler scheduler = new Scheduler();
        scheduler.deleteReservation(m, new int[]{13});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservationForHourSmallerThan1() {
        Member m = new Member("Marko", "Devic", 123);
        Scheduler scheduler = new Scheduler();
        scheduler.deleteReservation(m, new int[]{-1});
    }

    @Test
    public void freeSlotsTest() throws Exception {
        Scheduler scheduler = new Scheduler();

        scheduler.reserve(new Member("Marko", "Devic", 123), new int[]{9});
        scheduler.reserve(new Member("Marko", "Markovic", 124), new int[]{9});
        scheduler.reserve(new Member("Marko", "c", 125), new int[]{9});
        scheduler.reserve(new Member("Marko", "a", 126), new int[]{9});
        scheduler.reserve(new Member("Marko", "d", 127), new int[]{9});
        scheduler.reserve(new Member("Marko", "e", 128), new int[]{9});
        scheduler.reserve(new Member("Marko", "r", 129), new int[]{9});
        scheduler.reserve(new Member("Marko", "t", 121), new int[]{9});
        scheduler.reserve(new Member("Marko", "q", 122), new int[]{9});
        scheduler.reserve(new Member("Marko", "w", 131), new int[]{9});
        scheduler.reserve(new Member("Marko", "y", 132), new int[]{9});
        scheduler.reserve(new Member("Marko", "u", 133), new int[]{9});
        scheduler.reserve(new Member("Marko", "i", 134), new int[]{9});
        scheduler.reserve(new Member("Marko", "o", 135), new int[]{9});
        scheduler.reserve(new Member("Marko", "z", 136), new int[]{9});
        scheduler.reserve(new Member("Marko", "x", 137), new int[]{9});
        scheduler.reserve(new Member("Marko", "v", 137), new int[]{9});

        boolean b[] = scheduler.findFreeSlots();
        boolean a[] = new boolean[]{true, false, true, true, true, true, true, true, true, true, true, true};

        Assert.assertArrayEquals(b, a);

    }

}