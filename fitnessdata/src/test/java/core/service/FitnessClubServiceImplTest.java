package core.service;


import dto.MemberDTO;
import org.junit.Test;

import java.time.LocalDate;

public class FitnessClubServiceImplTest {

    @Test(expected = IllegalArgumentException.class)
    public void registerForNoMember() {

        MemberDTO member = null;
        FitnessClubServiceImpl fitnessClubService = new FitnessClubServiceImpl();

        fitnessClubService.register(member, LocalDate.now(), new int[]{9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForNoDate() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        LocalDate date = null;
        FitnessClubServiceImpl fitnessClubService = new FitnessClubServiceImpl();

        fitnessClubService.register(member, date, new int[]{9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerFornNoHours() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        FitnessClubServiceImpl fitnessClubService = new FitnessClubServiceImpl();

        fitnessClubService.register(member, LocalDate.now(), new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerForHoursBiggerThanMaxBookedHours() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        FitnessClubServiceImpl fitnessClub = new FitnessClubServiceImpl();

        fitnessClub.register(member, LocalDate.now(), new int[]{9, 10, 11, 12});
    }

    @Test(expected = IllegalArgumentException.class)
    public void unRegisterForNoMember() {

        MemberDTO m = null;
        FitnessClubServiceImpl fitnessClub = new FitnessClubServiceImpl();
        fitnessClub.unregister(m, LocalDate.now(), new int[]{9, 10, 11});

    }

    @Test(expected = IllegalArgumentException.class)
    public void unRegisterForHoursNoHours() {

        MemberDTO m = new MemberDTO("Marko", "Devic", 1234);
        FitnessClubServiceImpl fitnessClub = new FitnessClubServiceImpl();

        fitnessClub.unregister(m, LocalDate.now(), new int[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void unregisterForNoDate() {

        MemberDTO m = new MemberDTO("Marko", "Devic", 1234);
        FitnessClubServiceImpl fitnessClub = new FitnessClubServiceImpl();

        fitnessClub.unregister(m, null, new int[]{9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void unregisterForHoursBiggerThanMaxBookedHours() {

        MemberDTO member = new MemberDTO("a", "a", 1);
        FitnessClubServiceImpl fitnessClub = new FitnessClubServiceImpl();

        fitnessClub.unregister(member, LocalDate.now(), new int[]{9, 10, 11, 12});
    }

    @Test
    public void findLongestAvailableTodayTest() {



    }
}