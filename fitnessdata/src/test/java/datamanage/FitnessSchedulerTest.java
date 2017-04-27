package datamanage;

import dataprocess.Scheduler;
import dto.MemberDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FitnessSchedulerTest {

    private Scheduler sData;

    @Before
    public void setUp() throws Exception {
        sData = new FitnessScheduler();
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
        Set<MemberDTO> set = sData.getScheduledMembers(9);
        Assert.assertTrue(set.contains(member));
    }

    @Test
    public void addToSchedulerForTwoMembers() {
        MemberDTO member = new MemberDTO("a", "a", 1);
        MemberDTO member2 = new MemberDTO("d", "d", 2);

        sData.addToScheduler(member, new int[]{9});
        sData.addToScheduler(member2, new int[]{9});

        Set<MemberDTO> set = sData.getScheduledMembers(9);
        Assert.assertTrue(set.contains(member) && set.contains(member2));
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
        //First I have to add to FitnessScheduler so I can see if it is deleted after..
        MemberDTO member = new MemberDTO("a", "a", 1);
        sData.addToScheduler(member, new int[]{9});
        Set<MemberDTO> set = sData.getScheduledMembers(9);

        //Deleting...
        sData.deleteScheduler(member, new int[]{9});

        //Checking if set does NOT contain given member
        Assert.assertTrue(!set.contains(member));
    }

    @Test
    public void findFreeSlots() {
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
        sData.addToScheduler(new MemberDTO("Marko", "v", 137), new int[]{9});

        boolean[] freeSlots = sData.findFreeSlots();
        boolean[] actual = new boolean[]{false, true, false, false, false, false, false, false, false, false, false, false};

        Assert.assertArrayEquals(freeSlots, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSchedulerMembersForHoursSmallerThanBeginningHour() {
        sData.getScheduledMembers(7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSchedulerMembersForHoursBiggerThanWorkingHours() {
        sData.getScheduledMembers(21);
    }

    @Test
    public void getSchedulerMembersForHourTest() {
        MemberDTO member = new MemberDTO("Marko", "Devic", 1);

        sData.addToScheduler(member, new int[]{9});
        Set<MemberDTO> scheduledMembers = sData.getScheduledMembers(9);

        Assert.assertTrue(scheduledMembers.contains(member));
    }

    @Test
    public void getSchedulerMembers() {
        MemberDTO m1 = new MemberDTO("Marko", "Devic", 1);
        MemberDTO m2 = new MemberDTO("m", "d", 2);
        MemberDTO m3 = new MemberDTO("w", "d", 3);

        Set<MemberDTO> members = new HashSet<>();
        members.add(m1);
        members.add(m2);
        members.add(m3);

        sData.addToScheduler(m1, new int[]{9});
        sData.addToScheduler(m2, new int[]{9});
        sData.addToScheduler(m3, new int[]{9});

        Set<MemberDTO> allScheduledMembers = sData.getAllScheduledMembers();
        Assert.assertEquals(members, allScheduledMembers);
    }

    @Test
    public void getMemberNull() {
        Assert.assertNull(sData.getMember(""));
    }

    @Test
    public void getMemberTest() {
        MemberDTO member = new MemberDTO("Marko", "Devic", 1);
        sData.addToScheduler(member, new int[]{9});

        MemberDTO searchMember = sData.getMember("Devic");
        Assert.assertEquals(member, searchMember);
    }
}