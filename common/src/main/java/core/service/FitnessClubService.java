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

    boolean[] findLongestAvailableToday(LocalDate date);

    MemberDTO searchForMember(String lastname);

    Set<MemberDTO> showMembersInFitnessClubNow();

    Set<MemberDTO> showTodaysMembers();

}
