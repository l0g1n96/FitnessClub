package core.service;

import dto.MemberDTO;

import java.time.LocalDate;
import java.util.Set;

/**
 * Kljucne metode/servisi neophodni za funkcionisanje programa
 */

public interface FitnessClubService {

    boolean[] register(MemberDTO member, LocalDate date, int[] hours);

    boolean unregister(MemberDTO member, LocalDate date, int[] hours);

    void findLongestAvailableToday();

    MemberDTO searchForMember();

    Set<MemberDTO> showMembersInFitnessClubNow();

    Set<MemberDTO> showTodaysMembers();

}
