package datamanage;

import dto.MemberDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SchedulerDataImplTest {

    private Scheduler sData;

    @Before
    public void setUp() throws Exception {
        sData = new Scheduler();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToSchedulerForMemberNull() {

        MemberDTO member = null;
        sData.addToScheduler(member, new int[]{9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToSchedulerForNoHours() {
        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.addToScheduler(member, new int[]{});
    }

    @Test
    public void addToSchedulerForOneMember() {

        MemberDTO member = new MemberDTO("a", "a", 1);

        sData.addToScheduler(member, new int[]{9});

        Set<MemberDTO>[] set = sData.getScheduledMembers();

        Assert.assertTrue(set[1].contains(member));
    }

    @Test
    public void addToSchedulerForTwoMembers() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        MemberDTO member2 = new MemberDTO("d", "d", 2);

        sData.addToScheduler(member, new int[]{9});
        sData.addToScheduler(member2, new int[]{9});

        Set<MemberDTO>[] set = sData.getScheduledMembers();

        Assert.assertTrue(set[1].contains(member) && set[1].contains(member2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToSchedulerForHoursBiggerThanBookingHours() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.addToScheduler(member, new int[]{9, 10, 11, 12, 13});
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToSchedulerForHoursBiggerThanWorkingHours() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.addToScheduler(member, new int[]{21});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteSchedulerForMemberNull() {

        MemberDTO member = null;
        sData.deleteScheduler(member, new int[]{9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteSchedulerForNoHours() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.deleteScheduler(member, new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteSchedulerForHoursBiggerThanBooking() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.deleteScheduler(member, new int[]{9, 10, 11, 12});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteSchedulerForHoursSmallerThanZero() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.deleteScheduler(member, new int[]{-1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteSchedulerForHoursBiggerThanWorkingHours() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.deleteScheduler(member, new int[]{21, 22});
    }

    @Test
    public void deleteSchedulerTest() {

        //First I have to add to Scheduler so I can see if it is deleted after..
        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.addToScheduler(member, new int[]{9});
        Set<MemberDTO>[] set = sData.getScheduledMembers();

        //Deleting...
        sData.deleteScheduler(member, new int[]{9});

        //Checking if set does NOT contain given member
        Assert.assertTrue(!set[1].contains(member));
    }

    @Test
    public void longestPeriodAvailableTest() {

        sData.addToScheduler(new MemberDTO("Marko", "Devic", 123), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "Markovic", 124), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "c", 125), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "a", 126), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "d", 127), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "e", 128), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "r", 129), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "t", 121), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "q", 122), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "w", 131), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "y", 132), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "u", 133), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "i", 134), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "o", 135), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "z", 136), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "x", 137), new int[]{9});
        sData.addToScheduler(new MemberDTO("Marko", "v", 137), new int[]{10});

        int longestAvailable = sData.longestPeriodAvailable();

        Assert.assertEquals(longestAvailable, 11);
    }


}