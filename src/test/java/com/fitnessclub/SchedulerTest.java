package com.fitnessclub;

import com.fitnessclub.dto.Member;
import org.junit.Test;

public class SchedulerTest {

    @Test(expected = IllegalArgumentException.class)
    public void reservationTestNull() {
        Member m = null;
        Scheduler.getInstance().reserve(m, new int[]{1, 2});
    }

    @Test(expected = IllegalArgumentException.class)
    public void reservationTestForArrayNull() {
        Member m = new Member("Marko", "Devic", 12345);
        Scheduler.getInstance().reserve(m, new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void reservationTestForArrayBiggerThan3() {
        Member m = new Member("Marko", "Devic", 12345);
        Scheduler.getInstance().reserve(m, new int[]{1, 2, 4, 5});
    }

    @Test(expected = IllegalArgumentException.class)
    public void reservationTestForArrayBiggerThan12() {
        Member m = new Member("Marko", "Devic", 12345);
        Scheduler.getInstance().reserve(m, new int[]{13, 15});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservationTest() {
        Member m = new Member("Marko", "Devic", 123);
        Scheduler.getInstance().deleteReservation(m, new int[]{1, 2, 3, 4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservationForNoMember() {
        Member m = null;
        Scheduler.getInstance().deleteReservation(m, new int[]{1, 2, 3, 4});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteReservationForEmptyArray() {
        Member m = new Member("Marko", "Devic", 123);
        Scheduler.getInstance().deleteReservation(m, new int[]{});
    }
}