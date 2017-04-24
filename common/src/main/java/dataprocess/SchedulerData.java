package dataprocess;

import dto.MemberDTO;

public interface SchedulerData {

    boolean[] addToScheduler(MemberDTO member, int[] hours);

    void deleteScheduler(MemberDTO member, int[] hours);

    boolean[] findFreeSlots();
}
