package core.service;


import dto.MemberDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DefaultFitnessClubServiceTest {

    private FitnessClubService fitnessClubService;

    @Before
    public void setUp() {
        fitnessClubService = new DefaultFitnessClubService();
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForNoMember() {
        MemberDTO member = null;
        fitnessClubService.register(member, LocalDate.now(), new int[]{9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForNoDate() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        LocalDate date = null;
        fitnessClubService.register(member, date, new int[]{9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerFornNoHours() {
        MemberDTO member = new MemberDTO("a", "a", 1);
        fitnessClubService.register(member, LocalDate.now(), new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForHoursBiggerThanMaxBookedHours() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        fitnessClubService.register(member, LocalDate.now(), new int[]{9, 10, 11, 12});
    }

    @Test(expected = IllegalArgumentException.class)
    public void unRegisterForNoMember() {
        MemberDTO m = null;
        fitnessClubService.unregister(m, LocalDate.now(), new int[]{9, 10, 11});
    }

    @Test(expected = IllegalArgumentException.class)
    public void unRegisterForHoursNoHours() {

        MemberDTO m = new MemberDTO("Marko", "Devic", 1234);
        fitnessClubService.unregister(m, LocalDate.now(), new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void unregisterForNoDate() {
        MemberDTO m = new MemberDTO("Marko", "Devic", 1234);
        fitnessClubService.unregister(m, null, new int[]{9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void unregisterForHoursBiggerThanMaxBookedHours() {
        MemberDTO member = new MemberDTO("a", "a", 1);
        fitnessClubService.unregister(member, LocalDate.now(), new int[]{9, 10, 11, 12});
    }

    @Test
    public void showMembersInFitnessClubNow() {
        MemberDTO m1 = new MemberDTO("Marko", "Devic", 1);
        MemberDTO m2 = new MemberDTO("m", "d", 2);
        MemberDTO m3 = new MemberDTO("w", "d", 3);
        Set<MemberDTO> members = new HashSet<>();

        members.add(m1);
        members.add(m2);
        members.add(m3);

        fitnessClubService.register(m1, LocalDate.now(), new int[]{12, 13});
        fitnessClubService.register(m2, LocalDate.now(), new int[]{12, 13});
        fitnessClubService.register(m3, LocalDate.now(), new int[]{12, 13});

        Set<MemberDTO> memberSet = fitnessClubService.showMembersInFitnessClubNow();
        Assert.assertEquals(members, memberSet);
    }

    @Test
    public void showTodaysMembers() {
        String sDate = "2017-04-27";
        LocalDate date = LocalDate.parse(sDate);

        MemberDTO m1 = new MemberDTO("Marko", "Devic", 1);
        MemberDTO m2 = new MemberDTO("m", "d", 2);
        MemberDTO m3 = new MemberDTO("w", "d", 3);

        Set<MemberDTO> members = new HashSet<>();
        members.add(m1);
        members.add(m2);
        members.add(m3);

        fitnessClubService.register(m1, date, new int[]{12, 13});
        fitnessClubService.register(m2, date, new int[]{12, 13});
        fitnessClubService.register(m3, date, new int[]{12, 13});

        Set<MemberDTO> memberSet = fitnessClubService.showMembersInFitnessClubNow();
        Assert.assertEquals(members, memberSet);
    }

    @Test
    public void searchForMemberNull() {
        Assert.assertNull(fitnessClubService.searchForMember(""));
    }

    @Test
    public void searchForMemberTest() {
        MemberDTO m1 = new MemberDTO("Marko", "Devic", 1);
        fitnessClubService.register(m1, LocalDate.now(), new int[]{13, 14});
        MemberDTO searchMember = fitnessClubService.searchForMember("Devic");

        Assert.assertEquals(m1, searchMember);
    }

    @Test
    public void findLongestAvailableTodayTest() {
        String sDate = "2017-04-27";
        LocalDate date = LocalDate.parse(sDate);

        fitnessClubService.register(new MemberDTO("Marko", "Devic", 123), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "Markovic", 124), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "c", 125), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "a", 126), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "d", 127), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "e", 128), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "r", 129), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "t", 121), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "q", 122), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "w", 131), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "y", 132), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "u", 133), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "i", 134), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "o", 135), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "z", 136), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "x", 137), date, new int[]{9});
        fitnessClubService.register(new MemberDTO("Marko", "v", 137), date, new int[]{9});

        boolean[] actual = fitnessClubService.findLongestAvailableToday(date);
        boolean[] expected = new boolean[]{false, true, false, false, false, false, false, false, false, false, false, false};

        Assert.assertArrayEquals(actual, expected);
    }
}